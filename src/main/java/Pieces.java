import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Pieces {
    protected boolean witchPlayer;
    protected String symbol;
    protected Image image;
    protected int x, y;

    public Pieces(boolean witchPlayer, String symbol, int x, int y, String imagePath) {
        this.witchPlayer = witchPlayer;
        this.symbol = symbol;
        this.x = x;
        this.y = y;

        if (imagePath != null) {
            this.image = new ImageIcon(getClass().getResource("/images/" + imagePath)).getImage();
        }

    }


    public Image getImage() {
        return image;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getX(){return this.x;}
    public void setX(int x){this.x = x;}

    public int getY(){return this.y;}
    public void setY(int y){this.y = y;}
    
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Pieces[][] board);
}
