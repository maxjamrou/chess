import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePlay extends JPanel {
    public static final int WIDTH = 800, HEIGHT = 800;
    boolean pieceSelected = false;
    int selectedRow = -1, selectedCol = -1;
    boolean gameOver = false;
    
    Board board = new Board(8, 8);
    // white starts

    public GamePlay() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        board.initializeBoard();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(pieceSelected);
                long timestart = System.currentTimeMillis();
                int col = e.getX() / Board.TILE_SIZE;
                int row = e.getY() / Board.TILE_SIZE;
                
                if (board.getBoard()[row][col] != null && board.getBoard()[row][col].couleur.equals(board.getCurrentPlayer().getCouleur())) {
                    selectedRow = row;
                    selectedCol = col;
                    pieceSelected = true;
                }
                if(pieceSelected){
                    if (isValidMoveGeneral(selectedRow, selectedCol, row, col)) {
                        if(movePiece(selectedRow, selectedCol, row, col)){
                            Pieces piece = board.getBoard()[row][col];
                            if(piece instanceof Pawn){
                                Pawn pawn = (Pawn) piece;
                                pawn.notFirstMove();
                            }
                            // Player = !Player;
                            board.switchPlayer();
                            check();
                            pieceSelected = false;
                            selectedRow = -1;
                            selectedCol = -1;
                        }
    
                        if (isGameOver()) gameOver = true;
                    }
                }
            repaint();
            System.out.println("Temps pris après avoir cliqué : " + (System.currentTimeMillis() - timestart));
        }
    });
                
    }

    private boolean movePiece(int sRow, int sCol, int eRow, int eCol) {
        Pieces piece = board.getBoard()[sRow][sCol];
        Pieces newPiece = board.getBoard()[eRow][eCol];
        board.getBoard()[eRow][eCol] = board.getBoard()[sRow][sCol];
        board.getBoard()[sRow][sCol] = null;
        Pieces piecePlayer = board.getCurrentPlayer().getPieces().get((board.getCurrentPlayer().getPieces().indexOf(piece)));
        System.out.println("X:" + piecePlayer.getX() + ", Y:" + piecePlayer.getY());
        piecePlayer.setX(eRow);
        piecePlayer.setY(eCol);
        System.out.println("X:" + piecePlayer.getX() + ", Y:" + piecePlayer.getY());

        Joueur player2 = null;
        Pieces pieceTaken = null;
        if(newPiece!=null){
            player2 = board.getOtherPlayer();
            pieceTaken = player2.getPieces().remove((player2.getPieces().indexOf(newPiece)));
        }
        if(check()){
            board.getBoard()[sRow][sCol] = piece;
            board.getBoard()[eRow][eCol] = newPiece;
            if(newPiece!=null) player2.getPieces().add(pieceTaken);
            piecePlayer.setX(sRow);
            piecePlayer.setY(sCol);
            System.out.println("X:" + piecePlayer.getX() + ", Y:" + piecePlayer.getY());
            System.out.println("invalide move ---makes check");
            return false;
        }
        return true;
    }

    private boolean isValidMoveGeneral(int sRow, int sCol, int eRow, int eCol) {
        if(board.getBoard()[sRow][sCol] == null) return false;
        Pieces p = board.getBoard()[sRow][sCol];
        if (board.getBoard()[eRow][eCol] != null && board.getBoard()[eRow][eCol].couleur.equals(board.getCurrentPlayer().getCouleur()))
            return false;
        return p.isValidMove(sRow, sCol, eRow, eCol, board.getBoard());
    }


    /*
    * Optimisations possibles :
    * - Sauvegarder la position des deux rois à tout moment
    *       -> Puisqu'il s'agit de la pièce la plus importante aux échecs, garder en mémoire sa position pour la checker plus tard 
    *          est une bonne idée. De plus, cette pièce est unique aux deux joueurs.
    *       -> Cela évitera de devoir traverser toute la matrice board à chaque fois 
    *          qu'on souhaite retrouver le roi pour voir s'il est en échec.
    * - Le roi est forcément existant dans l'échiquier (même sur une partie finie), 
    *   ça ne sert probablement à rien de checker si le roi n'est pas sur le plateau
    * - Au lieu de checker tout le plateau pour regarder si le roi est en échec, on remarque que le roi est en échec seulement 
    *   dans 5 conditions :
    *       > Par une pièce qui l'attaque sur sa colonne            (reines/tours)
    *       > Par une pièce qui l'attaque sur sa ligne              (reines/tours)
    *       > Par une pièce qui l'attaque sur la diagonale 1        (fous/pions)
    *       > Par une pièce qui l'attaque sur la diagonale 2        (fous/pions)
    *       > Par un cavalier qui l'attaque sur 1 des 8 L possibles
    *           -> De ce fait, au lieu de checker toutes les cases dans le cas d'un échec, on peut envoyer un raybeam
    *              Le raybeam regarde par rapport à l'axe correspondant les dangers potentiels
    *                   -> S'il y a un obstacle (une des pièces du roi/une pièce adverse qui ne met pas en échec), il n'y a pas de problèmes
    *                   -> Sinon, si la menace principale sur l'axe est présente (e.g. reines/tours sur les colonnes/lignes)
    *                      renvoie qu'il y a échec
    *           -> Par la même occasion, on regarde s'il y a un cavalier adverse sur un des 8 L autour du roi
    *              Si oui, on renvoie qu'il y a échec
    */
    private boolean check() {
        King king = board.getCurrentPlayer().getRoi();
        int kRow = king.getX(), kCol = king.getY();
        king.resetImage();
        
        boolean inCheck = false;
        Joueur Player2 = board.getOtherPlayer();
        for(Pieces p : Player2.getPieces()){
            if (p.isValidMove(p.getX(), p.getY(), kRow, kCol, board.getBoard())) {
                inCheck = true;
                System.out.println("Check by " + p.getSymbol() + " at (" + p.getX() + "," + p.getY() + ")");
            }
        }

        // for (int r = 0; r < board.getBoard().length; r++)
        //     for (int c = 0; c < board.getBoard()[r].length; c++) {
        //         Pieces p = board.getBoard()[r][c];
        //         if (p != null && !p.couleur.equals(Player.getCouleur())) {
        //             if (p.isValidMove(r, c, kRow, kCol, board.getBoard())) {
        //                 inCheck = true;
        //                 System.out.println("Check by " + p.getSymbol() + " at (" + r + "," + c + ")");
        //             }
        //         }
        //     }
        if (inCheck) king.setCheckImage();
        return inCheck;
    }

    private boolean isGameOver() {
        for (int r = 0; r < board.getBoard().length; r++)
            for (int c = 0; c < board.getBoard()[r].length; c++) {
                Pieces p = board.getBoard()[r][c];
                if (p instanceof King && p.couleur.equals(board.getCurrentPlayer().getCouleur())) return false;
            }
        System.out.println("gameover");
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 20, 20);
        }
    }

}

