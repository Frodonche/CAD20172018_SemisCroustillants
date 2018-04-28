package modele;

import java.util.ArrayList;

import bateaux.Bateau;
import bateaux.Case;
import epoques.Epoque;

import javax.annotation.processing.SupportedSourceVersion;

public class Joueur {
	private static int LARGEUR_GRILLE = 10;
	private static int HAUTEUR_GRILLE = 10;
	
	private int[][] grille; // 1 si le joueur adverse a deja tire sur la case. 0 sinon
	private ArrayList<Bateau> flotte;
	private Game game;
	// Strategy strat;
	private boolean IA; // true si IA, false si humain

	public Joueur(Epoque epoque, int strat, boolean IA, Game g) {
		grille = new int[HAUTEUR_GRILLE][LARGEUR_GRILLE];
		initGrille();
		this.game = g;
		this.setFlotte();
		this.IA = IA;
		// {constructeur de strategie avec le param strat}
	}

	private void setFlotte() {
		ArrayList<Bateau> f = this.game.getEpoque().creerFlotte();
		this.flotte = f;
	}
	
	/**
	 * Initialise la grille en mettant toutes ses cases a 0
	 */
	public void initGrille() {
		for(int i = 0; i < HAUTEUR_GRILLE; i++) {
			for(int j = 0; j < LARGEUR_GRILLE; j++) {
				grille[i][j] = 0;
			}
		}
	}
	
	/**
	 * Marque les coordonnees col, ligne de la grille comme ayant deja ete ciblee
	 */
	public void marquerGrille(int col, int ligne) {
		grille[ligne][col] = 1;
	}
	
	/**
	 * Retourne vrai si la case col ligne de la grille a deja ete ciblee
	 * @param col
	 * @param ligne
	 * @return
	 */
	public boolean estMarque(int col, int ligne) {
		return grille[ligne][col] == 1;
	}

	public int[][] getGrille() {
		return grille;
	}

	public ArrayList<Bateau> getFlotte() {
		return flotte;
	}

	public String toStringFlotte() {
		StringBuilder s = new StringBuilder(30);
		int i;
		for (i = 0; i < 5; i++) {
			s.append(flotte.get(i).toString() + "_");
		}
		return s.toString();
	}

	/**
	 * Defini les coordonnees du bateau de la taille en question
	 * 
	 * @param taille
	 * @param x
	 * @param y
	 * @param orientation
	 */
	public void placerBateau(int taille, int x, int y, String orientation) {
		int cpt = 0;
		boolean trouve = false;
		while (trouve == false && cpt < 5) {
			if (flotte.get(cpt).getTaille() == taille && !flotte.get(cpt).estPlace()) {
				trouve = true;
				getFlotte().get(cpt).setCases(x, y, orientation);
			}
			cpt++;
		}

	}

	public void chargerBateau(String s){
        System.out.println(s);
        String[] cases = s.split(":");

        int taille = cases.length-1;
        for(Bateau b : flotte){
            if(b.getTaille()==taille && !b.estPlace()){
                
                b.setMunitions(Integer.valueOf(cases[0]));

                String orientation = "h";

                String[] var = cases[1].split("-");

                int x = Integer.valueOf(var[0]);
                int y = Integer.valueOf(var[1]);


                int temp = Integer.valueOf(var[0]);
                int comp;

                for(int i = 1; i<cases.length;i++){
                    comp = temp;
                    var = cases[i].split("-");
                    temp = Integer.valueOf(var[0]);
                    if(comp == temp){
                        orientation = "v";
                    }
                }

                b.setCases(x,y,orientation);

                for(Case c : b.getCases()){
                    for(int j = 1; j < cases.length;j++){
                        String[] variables = cases[j].split("-");
                        c.setVie(Integer.valueOf(variables[2]));
                    }
                }
            }
        }
    }

	/**
	 * Defini si un bateau est situe aux coordonnees x y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean estBateau(int x, int y) {
		for (Bateau b : flotte) {
			if (b.estTouche(x, y))
				return true;
		}
		return false;
	}

	/**
	 * Verifie si le bateau que l'on veut placer va chevaucher ou non un autre
	 * bateau
	 * 
	 * @param x
	 * @param y
	 * @param taille
	 * @param orientation
	 * @return
	 */
	public boolean chevaucheBateau(int x, int y, int taille, String orientation) {
		int vx, vy;
		if (orientation == "h") {
			vx = 1;
			vy = 0;
		} else {
			vx = 0;
			vy = 1;
		}
		for (int i = 0; i < taille; i++) {
			for (Bateau b : flotte) {
				if (b.estTouche(x + i * vx, y + i * vy)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getMunitions(int taille) {
		int cpt = 0;
		int result = -1;
		boolean trouve = false;
		while (trouve == false && cpt < 5) {
			if (flotte.get(cpt).getTaille() == taille) {
				trouve = true;
				result = flotte.get(cpt).getNbMunitions();
			}
			cpt++;
		}
		return result;
	}
	
	
	public boolean estCassee(int x, int y) {
		int cpt = 0;
		while (cpt < 5) {
			if(flotte.get(cpt).estCassee(x,y))
				return true;
			cpt++;
		}
		return false;	
	}
	
	
	public void utiliserMunition(int taille) {
		int cpt = 0;
		int result = -1;
		while (cpt < 5) {
			if (flotte.get(cpt).getTaille() == taille) {
				flotte.get(cpt).utiliserMunition();
			}
			cpt++;
		}
	}

//	public boolean estCassee()
	
	/**
	 * Verifie si le tir touche un bateau. Lui fait perdre de la vie le cas echeant
	 * @param x
	 * @param y
	 */
	public void tirer(int x, int y) {
		for(Bateau b : flotte) {
			b.tirer(x, y);
				
		}
	}
	
	/**
	 * Regarde si chaque bateau est detruit. Si oui, le joueur a perdu
	 * @return
	 */
	public boolean aPerdu() {
		boolean perdu = true;
		for(Bateau b : flotte) {
			if(b.estDetruit() == false)
				perdu = false;
		}
		return perdu;
	}
}
