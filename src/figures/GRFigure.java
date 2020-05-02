package figures;

import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class GRFigure implements Serializable {
    protected String name;
    protected SerializableColor color;
    public String getName(){
        return this.name;
    }
    public abstract Node draw(Group group);
}