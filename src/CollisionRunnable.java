import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class CollisionRunnable implements Runnable {
    // setup whatever it works with and will get passed to the constructor, such as tanks, missiles etc
    private volatile GamePanel gamePanel;
    private volatile Aeroplane aeroplane;
    private volatile CopyOnWriteArrayList<Enemy> enemies;
    private volatile CopyOnWriteArrayList<Projectile> projectiles;

    public CollisionRunnable(GamePanel gamePanel, CopyOnWriteArrayList<Enemy> enemies,
                             CopyOnWriteArrayList<Projectile> projectiles, Aeroplane aeroplane){
        this.gamePanel = gamePanel;
        this.aeroplane = aeroplane;
        this.enemies = enemies;
        this.projectiles = projectiles;
    }

    @Override
    public void run() {
        while(true){

            // accessing volatile memory here, basically do  work here, remember use removeAll with a collection
            // on a collection after loop if you need to to avoid Concurrency/Comodification issues
            ArrayList<Enemy> toBeRemoved = new ArrayList<>();
            ArrayList<Projectile> toBeRemoved2 = new ArrayList<>();
            synchronized (this){
                for(Enemy e: enemies){
                    if(aeroplane.getPolygon().intersects(e.getRectangle())){
                        System.out.println("KILL");
                        aeroplane.setDeadColor();
                        try {
                            // rudimentary but works for the purpose of the game
                            Thread.sleep(5000);
                            System.exit(0);

                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    for(Projectile p: projectiles){
                        if(e.getRectangle().intersects(p.getRectangle())){
                            e.reduceLives();
                            if(e.getLives() == 0){
                                toBeRemoved.add(e);
                            }
                            toBeRemoved2.add(p);
                        }
                    }
                }
                enemies.removeAll(toBeRemoved);
                projectiles.removeAll(toBeRemoved2);

            }

            gamePanel.repaint();
            try{
                Thread.sleep(20); // however long you want
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}