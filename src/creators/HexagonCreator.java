package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GRHexagon;
import control.GRPoint;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class HexagonCreator extends FigureCreator {

    private GRPoint center;
    private GRHexagon hexagon;
    private String name = "Hexagon";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public HexagonCreator(Group group, FigureList figureList) {
        super(group);
        this.center = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.center.x = e.getX();
            this.center.y = e.getY();
            this.hexagon = new GRHexagon(this.center, this.center, color);
            this.hexagon.draw(this.group);
            this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            group.getChildren().remove(figureIndex);
            hexagon = new GRHexagon(this.center,new GRPoint(e.getX(), e.getY()),color);
            hexagon.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            this.figureList.add(this.hexagon);
        }
    }
}