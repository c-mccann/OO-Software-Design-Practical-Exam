import java.awt.*;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class GreyEnemy extends Enemy {
    public GreyEnemy(int panelWidth, int panelHeight) {
        super(20,20, panelWidth, panelHeight, Color.GRAY, 2);
    }

    @Override
    public void moveEnemy(int speed) {
        topLeft.y += speed;
        rectangle.setRect(topLeft.x,topLeft.y,width,height);
    }
}
