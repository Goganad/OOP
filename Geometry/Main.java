package Geometry;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Figure Preview");
        Scene scene = new Scene(root, 800, 600, Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();

        GRLine line = new GRLine(50,50,750,50, 5, Color.BLACK);
        GRRectangle rectangle = new GRRectangle(100, 100, 100, 120, Color.RED, Color.BLACK);
        GRSquare square = new GRSquare(200, 400, 100, Color.DARKVIOLET, Color.BLACK);
        GRCircle circle = new GRCircle(400, 400, 50, Color.YELLOW, Color.BLACK);
        GREllipse ellipse = new GREllipse(600, 400, 75, 50, Color.GREEN, Color.BLACK);
        GRPolygon pentagon = new GRPolygon(50.0, 5, 500, 175, Color.YELLOW, Color.BLACK);
        GRPolygon hexagon = new GRPolygon(50.0, 6, 350, 175, Color.BLUE, Color.BLACK);
        GRTriangle triangle = new GRTriangle(100.0, 650, 175, Color.BLUEVIOLET, Color.BLACK);

        GRFigureList list = new GRFigureList();
        list.Add(line);
        list.Add(square);
        list.Add(circle);
        list.Add(rectangle);
        list.Add(ellipse);
        list.Add(pentagon);
        list.Add(hexagon);
        list.Add(triangle);
        list.Draw(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
