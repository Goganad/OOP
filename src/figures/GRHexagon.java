package figures;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GRHexagon extends Polygon implements GRFigure {
    private static String name = "Hexagon";

    private double radius;
    private double alphaOffset;
    private final static int SIDES = 6;

    private static Double[] getHexagonPoints(double radius, double alphaOffset, double x0, double y0){
        Double[] pointsArray = new Double[SIDES *2];
        double alpha = alphaOffset;
        for(int i=0; i<pointsArray.length; i+=2){
            pointsArray[i] = Math.cos(alpha)*radius + x0;
            pointsArray[i+1] = Math.sin(alpha)*radius + y0;
            alpha = alpha + 2*Math.PI/SIDES;
        }
        return pointsArray;
    }

    public static String getName(){
        return "Hexagon";
    }

    public GRHexagon(GRPoint point1, GRPoint point2, Color color){
        if (point2.x > point1.x && point2.y < point1.y || point2.x < point1.x && point2.y > point1.y) {
            alphaOffset = Math.PI / 6 + Math.atan(Math.abs(point2.x - point1.x) / Math.abs(point2.y - point1.y));
        } else {
            alphaOffset = Math.PI / 2 - Math.atan(Math.abs(point2.x - point1.x) / Math.abs(point2.y - point1.y));
        }
        radius = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        this.getPoints().addAll(getHexagonPoints(radius, alphaOffset, point1.x, point1.y));
        this.setFill(color);
        this.setStroke(color);
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(this);
    }
}
