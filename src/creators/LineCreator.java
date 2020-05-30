package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GRLine;
import figures.GRPoint;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class LineCreator extends FigureCreator {

    private GRPoint start;
    private GRLine line;
    private String name = "Line";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public LineCreator(Group group, FigureList figureList) {
        super(group);
        this.start = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.start.x = e.getX();
            this.start.y = e.getY();
            this.line = new GRLine(this.start, this.start, color);
            this.line.draw(this.group);
            this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            group.getChildren().remove(figureIndex);
            line = new GRLine(this.start,new GRPoint(e.getX(), e.getY()),color);
            line.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            this.figureList.add(this.line);
        }
    }
}