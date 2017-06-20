import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

/**
 * Created by C12508463 on 14/12/2016.
 */
public abstract class Enemy {

    protected int width, height, lives;
    protected Color color;
    protected Point topLeft;
    protected int panelHeight,panelWidth;
    Rectangle rectangle;

    public Enemy(int width, int height,int panelWidth, int panelHeight, Color color , int lives){
        this.width = width;
        this.height = height;
        this.color = color;
        this.lives = lives;
        Random random = new Random();
        topLeft = new Point(random.nextInt(panelWidth - width), 10);
        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;

        rectangle = new Rectangle(topLeft.x,topLeft.y,width,height);
    }

    public void drawEnemy(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillRect(topLeft.x,topLeft.y,width,height);
    }

    public abstract void moveEnemy(int speed);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public int getLives() {
        return lives;
    }
    public void reduceLives(){
        lives--;
    }
}
