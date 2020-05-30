package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GRPentagon;
import figures.GRPoint;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class PentagonCreator extends FigureCreator {

    private GRPoint center;
    private GRPentagon pentagon;
    private String name = "Pentagon";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public PentagonCreator(Group group, FigureList figureList) {
        super(group);
        this.center = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.center.x = e.getX();
            this.center.y = e.getY();
            this.pentagon = new GRPentagon(this.center, this.center, color);
            this.pentagon.draw(this.group);
            this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            group.getChildren().remove(figureIndex);
            pentagon = new GRPentagon(this.center,new GRPoint(e.getX(), e.getY()),color);
            pentagon.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            this.figureList.add(this.pentagon);
        }
    }
}