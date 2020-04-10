package control;

import figures.GRFigure;
import figures.GRPoint;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private GRPoint point1 = new GRPoint(0,0);
    private GRPoint point2 = new GRPoint(0,0);

    private ArrayList<Class> classes = new ArrayList<Class>();
    private ArrayList<String> figureNames = new ArrayList<String>();
    private Constructor[] figureConstructors;
    private ArrayList<Constructor> constructors = new ArrayList<Constructor>();
    private Node boofer = null;
    private int itemIndex = 0;

    @FXML
    private Group grpMain;

    @FXML
    private ColorPicker clrPicker;

    @FXML
    private ComboBox<String> boxShape;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class neededParameterType1 = Class.forName("figures.GRPoint");
            Class neededParameterType2 = Class.forName("javafx.scene.paint.Color");
            classes = getClassesFromPackage("figures");
            System.out.println(classes);

            for (Class cl: classes) {
                figureConstructors = cl.getConstructors();
                for (Constructor constructor : figureConstructors) {
                    if (constructor.getParameterCount() == 3 &&
                            constructor.getParameterTypes()[0] == neededParameterType1 &&
                            constructor.getParameterTypes()[1] == neededParameterType1 &&
                            constructor.getParameterTypes()[2] == neededParameterType2){
                        GRFigure figure;
                        figure = (GRFigure) constructor.newInstance(point1, point2, clrPicker.getValue());
                        Field nameField = cl.getDeclaredField("name");
                        nameField.setAccessible(true);
                        figureNames.add((String)nameField.get(figure));
                        constructors.add(constructor);
                    }
                }
            }
            boxShape.getItems().addAll(figureNames);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Loading classes failed");
            alert.setContentText("Error occured while load classes from file!");
        }

        boxShape.setValue(figureNames.get(0));
    }

    @FXML
    void paneMouseClick(MouseEvent event) {
        point1.x = event.getX();
        point1.y = event.getY();
    }

    @FXML
    void paneMouseDrag(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        grpMain.getChildren().remove(boofer);
        point2.x = event.getX();
        point2.y = event.getY();
        for (int i = 0;i<figureNames.size();i++) {
            if (figureNames.get(i) == boxShape.getValue()){
                itemIndex = i;
            }
        }
        boofer = (Node) constructors.get(itemIndex).newInstance(point1, point2, clrPicker.getValue());
        grpMain.getChildren().add(boofer);
    }

    @FXML
    void paneMouseRelease(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        boofer = (Node) constructors.get(itemIndex).newInstance(point1, point2, clrPicker.getValue());
        grpMain.getChildren().add(boofer);
    }

    private static ArrayList<Class> getClassesFromPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ArrayList<Class> classes = new ArrayList<Class>();

        packageName = packageName.replace(".", "/");
        URL packageURL = classLoader.getResource(packageName);

        InputStream inputStream = (InputStream) packageURL.getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String newLine = bufferedReader.readLine();
        while (newLine != null) {
            if (newLine.endsWith(".class")) {
                String buf = packageName.replace("/", ".") + "." + newLine.substring(0, newLine.lastIndexOf('.'));
                classes.add(Class.forName(buf));
            }
            newLine = bufferedReader.readLine();
        }
        return classes;
    }

}
