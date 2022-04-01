import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScorePanel extends JPanel {
    private static final Font scoreFont = new Font("Sukhumvit Set", Font.BOLD, 20);
    private static int time_int = 0;

    ScorePanel() {
        setFocusable(false);
        setBackground(Color.decode("#797A7E"));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
    }

    public static void reset(){
        time_int = 0;
    }

    public static void setTime(int time){
        time_int = time;
    }

    public static int getTime(){
        return time_int;
    }

    public static String getDisplayTime() {
        int second_int = time_int % 60;
        int minute_int = time_int / 60;
        String second_str = Integer.toString(second_int);
        String minute_str = Integer.toString(minute_int);
        if (second_str.length() == 1) {
            second_str = "0" + second_str;
        }
        if (minute_str.length() == 1) {
            minute_str = "0" + minute_str;
        }
        return minute_str+":"+second_str;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + MainPanel.getScore(), 20, 30);
        g.drawString(getDisplayTime(), 300, 30);
    }
}
