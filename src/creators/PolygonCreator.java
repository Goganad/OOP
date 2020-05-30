package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GRPolygon;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class PolygonCreator extends FigureCreator {
    enum State {waiting, setPoint}

    private ArrayList<Double> pointArrayList = new ArrayList<>();
    private Double[] points;
    private State state;
    private GRPolygon polygon;
    private String name = "Polygon";
    private int itemIndex;

    public String getName() {
        return name;
    }

    public PolygonCreator(Group group, FigureList figureList) {
        super(group);
        this.figureList = figureList;
        this.state = State.waiting;
    }

    @Override
    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
            switch (state) {
                case waiting: {
                    this.pointArrayList.add(e.getX());
                    this.pointArrayList.add(e.getY());
                    points = new Double[pointArrayList.size()];
                    points = pointArrayList.toArray(points);
                    this.polygon = new GRPolygon(points, color);
                    this.state = State.setPoint;
                    this.polygon.draw(group);
                    this.itemIndex = this.group.getChildren().size() - 1;
                    break;
                }
                case setPoint: {
                    this.group.getChildren().remove(itemIndex);
                    this.pointArrayList.add(e.getX());
                    this.pointArrayList.add(e.getY());
                    points = new Double[pointArrayList.size()];
                    points = pointArrayList.toArray(points);
                    this.polygon = new GRPolygon(points, color);
                    polygon.draw(group);
                    break;
                }
            }
        }
    }

    @Override
    public void create(KeyEvent e) {
        pointArrayList.clear();
        this.figureList.add(this.polygon);
        state = State.waiting;
    }
}
