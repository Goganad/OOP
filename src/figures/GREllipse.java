package figures;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class GREllipse extends Ellipse implements GRFigure {
    private static String name = "Ellipse";

    private double radiusX;
    private double radiusY;

    public GREllipse(GRPoint point1, GRPoint point2, Color color){
        radiusX = Math.abs(point2.x - point1.x);
        radiusY = Math.abs(point2.y - point1.y);
        this.setCenterX(point1.x);
        this.setCenterY(point1.y);
        this.setRadiusX(radiusX);
        this.setRadiusY(radiusY);
        this.setFill(color);
        this.setStroke(color);
    }

    public static String getName(){
        return "Ellipse";
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(this);
    }
}
