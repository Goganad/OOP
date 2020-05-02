package control;

import figures.GRFigure;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.ArrayList;

public class FigureList implements Serializable {
    public ArrayList<GRFigure> figures;

    public FigureList() {
        figures = new ArrayList<>();
    }

    public void add(GRFigure figure) {
        figures.add(figure);
    }

    public void deleteAll() {
        figures.clear();
    }

}