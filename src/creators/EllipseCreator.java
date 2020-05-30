package creators;

import control.FigureCreator;
import control.FigureList;
import control.SerializableColor;
import figures.GREllipse;
import figures.GRPoint;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class EllipseCreator extends FigureCreator {

    private GRPoint center;
    private GREllipse ellipse;
    private String name = "Ellipse";
    private SerializableColor color = null;
    private int figureIndex;

    public String getName() {
        return name;
    }

    public EllipseCreator(Group group, FigureList figureList) {
        super(group);
        this.center = new GRPoint(0,0);
        this.figureList = figureList;
    }

    public void create(MouseEvent e, SerializableColor color) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.center.x = e.getX();
            this.center.y = e.getY();
            this.ellipse = new GREllipse(this.center, this.center, color);
            this.ellipse.draw(this.group);
            this.figureIndex = this.group.getChildren().size()-1;
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            group.getChildren().remove(figureIndex);
            ellipse = new GREllipse(this.center,new GRPoint(e.getX(), e.getY()),color);
            ellipse.draw(group);
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED){
            this.figureList.add(this.ellipse);
        }
    }
}