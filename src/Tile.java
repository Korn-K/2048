import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Tile {
    // graphics settings
    private static final ArrayList<Color> colorList = new ArrayList<Color>(
        Arrays.asList(Color.decode("#CDC9C3"),Color.decode("#F6A77D"),Color.decode("#F1946F"),Color.decode("#EC8261"),
        Color.decode("#E86F53"),Color.decode("#E35D46"),Color.decode("#DF4A38"),Color.decode("#DA382A"),Color.decode("#D5251C"),Color.decode("#D1130E"),
        Color.decode("#CC0000"),Color.decode("#CC0000"),Color.decode("#C30000"),Color.decode("#B90000"),Color.decode("#B00000"),Color.decode("#A70000"),
        Color.decode("#9E0000"),Color.decode("#940000")));
    private static final int gridSpacing = 10;
    private static final int gridSize = 80;
    private static final int tileFontSize = 30;
    private static final Font tileFont = new Font("Sukhumvit Set", Font.BOLD, tileFontSize);
    private static final Color tileFontColor = Color.WHITE;

    // data
    private final int row;
    private final int col;
    private int log2value = 0;

    Tile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDisplayValue() {
        return (int)Math.pow(2, log2value);
    }

    public void setLog2Value(int log2value) {
        this.log2value = log2value;
    }

    public int getLog2Value() {
        return log2value;
    }

    public int xpos() {
        return col * gridSize + gridSpacing * (col + 1);
    }

    public int ypos() {
        return row * gridSize + gridSpacing * (row + 1);
    }

    public Color getDisplayColor(){
        return colorList.get(log2value);
    }

    public void draw(Graphics g) {
        g.setColor(getDisplayColor());
        g.fillRoundRect(xpos(), ypos(), gridSize, gridSize, gridSize/10, gridSize/10);
        g.setColor(tileFontColor);
        g.setFont(tileFont);
        if (log2value!=0){
            g.drawString(Integer.toString(getDisplayValue()), (int)Math.round(xpos()+ gridSize/2 - Integer.toString(getDisplayValue()).length()*tileFontSize/3.5), (int)Math.round(ypos() + gridSize/2 + tileFontSize/3));
        }
    }
}