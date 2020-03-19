package Geometry;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GRRectangle implements GRFigure {
    private Rectangle rectangle = null;

    public GRRectangle(double x0, double y0, int width, int heigth, Color fillcolor, Color linecolor){
        rectangle = new Rectangle();
        rectangle.setX(x0);
        rectangle.setY(y0);
        rectangle.setWidth(width);
        rectangle.setHeight(heigth);
        rectangle.setFill(fillcolor);
        rectangle.setStroke(linecolor);
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(rectangle);
    }
}
