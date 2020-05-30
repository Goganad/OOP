package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import control.GRPoint;
import figures.GRRectangle;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class RectangleCreator extends FigureCreator {

    private GRPoint center;
    private GRRectangle rectangle;
    private String name = "Rectangle";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public RectangleCreator(Group group, FigureList figureList) {
        super(group);
        this.center = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.center.x = e.getX();
            this.center.y = e.getY();
            this.rectangle = new GRRectangle(this.center, this.center, color);
            this.rectangle.draw(this.group);
            this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            group.getChildren().remove(figureIndex);
            rectangle = new GRRectangle(this.center,new GRPoint(e.getX(), e.getY()),color);
            rectangle.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            this.figureList.add(this.rectangle);
        }
    }
}