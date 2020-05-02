package control;

import figures.GRFigure;
import figures.GRPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private GRPoint point1 = new GRPoint();
    private GRPoint point2 = new GRPoint();

    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<String> figureNames = new ArrayList<>();
    FigureList figureList = new FigureList();
    private Constructor[] figureConstructors;
    private ArrayList<Constructor> constructors = new ArrayList<>();
    private GRFigure figure = null;
    private Node boofer = null;
    private int itemIndex = 0;
    private int grpIndex = 0;

    @FXML
    private Group grpMain;

    @FXML
    private ColorPicker clrPicker;

    @FXML
    private ComboBox<String> boxShape;

    private Window stage;
    private FileChooser fileChooser = new FileChooser();

    private static Field getField(Class cl, String fieldName) throws NoSuchFieldException {
        try {
            return cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = cl.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class neededParameterType1 = Class.forName("figures.GRPoint");
            Class neededParameterType2 = Class.forName("control.SerializableColor");
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
                        SerializableColor color = new SerializableColor(clrPicker.getValue());
                        figure = (GRFigure) constructor.newInstance(point1, point2, color);
                        Class figureClass = figure.getClass();
                        Field nameField = getField(figureClass, "name");
                        nameField.setAccessible(true);
                        figureNames.add((String)nameField.get(figure));
                        constructors.add(constructor);
                    }
                }
            }
            boxShape.getItems().addAll(figureNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boxShape.setValue(figureNames.get(0));
    }

    @FXML
    void paneMouseClick(MouseEvent event) {
        point1.x = (int)event.getX();
        point1.y = (int)event.getY();
        for (int i = 0;i<figureNames.size();i++) {
            if (figureNames.get(i) == boxShape.getValue()){
                itemIndex = i;
            }
        }
    }

    @FXML
    void paneMouseDrag(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        grpMain.getChildren().remove(boofer);
        point2.x = (int)event.getX();
        point2.y = (int)event.getY();
        figure = (GRFigure) constructors.get(itemIndex).newInstance(point1, point2, new SerializableColor(clrPicker.getValue()));
        boofer = figure.draw(grpMain);
    }

    @FXML
    void paneMouseRelease(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        figure = (GRFigure) constructors.get(itemIndex).newInstance(point1, point2, new SerializableColor(clrPicker.getValue()));
        boofer = figure.draw(grpMain);
        figureList.add(figure);
        System.out.println(figureList.figures);
    }

    @FXML
    void fileOpen(ActionEvent event) {
        try {
            stage = this.clrPicker.getScene().getWindow();
            fileChooser.setTitle("Open Dialog");
            fileChooser.setInitialFileName("figures.gr");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("graphics file", "*.gr")
            );
            File file = fileChooser.showOpenDialog(stage);
            if (file!=null) {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                ArrayList<GRFigure> newFigureList = (ArrayList<GRFigure>)inputStream.readObject();
                grpMain.getChildren().removeAll();
                for (GRFigure figure:newFigureList
                ) {
                    figure.draw(grpMain);
                    figureList.add(figure);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fileSaveAs(ActionEvent event) {
        try {
            stage = this.clrPicker.getScene().getWindow();
            fileChooser.setTitle("Save Dialog");
            fileChooser.setInitialFileName("figures.gr");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Graphics file", "*.gr")
            );
            File file = fileChooser.showSaveDialog(stage);
            if (file!=null) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
                outputStream.writeObject(figureList.figures);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
