public class Queen extends Pieces {
    public Queen(String couleur, int x, int y) {
        super(couleur, "Q", 9, "Reine", x, y, couleur.equals("BLANC") ? "white_queen.png" : "black_queen.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        Rook rook = new Rook(this.couleur, x, y);
        Bishop bishop = new Bishop(this.couleur, x, y);

        return rook.isValidMove(selectedRow, selectedCol, row, col, board)
                || bishop.isValidMove(selectedRow, selectedCol, row, col, board);
    }
}

