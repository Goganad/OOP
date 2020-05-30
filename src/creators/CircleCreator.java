package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GRCircle;
import figures.GRPoint;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class CircleCreator extends FigureCreator {

    private GRPoint center;
    private GRCircle circle;
    private String name = "Circle";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public CircleCreator(Group group, FigureList figureList) {
        super(group);
        this.center = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
                this.center.x = e.getX();
                this.center.y = e.getY();
                this.circle = new GRCircle(this.center, this.center, color);
                this.circle.draw(this.group);
                this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                group.getChildren().remove(figureIndex);
                circle = new GRCircle(this.center,new GRPoint(e.getX(), e.getY()),color);
                circle.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            figureList.add(circle);
        }
    }
}
