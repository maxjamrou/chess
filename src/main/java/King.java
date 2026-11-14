import javax.swing.ImageIcon;

public class King extends Pieces {
    private final String normalImagePath;
    private final String redImagePath = "red_king.png";

    public King(String couleur, int x, int y) {
        super(couleur, "K", 0, x, y, couleur.equals("BLANC") ? "white_king.png" : "black_king.png");
        normalImagePath = couleur.equals("BLANC") ? "white_king.png" : "black_king.png";
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        int dRow = Math.abs(row - selectedRow);
        int dCol = Math.abs(col - selectedCol);
        return (dRow <= 1 && dCol <= 1) &&
                (board[row][col] == null || !board[row][col].couleur.equals(this.couleur));
    }

    public void resetImage() {
        this.image = new ImageIcon(getClass().getResource("/images/" + normalImagePath)).getImage();
    }

    public void setCheckImage() {
        this.image = new ImageIcon(getClass().getResource("/images/" + redImagePath)).getImage();
    }
}
