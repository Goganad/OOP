package Geometry;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GRPolygon implements GRFigure {
    private Polygon polygon = null;

    public GRPolygon(double radius, int sidesNumber, double x0, double y0, Color fillcolor, Color linecolor){
        double alpha = 0;
        double[] pointsArray = new double[2*sidesNumber];

        for(int i=0; i<pointsArray.length; i+=2){
            pointsArray[i] = Math.cos(alpha)*radius + x0;
            pointsArray[i+1] = Math.sin(alpha)*radius + y0;
            alpha = alpha + 2*Math.PI/sidesNumber;
        }

        polygon = new Polygon(pointsArray);
        polygon.setFill(fillcolor);
        polygon.setStroke(linecolor);
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(polygon);
    }
}
