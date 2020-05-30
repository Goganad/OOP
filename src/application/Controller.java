package application;

import control.FigureCreator;
import control.FigureList;
import control.GRFigure;
import control.SerializableColor;
import control.GRPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
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
import java.util.Vector;
import java.util.jar.JarFile;


public class Controller implements Initializable {
    private GRPoint point1 = new GRPoint();
    private GRPoint point2 = new GRPoint();

    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<String> figureNames = new ArrayList<>();
    private ArrayList<FigureCreator> figureCreators = new ArrayList<>();
    FigureList figureList = new FigureList();
    private Constructor[] figureConstructors;
    private int itemIndex = 0;

    @FXML
    private Group grpMain = new Group();

    @FXML
    private ColorPicker clrPicker = new ColorPicker();

    @FXML
    private ComboBox<String> boxShape = new ComboBox<>();

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
            Class neededParameterType1 = Class.forName("javafx.scene.Group");
            Class neededParameterType2 = Class.forName("control.FigureList");
            classes = getClassesFromPackage("creators");
            System.out.println(classes);

            for (Class cl: classes) {
                figureConstructors = cl.getConstructors();
                for (Constructor constructor : figureConstructors) {
                    if (constructor.getParameterCount() == 2 &&
                            constructor.getParameterTypes()[0] == neededParameterType1 &&
                            constructor.getParameterTypes()[1] == neededParameterType2){
                        FigureCreator figureCreator;
                        figureCreator = (FigureCreator) constructor.newInstance(grpMain, figureList);
                        Class figureClass = figureCreator.getClass();
                        Field nameField = getField(figureClass, "name");
                        nameField.setAccessible(true);
                        figureNames.add((String)nameField.get(figureCreator));
                        figureCreators.add(figureCreator);
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
    void paneMouseClick(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int i = 0;i<figureNames.size();i++) {
            if (figureNames.get(i).equals(boxShape.getValue())){
                itemIndex = i;
            }
        }
        figureCreators.get(itemIndex).create(event, new SerializableColor(clrPicker.getValue()));
        System.out.println(figureList.figures);
    }

    @FXML
    void paneMouseDrag(MouseEvent event) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        figureCreators.get(itemIndex).create(event, new SerializableColor(clrPicker.getValue()));
    }

    @FXML
    void paneMouseRelease(MouseEvent event){
        figureCreators.get(itemIndex).create(event, new SerializableColor(clrPicker.getValue()));
    }

    @FXML
    void paneOnKeyPressed(KeyEvent event){
        figureCreators.get(itemIndex).create(event);
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

    public static void getClassesFromJARsDirectory(String directoryPath) throws Exception {
        File directory = new File(directoryPath);

        if (directory.exists()) {
            File[] jars = directory.listFiles(((dir, name) -> name.endsWith(".jar")));
            if (jars != null && jars.length != 0) {

                Vector<String> classes = new Vector<>();
                Vector<URL> urls = new Vector<>();

                for (File file : jars) {
                    JarFile jarFile = new JarFile(file);
                    jarFile.stream().forEach(jarEntry -> {if (jarEntry.getName().endsWith(".class"))
                        System.out.println(jarEntry.getRealName());});
                }
            } else {
                throw new Exception("Directory does not exist");
            }
        }
    }

}
