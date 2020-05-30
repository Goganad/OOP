package control;

import javafx.scene.Group;
import javafx.scene.Node;
import java.io.Serializable;

public abstract class GRFigure implements Serializable {
    protected SerializableColor color;
    public abstract Node draw(Group group);
}