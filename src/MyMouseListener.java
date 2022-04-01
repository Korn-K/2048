import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    private Runnable onClick;
    
    public MyMouseListener(Runnable onClick) {
        this.onClick = onClick;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.onClick.run();
    }

    @Override
    public void mousePressed(MouseEvent e) {
                
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}