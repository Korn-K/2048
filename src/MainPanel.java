import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements KeyListener{
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;

    private static int score = 0;

    static ArrayList<Tile> tileList = new ArrayList<Tile>();

    MainPanel(){
        addKeyListener(this);
        setFocusable(true);
        reset();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Tile tile : tileList) {
            tile.draw(g);
        }
    }

    public static void reset() {
        tileList.clear();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tileList.add(new Tile(row, col));
            }
        }
        score = 0;
        generate(3);
    }

    public static void setScore(int inputScore) {
        score = inputScore;
    }

    public static String getScore() {
        return Integer.toString(score);
    }
    
    public static Boolean movableUp(){
        Boolean movable = false;
        for (int index=tileList.size()-1; index>3; index--){
            Tile currentTile = tileList.get(index);
            if (currentTile.getLog2Value() != 0 & tileList.get(index-4).getLog2Value() == 0){
                movable = true;
                break;
            }
            if (currentTile.getLog2Value() != 0 & currentTile.getLog2Value() == tileList.get(index-4).getLog2Value()){
                movable = true;
                break;
            }
        }
        return movable;
    }


    private static void moveUp(){
        // if can merge with upper block do it
        for (int colnum=0; colnum<4; colnum++){
            for (int rownum=0; rownum<4; rownum++){
          
                int index = rownum*4 + colnum;
                int focusTileValue = tileList.get(index).getLog2Value();
                if (focusTileValue != 0){

                    for (int pairingrownum=rownum+1; pairingrownum<4; pairingrownum++){

                        int pairingindex = pairingrownum*4 + colnum;
                        int pairingTileValue = tileList.get(pairingindex).getLog2Value();

                        if (focusTileValue == pairingTileValue){
                            score += Math.pow(2,focusTileValue+1);
                            tileList.get(index).setLog2Value(focusTileValue+1);
                            tileList.get(pairingindex).setLog2Value(0);

                            rownum = pairingrownum - 1;
                            break;
                        } else if (pairingTileValue > 0) {
                            rownum = pairingrownum -1;
                            break;
                        }
                    }
                }
            }
        }

        // if the current tile empty (log2value=0) then move up (loop3 to properly move all tiles)
        for (int colnum = 0; colnum < 4; colnum++){
            for (int rownum=0; rownum<4; rownum++){
                int index = rownum * 4 + colnum;
                Tile focusTile = tileList.get(index);
                if (focusTile.getLog2Value() == 0){
                    for (int pairingrownum=rownum+1; pairingrownum<4;pairingrownum++){
                        int pairingindex = pairingrownum*4 + colnum;
                        Tile pairingTile = tileList.get(pairingindex);
                        if (pairingTile.getLog2Value() != 0){
                            focusTile.setLog2Value(pairingTile.getLog2Value());
                            pairingTile.setLog2Value(0);
                            rownum = pairingrownum - 1;
                            break;
                        }
                    }
                }
            }
            // System.out.println("no more tiles to move in col"+colnum);
        }
    }

    private static void rotate(){
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for (int index=0; index<tileList.size(); index++){
            tempList.add(tileList.get(index).getLog2Value());
        }
        for (int index=0; index<tileList.size(); index++){
            int row = index/4;
            int col = index%4;
            int newIndex = (3-col)*4 + row;
            tileList.get(index).setLog2Value(tempList.get(newIndex));
        }
    }

    private static void rotate(int iter){
        for (int i=0; i<iter;i++){
            rotate();
        }
    }

    // generate new cell
    private static void generate(){
        ArrayList<Integer> emptyTileIndices = new ArrayList<Integer>();
        for (int index = 0; index<tileList.size(); index++){
            if (tileList.get(index).getLog2Value()==0) {
                emptyTileIndices.add(index);
            }
        }
        Collections.shuffle(emptyTileIndices);
        int randomEmptyTileIndex = emptyTileIndices.get(0);
        tileList.get(randomEmptyTileIndex).setLog2Value(1);
    }

    private static void generate(int iter){
        for (int i=0; i<iter; i++){
            generate();
        }
    }

    private static void Up() {
        if(movableUp()){
            moveUp();
            // play move sound
            generate();
        } else {
            // play not move sound
        }
    }

    private static void Down() {
        rotate(2);
        Up();
        rotate(2);
    }

    private static void Right() {
        rotate(3);
        Up();
        rotate();
    }
    private static void Left() {
        rotate();
        Up();
        rotate(3);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {Up();}
        if (e.getKeyCode() == KeyEvent.VK_A) {Left();}
        if (e.getKeyCode() == KeyEvent.VK_D) {Right();}
        if (e.getKeyCode() == KeyEvent.VK_S) {Down();}
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}