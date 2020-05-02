package figures;

import control.SerializableColor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class GRRectangle extends GRFigure {
    private double width;
    private double height;
    private GRPoint leftTopPoint = new GRPoint();
    private SerializableColor color;

    public GRRectangle(GRPoint point1, GRPoint point2, SerializableColor color){
       this.width = 2*Math.abs(point1.x - point2.x);
       this.height = 2*Math.abs(point1.y - point2.y);
       if(point2.y < point1.y && point1.x < point2.x){
           this.leftTopPoint.x = point2.x - width;
           this.leftTopPoint.y = point2.y;
       } else
       if(point2.y < point1.y && point1.x > point2.x){
           this.leftTopPoint.x = point2.x;
           this.leftTopPoint.y = point2.y;
       } else
       if(point2.y > point1.y && point1.x > point2.x){
           this.leftTopPoint.x = point2.x;
           this.leftTopPoint.y = point2.y - height;
       } else {
           this.leftTopPoint.x = point2.x - width;
           this.leftTopPoint.y = point2.y - height;
       }
        this.color = color;
        this.name = "Rectangle";
    }

    @Override
    public Node draw(Group group) {
        Rectangle boof = new Rectangle(leftTopPoint.x, leftTopPoint.y, width, height);
        boof.setFill(this.color.getFXColor());
        group.getChildren().add(boof);
        return boof;
    }
}
