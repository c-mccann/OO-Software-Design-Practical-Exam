import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class ProjectileRunnable implements Runnable {
    // setup whatever it works with and will get passed to the constructor, such as tanks, missiles etc
    private volatile JPanel panel;
    private volatile CopyOnWriteArrayList<Projectile> projectiles;

    public ProjectileRunnable(JPanel panel, CopyOnWriteArrayList<Projectile> projectiles){
        this.panel = panel;
        this.projectiles = projectiles;
    }

    @Override
    public void run() {
        while(true){

            // accessing volatile memory here, basically do  work here, remember use removeAll with a collection
            // on a collection after loop if you need to to avoid Concurrency/Comodification issues
            synchronized (this){
                for(Projectile p: projectiles){
                    p.move();
                }
            }
            panel.repaint();
            try{
                Thread.sleep(50); // however long you want
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}