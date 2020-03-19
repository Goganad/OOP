package Geometry;

import javafx.scene.paint.Color;

public class GRTriangle extends GRPolygon {
    public GRTriangle(double side, double x0, double y0, Color fillcolor, Color linecolor){
        super(side*Math.sqrt(3)/3, 3, x0, y0, fillcolor, linecolor);
    }
}
