
import java.util.ArrayList;

public class Joueur {
    private String couleur;

    private ArrayList<Pieces> pieces;
    private ArrayList<Pieces> pions;
    private ArrayList<Pieces> fous;
    private ArrayList<Pieces> tours;
    private ArrayList<Pieces> cavaliers;
    private ArrayList<Pieces> reines;
    private Pieces roi;

    private int points;
    public static final int TOTALPOINTS = 39;
    
    public Joueur(String c){
        couleur = c;
        pieces = new ArrayList<>();
        points = TOTALPOINTS;
    }

    public String getCouleur(){return this.couleur;}

    public int getPoints(){return this.points;}
    public void setPoints(int v){this.points += v;}

    public ArrayList<Pieces> getPieces(){return this.pieces;}
    public void addPieces(Pieces p){this.pieces.add(p);}

    public ArrayList<Pieces> getPions(){return this.pions;}
    public void addPions(Pieces p){this.pions.add(p);}

    public ArrayList<Pieces> getFous(){return this.fous;}
    public void addFous(Pieces p){this.fous.add(p);}

    public ArrayList<Pieces> getTours(){return this.tours;}
    public void addTours(Pieces p){this.tours.add(p);}

    public ArrayList<Pieces> getCavaliers(){return this.cavaliers;}
    public void addCavaliers(Pieces p){this.cavaliers.add(p);}

    public ArrayList<Pieces> getReines(){return this.reines;}
    public void addReines(Pieces p){this.reines.add(p);}

    public Pieces getRoi(){return this.roi;}
    public void setRoi(Pieces r){this.roi = r;}
}
