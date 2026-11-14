import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Pieces {
    protected boolean witchPlayer;
    protected String symbol;
    protected Image image;
    protected int x, y;
    protected final int valeur;

    public Pieces(boolean witchPlayer, String symbol, int v, String imagePath) {
        this.witchPlayer = witchPlayer;
        this.symbol = symbol;
        this.valeur = v;
        if (imagePath != null) {
            this.image = new ImageIcon(getClass().getResource("/images/" + imagePath)).getImage();
        }

    }

    public int getValeur(){return this.valeur;}

    public Image getImage() {
        return image;
    }

    public String getSymbol() {
        return symbol;
    }


    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Pieces[][] board);
}
