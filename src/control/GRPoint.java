package control;

import java.io.Serializable;

public class GRPoint implements Serializable {
    public double x;
    public double y;

    public GRPoint(){
        this.x = 0;
        this.y = 0;
    }

    public GRPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
}
