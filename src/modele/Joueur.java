package modele;

import java.util.ArrayList;

import bateaux.Bateau;
import epoques.Epoque;

public class Joueur {
	int[][] grille; // grille de tir du joueur. 0 : vierge, 1 : dans l'eau, 2 : touche, 3 : coule
	ArrayList<Bateau> flotte;
	Game game;
	// Strategy strat;
	boolean IA; // true si IA, false si humain

	public Joueur(Epoque epoque, int strat, boolean IA, Game g) {
		grille = new int[10][10];
		this.game = g;
		this.setFlotte();
		this.IA = IA;
		// {constructeur de strategie avec le param strat}
	}

	private void setFlotte() {
		ArrayList<Bateau> f = this.game.getEpoque().creerFlotte();
		this.flotte = f;
	}

	private void setFlotte(String s) {

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
