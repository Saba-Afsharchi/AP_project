import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PLAYER extends Rectangle {
    private final Color color;
    private final ArrayList<Coordinate> Mhistory;

    public PLAYER(Color color, ArrayList Mhistory, int x ,int y,int xp,int yp ) {
        this.color=color;
        this.Mhistory=Mhistory;
        this.setFill(color);
        this.setX(50);
        this.setY(50);
        this.setWidth(xp);
        this.setHeight(yp);
    }


}
