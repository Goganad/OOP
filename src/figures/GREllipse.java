package figures;

import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.awt.*;

public class GREllipse extends GRFigure {
    private GRPoint center = new GRPoint();
    private double radiusX;
    private double radiusY;

    public GREllipse(GRPoint point1, GRPoint point2, SerializableColor color){
        this.color = color;
        this.radiusX = Math.abs(point2.x - point1.x);
        this.radiusY = Math.abs(point2.y - point1.y);
        this.center.x = point1.x;
        this.center.y = point1.y;
        this.name = "Ellipse";
    }

    @Override
    public Node draw(Group group) {
        Ellipse boof = new Ellipse(this.center.x, this.center.y, this.radiusX, this.radiusY);
        boof.setFill(this.color.getFXColor());
        group.getChildren().add(boof);
        return boof;
    }
}
