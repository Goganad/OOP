package Geometry;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GRLine implements GRFigure {
        private Line line = null;

        public GRLine(int x1, int y1, int x2, int y2, double linesize, Color linecolor){
            line = new Line(x1, y1, x2, y2);
            line.setStrokeWidth(linesize);
            line.setStroke(linecolor);
        }

        @Override
        public void draw(Group root) {
            root.getChildren().add(line);
        }
}
