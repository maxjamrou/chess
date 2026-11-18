import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Pieces {
    protected String couleur;
    protected String symbol;
    protected Image image;
    protected int x, y;
    protected final int valeur;
    protected boolean captured;

    public Pieces(String couleur, String symbol, int v, int x, int y, String imagePath) {
        this.couleur = couleur;
        this.symbol = symbol;
        this.valeur = v;
        this.x = x;
        this.y = y;
        this.captured = false;
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

    public int getX(){return this.x;}
    public void setX(int x){this.x = x;}

    public int getY(){return this.y;}
    public void setY(int y){this.y = y;}
    
    public boolean isCaptured(){return this.captured;}
    public void switchCaptured(){this.captured = true;}

    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Pieces[][] board);
}
