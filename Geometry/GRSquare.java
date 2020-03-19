package Geometry;

import javafx.scene.paint.Color;

public class GRSquare extends GRPolygon {
    public GRSquare(double x0, double y0, double side, Color fillcolor, Color linecolor){
        super(side*Math.sqrt(2)/2, 4, x0, y0, fillcolor, linecolor);
    }
}
