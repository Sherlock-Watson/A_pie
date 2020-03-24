import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 640,480);
        Circle circle = new Circle(30);
        circle.setStyle("-fx-fill: rgb(34, 177, 76);");
        EasingProperty easingProperty = new EasingProperty(circle.radiusProperty());
        circle.setOnMouseClicked(e -> {
            if (circle.getRadius() > 30) {
                easingProperty.setToValue(30);
            }
            else {
                easingProperty.setToValue(100);
            }
        });
        pane.getChildren().addAll(circle);
        stage.setScene(scene);
        stage.show();
    }
}
