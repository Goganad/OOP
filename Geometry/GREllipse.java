package Geometry;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class GREllipse implements GRFigure {
    private Ellipse ellipse = null;

    public GREllipse(double x, double y, double radiusX, double radiusY, Color fillcolor, Color linecolor){
        ellipse = new Ellipse();
        ellipse.setCenterX(x);
        ellipse.setCenterY(y);
        ellipse.setRadiusX(radiusX);
        ellipse.setRadiusY(radiusY);
        ellipse.setStroke(linecolor);
        ellipse.setFill(fillcolor);
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(ellipse);
    }
}
