public class Queen extends Pieces {
    public Queen(String couleur) {
        super(couleur, "Q", 9, couleur.equals("BLANC") ? "white_queen.png" : "black_queen.png");
    }

    @Override
    public boolean isValidMove(int selectedRow, int selectedCol, int row, int col, Pieces[][] board) {
        Rook rook = new Rook(this.couleur);
        Bishop bishop = new Bishop(this.couleur);

        return rook.isValidMove(selectedRow, selectedCol, row, col, board)
                || bishop.isValidMove(selectedRow, selectedCol, row, col, board);
    }
}

