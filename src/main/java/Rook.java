public class Rook extends Pieces {
    public Rook(String couleur, int x, int y) {
        super(couleur, "R", 5, x, y, couleur.equals("BLANC") ? "white_rook.png" : "black_rook.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        if (selectedRow != row && selectedCol != col) return false;

        if (selectedRow == row) {
            int step = (col > selectedCol) ? 1 : -1;
            for (int c = selectedCol + step; c != col; c += step) {
                if (board[row][c] != null) return false;
            }
        } else {
            int step = (row > selectedRow) ? 1 : -1;
            for (int r = selectedRow + step; r != row; r += step) {
                if (board[r][col] != null) return false;
            }
        }

        return board[row][col] == null || !board[row][col].couleur.equals(this.couleur);
    }
}
