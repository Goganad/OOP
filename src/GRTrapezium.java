import control.GRFigure;
import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class GRTrapezium extends GRFigure {
    private Double[] points;

    public GRTrapezium(Double[] points, SerializableColor color) {
        this.points = points;
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
