package figures;

import control.GRFigure;
import control.GRPoint;
import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class GRLine extends GRFigure {
    private GRPoint start = new GRPoint();
    private GRPoint end = new GRPoint();
    private SerializableColor color;

    public GRLine(GRPoint point1, GRPoint point2, SerializableColor color){
        this.start.x = point1.x;
        this.start.y = point1.y;
        this.end.x = point2.x;
        this.end.y = point2.y;
        this.color = color;
    }

    @Override
    public Node draw(Group group) {
        Line boof = new Line(start.x, start.y, end.x, end.y);
        boof.setStroke(this.color.getFXColor());
        group.getChildren().add(boof);
        return boof;
    }
}
