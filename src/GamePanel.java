import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 14/12/2016.
 */

public class GamePanel extends JPanel {
    // set up user tank, enemy tank, missiles et cetera
    // also Jlabels, scores etc
    private int height, width;
    private Aeroplane aeroplane;
    private CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();

    public GamePanel(){

    }
    public GamePanel(int height, int width){
        this.height = height;
        this.width = width;
        setSize(new Dimension(width,height));
        // whatever you want or nothing here
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setFocusable(true);


        aeroplane = new Aeroplane(height,width);

        // mouse adapter template
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.out.println("X:\t" +mouseEvent.getX() + "\tY:\t" + mouseEvent.getY());

            }
        });

        // key adapter template
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                System.out.println("Char:\t" + keyEvent.getKeyChar() + "\tCode:\t" + keyEvent.getKeyCode());

                switch(keyEvent.getKeyCode()){
                    case 37: // left
                        aeroplane.moveLeft();
                        break;
                    case 39: // right
                        aeroplane.moveRight();
                        break;
                    case 38: // up
                        aeroplane.accelerate();
                        break;
                    case 40: // down
                        aeroplane.decelerate();
                        break;
                    case 32: // space
                        projectiles.add(aeroplane.shootLeft());
                        projectiles.add(aeroplane.shootRight());
                        break;
                }
               repaint();

            }
        });

        Thread spawnEnemiesThread = new Thread(new SpawnEnemyRunnable(this,enemies, aeroplane));
        spawnEnemiesThread.setDaemon(true);
        spawnEnemiesThread.start();

        Thread movementThread = new Thread((new MovementRunnable(this,enemies,projectiles,aeroplane)));
        movementThread.setDaemon(true);
        movementThread.start();

        Thread collisionThread = new Thread((new CollisionRunnable(this, enemies, projectiles, aeroplane)));
        collisionThread.setDaemon(true);
        collisionThread.start();



        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        synchronized (this){
            // for all objects: draw them
            aeroplane.drawPlane(g2d);
            for(Enemy e: enemies){
                e.drawEnemy(g2d);
            }
            for(Projectile p: projectiles){
                p.drawProjectile(g2d);
            }
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public Aeroplane getAeroplane() {
        return aeroplane;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
