import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 14/12/2016.
 */
public class MovementRunnable implements Runnable {
    // setup whatever it works with and will get passed to the constructor, such as tanks, missiles etc
    private volatile GamePanel panel;
    private volatile CopyOnWriteArrayList<Enemy> enemies;
    private volatile CopyOnWriteArrayList<Projectile> projectiles;
    private volatile Aeroplane aeroplane;
    private int updates = 0;

    public MovementRunnable(GamePanel panel, CopyOnWriteArrayList<Enemy> enemies,
                            CopyOnWriteArrayList<Projectile> projectiles, Aeroplane aeroplane){
        this.panel = panel;
        this.enemies = enemies;
        this.aeroplane = aeroplane;
        this.projectiles = projectiles;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true){

            // accessing volatile memory here, basically do  work here, remember use removeAll with a collection
            // on a collection after loop if you need to to avoid Concurrency/Comodification issues
            synchronized (this){
                ArrayList<Enemy> toBeRemoved = new ArrayList<>();
                ArrayList<Projectile> toBeRemoved2 = new ArrayList<>();
                for(Enemy e: enemies){
                    if(e instanceof YellowEnemy){
                        ((YellowEnemy) e).decideMovement(aeroplane);
                    }
                    e.moveEnemy(aeroplane.getSpeed());
                    if(e.getTopLeft().y > panel.getHeight()){
                        toBeRemoved.add(e);
                    }
                    if(e instanceof RedEnemy){
                        if(updates == 20){
                            updates = 0;
                            if(random.nextInt(2) == 0){
                                  ((RedEnemy) e).reverseHorizontalDirection();
                            }
                        }
                    }
                }
                for (Projectile p: projectiles){
                    p.move();
                    if(p.getTopLeft().y < 0){
                        toBeRemoved2.add(p);
                    }
                }
                enemies.removeAll(toBeRemoved);
                projectiles.removeAll(toBeRemoved2);
            }
            panel.repaint();
            updates++;
            if(updates == 21) updates = 0;

            try{
                Thread.sleep(20); // however long you want
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
