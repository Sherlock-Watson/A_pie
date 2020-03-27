//1.创建一个二维数组用于存储省份和城市
var cities = new Array(3);
cities[0] = new Array("上海","昆明","北京","烟台");
cities[1] = new Array("东京","大阪","镰仓");
cities[2] = new Array("罗马","米兰","威尼斯","佛罗伦萨");
cities[3] = new Array("纽约","旧金山","华盛顿");

function changeCity(val){

    //7.获取第二个下拉列表
    var cityEle = document.getElementById("city");

    //9.清空第二个下拉列表的option内容
    cityEle.options.length=0;

    //2.遍历二维数组中的省份
    for(var i=0;i<cities.length;i++){
        //注意，比较的是角标
        if(val==i){
            //3.遍历用户选择的省份下的城市
            for(var j=0;j<cities[i].length;j++){
                //4.创建城市的文本节点
                var textNode = document.createTextNode(cities[i][j]);
                //5.创建option元素节点
                var opEle = document.createElement("option");
                //6.将城市的文本节点添加到option元素节点
                opEle.appendChild(textNode);
                //8.将option元素节点添加到第二个下拉列表中去
                cityEle.appendChild(opEle);
            }
        }
    }
}