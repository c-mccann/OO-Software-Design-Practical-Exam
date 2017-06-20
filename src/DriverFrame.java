import javax.swing.*;
import java.awt.*;

/**
 * Created by C12508463 on 14/12/2016.
 */

public class DriverFrame extends JFrame {
    private int height  = 800, width = 400;
    public DriverFrame(){
        super("Aeroplanes");
        setMinimumSize(new Dimension(width,height));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(createContentPane());
        setVisible(true);
        pack();


    }
    private Container createContentPane(){
        // Card Layout seems to be the handiest for switching panels
        Container pane = new JPanel(new CardLayout());
        pane.setPreferredSize(new Dimension(width,height));

        GamePanel gamePanel = new GamePanel(height,width);

        pane.add(gamePanel,"Game Panel",0);

        return pane;
    }



    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new DriverFrame();
        });
    }
}

