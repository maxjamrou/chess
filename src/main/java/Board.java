import java.awt.Color;
import java.awt.Graphics;

public class Board {
    public static final int TILE_SIZE = 100;
    private Pieces[][] board;
    private final Joueur JB;
    private final Joueur JN;

    public Board(int rows, int cols) {
        board = new Pieces[rows][cols];
        JB = new Joueur("BLANC");
        JN = new Joueur("NOIR");
    }

    // Initialize pieces on the board
    public void initializeBoard() {
        // Pawns
        Pawn p;
        for (int i = 0; i < 8; i++) {
            p = new Pawn("BLANC", 1, i);
            JB.addPieces(p);
            JB.addPions(p);
            board[1][i] = p;  // white pawns
            p = new Pawn("NOIR", 6, i);
            JN.addPieces(p);
            JN.addPions(p);
            board[6][i] = p; // black pawns
        }

        // Rooks
        Rook r;

        r = new Rook("BLANC", 0, 0);
        JB.addPieces(r);
        JB.addTours(r);
        board[0][0] = r;

        r = new Rook("BLANC", 0, 7);
        JB.addPieces(r);
        JB.addTours(r);
        board[0][7] = r;

        r = new Rook("NOIR", 7, 0);
        JN.addPieces(r);
        JN.addTours(r);
        board[7][0] = r;

        r = new Rook("NOIR", 7, 7);
        JN.addPieces(r);
        JN.addTours(r);
        board[7][7] = r;

        // Knights
        Knight k;

        k = new Knight("BLANC", 0, 1);
        JB.addPieces(k);
        JB.addCavaliers(k);
        board[0][1] = k;

        k = new Knight("BLANC", 0, 6);
        JB.addPieces(k);
        JB.addCavaliers(k);
        board[0][6] = k;
        
        k = new Knight("NOIR", 7, 1);
        JN.addPieces(k);
        JN.addCavaliers(k);
        board[7][1] = k;
        
        k = new Knight("NOIR", 7, 6);
        JN.addPieces(k);
        JN.addCavaliers(k);
        board[7][6] = k;

        // Bishops
        Bishop b;

        b = new Bishop("BLANC", 0, 2);
        JB.addPieces(b);
        JB.addFous(b);
        board[0][2] = b;

        b = new Bishop("BLANC", 0, 5);
        JB.addPieces(b);
        JB.addFous(b);
        board[0][5] = b;

        b = new Bishop("NOIR", 7, 2);
        JN.addPieces(b);
        JN.addFous(b);
        board[7][2] = b;

        b = new Bishop("NOIR", 7, 5);
        JN.addPieces(b);
        JN.addFous(b);
        board[7][5] = b;

        // Queens
        Queen q;

        q = new Queen("BLANC", 0, 3);
        JB.addPieces(q);
        JB.addReines(q);
        board[0][3] = q;

        q = new Queen("NOIR", 7, 3);
        JN.addPieces(q);
        JN.addReines(q);
        board[7][3] = q;

        // Kings
        King king;
        king = new King("BLANC", 0, 4);
        JB.addPieces(king);
        JB.setRoi(king);
        board[0][4] = king;

        king = new King("NOIR", 7, 4);
        JN.addPieces(king);
        JN.setRoi(king);
        board[7][4] = king;

        // Empty squares remain null
    }

    // Draw the board and pieces
    public void draw(Graphics g) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // Determine tile color
                Color tileColor = (row + col) % 2 == 0 ? Color.BLUE : Color.GRAY;
                g.setColor(tileColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Draw piece if exists
                // draw piece image
                if (board[row][col] != null && board[row][col].getImage() != null) {
                    g.drawImage(board[row][col].getImage(), col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    // Getter for board array
    public Pieces[][] getBoard() {
        return board;
    }
    public void setBoard(Pieces[][] board) {
        this.board = board;
    }

    public Joueur getJoueurBlanc(){return this.JB;}

    public Joueur getJoueurNoir(){return this.JN;}
}
