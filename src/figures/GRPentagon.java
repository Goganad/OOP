package figures;

import control.GRFigure;
import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class GRPentagon extends GRFigure {
    private double radius;
    private double alphaOffset;
    private Double[] points;
    private SerializableColor color;
    private final static int SIDES = 5;

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

    public GRPentagon(GRPoint point1, GRPoint point2, SerializableColor color){
        if (point2.x < point1.x && point2.y > point1.y) {
            alphaOffset = Math.PI / 2 + Math.atan((double)Math.abs(point2.x - point1.x) / (double)Math.abs(point2.y - point1.y));
        } else
        if (point2.x > point1.x && point2.y < point1.y) {
            alphaOffset = Math.PI / 3 + Math.atan((double)Math.abs(point2.x - point1.x) / (double)Math.abs(point2.y - point1.y));
        } else
        if (point2.x < point1.x && point2.y < point1.y) {
            alphaOffset = Math.PI / 3 - Math.atan((double)Math.abs(point2.x - point1.x) / (double)Math.abs(point2.y - point1.y));
        } else {
            alphaOffset = Math.PI / 2 - Math.atan((double)Math.abs(point2.x - point1.x) / (double)Math.abs(point2.y - point1.y));
        }
        this.radius = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        this.points = getHexagonPoints(radius, alphaOffset, point1.x, point1.y);
        this.color = color;
    }

    @Override
    public Node draw(Group group) {
        Polygon boof = new Polygon();
        boof.getPoints().addAll(points);
        boof.setFill(this.color.getFXColor());
        group.getChildren().add(boof);
        return boof;
    }
}
