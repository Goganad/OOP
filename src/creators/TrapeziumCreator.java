package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import control.GRPoint;
import figures.GRTrapezium;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class TrapeziumCreator extends FigureCreator {
    enum State {waiting, firstPointSet, secondPointSet, thirdPointSet}

    private GRPoint[] points;
    private State state;
    private String name = "Trapezium";

    public String getName() {
        return name;
    }

    public TrapeziumCreator(Group group, FigureList figureList) {
        super(group);
        this.points = new GRPoint[3];
        this.figureList = figureList;
        this.state = State.waiting;
    }

    @Override
    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            switch (state) {
                case waiting: {
                    points[0] = new GRPoint(e.getX(), e.getY());
                    state = State.firstPointSet;
                    break;
                }
                case firstPointSet: {
                    points[1] = new GRPoint(e.getX(), e.getY());
                    state = State.secondPointSet;
                    break;
                }
                case secondPointSet: {
                    points[2] = new GRPoint(e.getX(), e.getY());
                    state = State.thirdPointSet;
                    break;
                }
                case thirdPointSet: {
                    var x1 = points[0].x;
                    var x2 = points[1].x;
                    var x3 = points[2].x;;
                    var x4 = e.getX();
                    var y1 = points[0].y;
                    var y2 = points[1].y;
                    var y3 = points[2].y;
                    var y4 = e.getY();

                    var k = ((x4 - x3) * (x2 - x1) + (y4 - y3) * (y2 - y1)) / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                    var x = k * (x2 - x1) + x3;
                    var y = k * (y2 - y1) + y3;

                    Double[] trapeziumPoints = new Double[] {x1, y1, x2, y2, x3, y3, x, y};
                    GRTrapezium trapezium = new GRTrapezium(trapeziumPoints, color);
                    this.figureList.add(trapezium);
                    trapezium.draw(group);
                    state = State.waiting;
                    break;
                }
            }
        }
    }
}
