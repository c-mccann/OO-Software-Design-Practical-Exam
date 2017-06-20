import java.awt.*;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class RedEnemy extends Enemy {
    protected boolean goingLeft = true;
    public RedEnemy(int panelWidth, int panelHeight) {
        super(30, 15, panelWidth, panelHeight, Color.RED, 1);
    }

    @Override
    public void moveEnemy(int speed) {
        topLeft.y += speed;
        if(goingLeft){
            if(topLeft.x - speed > 0) {
                topLeft.x -= speed;
            }
            else reverseHorizontalDirection();
        }
        else{
            if(topLeft.x + speed < panelWidth){
                topLeft.x += speed;
            }
            else reverseHorizontalDirection();
        }

        rectangle.setRect(topLeft.x,topLeft.y,width,height);
    }

    public void reverseHorizontalDirection(){
        goingLeft = !goingLeft;
    }
}
