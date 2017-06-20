import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class WhiteEnemy extends Enemy{

    public WhiteEnemy(int panelWidth, int panelHeight){
        super(20,20, panelWidth, panelHeight, Color.WHITE, 1);
    }

    @Override
    public void moveEnemy(int speed) {
        topLeft.y += speed;
        rectangle.setRect(topLeft.x,topLeft.y,width,height);
    }
}
