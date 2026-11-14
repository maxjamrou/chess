import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Pieces {
    protected String couleur;
    protected String symbol;
    protected Image image;
    protected int x, y;
    protected final int valeur;

    public Pieces(String couleur, String symbol, int v, String imagePath) {
        this.couleur = couleur;
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
