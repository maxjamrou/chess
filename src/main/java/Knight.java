public class Knight extends Pieces {
    public Knight(String couleur) {
        super(couleur, "N", 3, couleur.equals("BLANC") ? "white_knight.png" : "black_knight.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        int dRow = Math.abs(row - selectedRow);
        int dCol = Math.abs(col - selectedCol);

        return ((dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2))
                && (board[row][col] == null || !board[row][col].couleur.equals(this.couleur));
    }
}
