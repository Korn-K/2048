import javax.swing.JFrame;
import java.awt.BorderLayout;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame myFrame = new JFrame("2048");

        MainPanel mainPanel = new MainPanel();
        myFrame.add(mainPanel,"Center");

        ScorePanel scorePanel = new ScorePanel();
        myFrame.add(scorePanel,BorderLayout.NORTH);

        MenuPanel menuPanel = new MenuPanel();
        myFrame.add(menuPanel,BorderLayout.SOUTH);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(380, 500);
        myFrame.setVisible(true);
        myFrame.setResizable(false);

        
        Thread gameThread = new GameThread(myFrame);
        Thread timeThread = new TimeThread();
        try{
            gameThread.start();
            timeThread.start();
        } catch (Exception e){
            // do nothing
        }
    }
}