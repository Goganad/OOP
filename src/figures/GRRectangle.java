package figures;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GRRectangle extends Rectangle implements GRFigure {
    private static String name = "Rectangle";
    private double a;
    private double b;

    public GRRectangle(GRPoint point1, GRPoint point2, Color color){
       a = 2*Math.abs(point1.x - point2.x);
       b = 2*Math.abs(point1.y - point2.y);
       this.setWidth(a);
       this.setHeight(b);
       if(point2.y < point1.y && point1.x < point2.x){
           this.setX(point2.x - a);
           this.setY(point2.y);
       } else
       if(point2.y < point1.y && point1.x > point2.x){
           this.setX(point2.x);
           this.setY(point2.y);
       } else
       if(point2.y > point1.y && point1.x > point2.x){
           this.setX(point2.x);
           this.setY(point2.y - b);
       } else {
           this.setX(point2.x - a);
           this.setY(point2.y - b);
       }
        this.setFill(color);
        this.setStroke(color);
    }

    public static String getName(){
        return "Rectangle";
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(this);
    }
}
