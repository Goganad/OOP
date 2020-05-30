package control;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class FigureCreator {
    protected Group group;
    protected String name = "Name";
    protected FigureList figureList;

    public String getName() {
        return this.name;
    }

    public FigureCreator(Group group) {
        this.group = group;
    }

    public abstract void create(MouseEvent e, SerializableColor color);
    public void create(KeyEvent e){};
}
