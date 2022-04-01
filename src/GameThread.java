import javax.swing.JFrame;

public class GameThread extends Thread{
    JFrame myFrame;

    public GameThread(JFrame myFrame){
        this.myFrame = myFrame;
    }

    public void run(){
        while (true){
            myFrame.repaint();
        }
    }
}