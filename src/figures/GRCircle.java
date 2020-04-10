package figures;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GRCircle extends Circle implements GRFigure {
    private static double radius;
    private static String name = "Circle";

    public GRCircle(GRPoint point1, GRPoint point2, Color color){
        radius = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        this.setCenterX(point1.x);
        this.setCenterY(point1.y);
        this.setRadius(radius);
        this.setFill(color);
        this.setStroke(color);
    }

    public static String getName(){
        return "Circle";
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(this);
    }
}
