import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class Projectile {
    Point topLeft;
    Color color = Color.WHITE;
    int diameter = 3; // same as gun width
    Rectangle rectangle;
    int speed = 1;
    public Projectile(Point topLeft){
        this.topLeft = topLeft;
        rectangle = new Rectangle(topLeft.x,topLeft.y,diameter,diameter);
    }

    public void move(){
        topLeft.y -= speed;
        rectangle.setRect(topLeft.x, topLeft.y,diameter,diameter);
    }

    public void drawProjectile(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillOval(topLeft.x,topLeft.y, diameter,diameter);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
