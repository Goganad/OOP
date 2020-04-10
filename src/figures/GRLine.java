package figures;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GRLine extends Line implements GRFigure {
    private static String name = "Line";

    public GRLine(GRPoint point1, GRPoint point2, Color color){
        this.setStartX(point1.x);
        this.setStartY(point1.y);
        this.setEndX(point2.x);
        this.setEndY(point2.y);
        this.setFill(color);
        this.setStroke(color);
    }

    @Override
    public void draw(Group root) {
        root.getChildren().add(this);
    }
}
