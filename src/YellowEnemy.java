import java.awt.*;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class YellowEnemy extends Enemy {
    protected boolean isAeroplaneLeft;

    public YellowEnemy(int panelWidth, int panelHeight) {
        super(20, 20, panelWidth, panelHeight, Color.YELLOW, 1);
    }

    @Override
    public void moveEnemy(int speed) {
        topLeft.y += speed;
        if(isAeroplaneLeft){
            topLeft.x += 3;
        }
        else{
            topLeft.x -= 3;
        }
        rectangle.setRect(topLeft.x,topLeft.y,width,height);

    }
    public void decideMovement(Aeroplane aeroplane){
        if(aeroplane.getCurrentX() < topLeft.x + (width /2)){
            isAeroplaneLeft = false;
        }
        if(aeroplane.getCurrentX() > topLeft.x - (width /2)){
            isAeroplaneLeft = true;
        }
    }

    @Override
    public void drawEnemy(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(topLeft.x,topLeft.y,width,height);
    }
}
