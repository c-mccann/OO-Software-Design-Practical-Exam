import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 14/12/2016.
 */

public class SpawnEnemyRunnable implements Runnable {
    // setup whatever it works with and will get passed to the constructor, such as tanks, missiles etc
    private volatile GamePanel panel;
    private volatile CopyOnWriteArrayList<Enemy> enemies;
    private volatile Aeroplane aeroplane;
    private int pixelCount = 0;

    public SpawnEnemyRunnable(GamePanel panel, CopyOnWriteArrayList<Enemy> enemies, Aeroplane aeroplane){
        this.panel = panel;
        this.enemies = enemies;
        this.aeroplane = aeroplane;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true){

            // accessing volatile memory here, basically do  work here, remember use removeAll with a collection
            // on a collection after loop if you need to to avoid Concurrency/Comodification issues
            synchronized (this){
                pixelCount += aeroplane.getSpeed();
                if (pixelCount >= 20){
                    if(random.nextInt(4) == 0){
                        switch(random.nextInt(4)){
                            case 0:
                                enemies.add(new WhiteEnemy(panel.getWidth(),panel.getHeight()));
                                break;
                            case 1:
                                enemies.add(new GreyEnemy(panel.getWidth(),panel.getHeight()));
                                break;
                            case 2:
                                enemies.add(new RedEnemy(panel.getWidth(),panel.getHeight()));
                                break;
                            case 3:
                                enemies.add(new YellowEnemy(panel.getWidth(),panel.getHeight()));
                                break;
                        }

                    }
                    pixelCount = 0;
                }
            }
            panel.repaint();
            try{
                Thread.sleep(200); // however long you want
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
