import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class Aeroplane {
    private int height = 25, width = 30, movementValue = 8, currentX, currentY, speed = 1, panelHeight, panelWidth;
    Point one, two, three;
    Color color = Color.WHITE;
    Polygon polygon;
    Rectangle gunLeft, gunRight;

    public Aeroplane(int panelHeight, int panelWidth){
        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;
        currentX = panelWidth / 2;
        currentY = (int)(panelHeight - (panelHeight * 0.05));
        one = new Point(currentX - (width / 2),currentY);
        two = new Point(currentX,currentY-height);
        three = new Point(currentX + (width /2),currentY);
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        polygon = new Polygon(new int[]{one.x,two.x,three.x}, new int[]{one.y,two.y,three.y},3);

        gunLeft = new Rectangle((one.x + two.x) / 2 - 2, one.y - height + 4, 3,21);
        gunRight = new Rectangle((two.x + three.x) / 2, three.y - height + 4, 3,21);

        System.out.println("one\t" + one);
        System.out.println(two);
        System.out.println(three);

        System.out.println();
        System.out.println(gunLeft.x);
        System.out.println(gunLeft.y);
        System.out.println();
        System.out.println(gunRight.x);
        System.out.println(gunRight.y);

    }

    public void drawPlane(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillPolygon(polygon);
        // guns
        g2d.fillRect(gunLeft.x,gunLeft.y,gunLeft.width,gunLeft.height);
        g2d.fillRect(gunRight.x,gunRight.y,gunRight.width,gunRight.height);
    }


    public void moveLeft( ){
        if (currentX - (width /2) - movementValue > 0) {
            currentX -= movementValue;
            one.x -= movementValue;
            two.x -= movementValue;
            three.x -= movementValue;
            gunLeft.setRect(gunLeft.x - movementValue, gunLeft.y, gunLeft.getWidth(), gunLeft.getHeight());
            gunRight.setRect(gunRight.x - movementValue, gunRight.y, gunRight.getWidth(), gunRight.getHeight());
            polygon.translate(-movementValue, 0);
        }
    }

    public void moveRight(){
        if (currentX + (width /2) + movementValue < panelWidth){
            currentX += movementValue;
            one.x += movementValue;
            two.x += movementValue;
            three.x += movementValue;
            gunLeft.setRect(gunLeft.x + movementValue,gunLeft.y,gunLeft.getWidth(), gunLeft.getHeight());
            gunRight.setRect(gunRight.x + movementValue,gunRight.y,gunRight.getWidth(), gunRight.getHeight());
            polygon.translate(movementValue, 0);
        }

    }

    public void accelerate(){
        if(speed < 10){
            speed++;
        }
    }
    public void decelerate(){
        if(speed > 1){
            speed--;
        }
    }


    public int getSpeed() {
        return speed;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Projectile shootLeft(){
        return new Projectile(new Point(gunLeft.x, gunLeft.y));
    }
    public Projectile shootRight(){
        return new Projectile(new Point(gunRight.x,gunRight.y));
    }
    public void setDeadColor(){
        color = Color.RED;
    }

    public int getCurrentX() {
        return currentX;
    }
}
