package Geometry;

import javafx.scene.Group;
import java.util.ArrayList;

public class GRFigureList {
    private ArrayList<GRFigure> Figures;

    public GRFigureList() {
        Figures = new ArrayList<GRFigure>();
    }

    public void Add(GRFigure figure) {
        Figures.add(figure);
    }

    public void Draw(Group root) {
        for (GRFigure fig: Figures) {
            fig.draw(root);
        }
    }

}
