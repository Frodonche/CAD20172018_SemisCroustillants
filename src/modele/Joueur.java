package modele;

import java.util.ArrayList;

import bateaux.Bateau;
import epoques.Epoque;

public class Joueur {
	int[][] grille; // grille de tir du joueur. 0 : vierge, 1 : dans l'eau, 2 : touche, 3 : coule
	ArrayList<Bateau> flotte;
	Game game;
	//Strategy strat;
	boolean IA; // true si IA, false si humain
	
	public Joueur(Epoque epoque, int strat, boolean IA, Game g) {
		grille = new int[10][10];
		this.game = g;
		this.setFlotte();
		this.IA = IA;
		// {constructeur de strategie avec le param strat}
		
	}
	
	private void setFlotte() {
		ArrayList<Bateau> flotte = this.game.getEpoque().creerFlotte();
		System.out.println(flotte);
	}
}
