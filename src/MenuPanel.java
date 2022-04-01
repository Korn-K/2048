import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MenuPanel extends JPanel {
    
    private static final Font menuFont = new Font("Sukhumvit Set", Font.PLAIN, 15);

    MenuPanel() {
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(menuFont);
        resetButton.addMouseListener(new MyMouseListener(() -> {
            MainPanel.reset();
            ScorePanel.reset();
            System.out.println("Successfully Reset");
        }));
        add(resetButton);
        resetButton.setFocusable(false);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(menuFont);
        saveButton.addMouseListener(new MyMouseListener(() -> {
            String data = "";
            for (Tile tile : MainPanel.tileList) {
                data += tile.getLog2Value() + "\n";
            }
            data += MainPanel.getScore() + "\n";
            data += ScorePanel.getTime() + "\n";
            try {
                FileWriter file = new FileWriter("data.txt");
                file.write(data);
                file.close();
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            System.out.println("Successfully Saved");
        }));
        add(saveButton);
        saveButton.setFocusable(false);

        JButton loadButton = new JButton("Load");
        loadButton.setFont(menuFont);
        loadButton.addMouseListener(new MyMouseListener(() -> {
            String stringRead = "";
            try{
                File file = new File("data.txt");
                Scanner myScanner = new Scanner(file);
                while (myScanner.hasNextLine()){
                    stringRead+= myScanner.nextLine()+"\n";
                }
                myScanner.close();
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            String[] data = stringRead.strip().split("\n");
            for (int i = 0; i < MainPanel.tileList.size(); i++) {
                MainPanel.tileList.get(i).setLog2Value(Integer.parseInt(data[i]));
            }
            MainPanel.setScore(Integer.parseInt(data[data.length-2]));
            ScorePanel.setTime(Integer.parseInt(data[data.length-1]));
            System.out.println("Successfully Loaded");
        }));
        add(loadButton);
        loadButton.setFocusable(false);

        setBackground(Color.decode("#797A7E"));
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
}