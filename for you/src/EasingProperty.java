import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class EasingProperty {
    private static final Duration animationDuration = Duration.millis(15);
    private static final double minUnit = 0.001;
    private static final double absoluteMinUnit = 1e-10;

    private double fromValue;
    private double toValue;
    private DoubleProperty property;
    private double currentValue;
    private Timeline t1;

    EasingProperty(DoubleProperty valueProcessed) {
        this.property = valueProcessed;
        this.fromValue = property.getValue();
        this.currentValue = this.fromValue;
        KeyFrame keyFrame = new KeyFrame(animationDuration, new KeyFrameHandler());
        t1 = new Timeline(keyFrame);
        t1.setCycleCount(Animation.INDEFINITE);
        t1.stop();
    }

    void setToValue(double value) {
        if (getTimeLine().getStatus() == Animation.Status.RUNNING)
            getTimeLine().stop();
        if (Math.abs(value - property.getValue()) < absoluteMinUnit) {
            return;
        }
        this.toValue = value;
        this.fromValue = property.getValue();
        this.currentValue = fromValue;
        getTimeLine().play();
    }

    private double getTotalDistance() {
        return (toValue - fromValue);
    }

    private double getDistance() {
        return (toValue - currentValue);
    }

    private Timeline getTimeLine() {
        return this.t1;
    }

    private int compareDouble(double a, double b) {
        if (Math.abs(a - b) < Math.abs(minUnit * getTotalDistance()))
            return 0;
        else if (a > b)
            return 1;
        else
            return -1;
    }

    private double getCurrentSpeed() {
        double distance = getDistance();
        double totalDistance = getTotalDistance();
        double rate = 0.15;
        return rate * distance;
    }

    protected class KeyFrameHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (compareDouble(currentValue, toValue) == 0) {
                currentValue = toValue;
                property.setValue(toValue);
                getTimeLine().stop();
                fromValue = toValue;
                return;
            }
            currentValue += getCurrentSpeed();
            property.setValue(currentValue);
        }
    }
}
