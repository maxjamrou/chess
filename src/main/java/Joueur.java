
import java.util.ArrayList;

public class Joueur {
    private final String couleur;

    private ArrayList<Pieces> pieces;
    private ArrayList<Pawn> pions;
    private ArrayList<Bishop> fous;
    private ArrayList<Rook> tours;
    private ArrayList<Knight> cavaliers;
    private ArrayList<Queen> reines;
    private King roi;

    private int points;
    public static final int TOTALPOINTS = 39;
    
    public Joueur(String c){
        couleur = c;
        pieces = new ArrayList<>();
        pions = new ArrayList<>();
        fous = new ArrayList<>();
        tours = new ArrayList<>();
        cavaliers = new ArrayList<>();
        reines = new ArrayList<>();
        points = TOTALPOINTS;
    }

    public String getCouleur(){return this.couleur;}

    public int getPoints(){return this.points;}
    public void setPoints(int v){this.points += v;}

    public ArrayList<Pieces> getPieces(){return this.pieces;}
    public void addPieces(Pieces p){this.pieces.add(p);}

    public ArrayList<Pawn> getPions(){return this.pions;}
    public void addPions(Pawn p){this.pions.add(p);}

    public ArrayList<Bishop> getFous(){return this.fous;}
    public void addFous(Bishop p){this.fous.add(p);}

    public ArrayList<Rook> getTours(){return this.tours;}
    public void addTours(Rook p){this.tours.add(p);}

    public ArrayList<Knight> getCavaliers(){return this.cavaliers;}
    public void addCavaliers(Knight p){this.cavaliers.add(p);}

    public ArrayList<Queen> getReines(){return this.reines;}
    public void addReines(Queen p){this.reines.add(p);}

    public King getRoi(){return this.roi;}
    public void setRoi(King r){this.roi = r;}
}
