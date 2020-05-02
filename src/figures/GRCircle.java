package figures;

import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class GRCircle extends GRFigure {
    private static double radius;
    private GRPoint center = new GRPoint();
    private SerializableColor color;

    public GRCircle(GRPoint point1, GRPoint point2, SerializableColor color){
        this.radius = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        this.center.x = point1.x;
        this.center.y = point1.y;
        this.color = color;
        this.name = "Circle";
    }

    @Override
    public Node draw(Group group) {
        Circle boof = new Circle(this.center.x, this.center.y, radius);
        boof.setFill(this.color.getFXColor());
        group.getChildren().add(boof);
        return boof;
    }
}
