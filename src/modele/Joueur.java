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

	public Joueur(Epoque e, int strat, boolean IA, Game g, ArrayList<Bateau> f){
		grille = new int[10][10];
		this.game = g;
		this.flotte = f;
		this.IA = IA;
	}

	private void setFlotte() {
		ArrayList<Bateau> f= this.game.getEpoque().creerFlotte();
		this.flotte = f;
	}

	public int[][] getGrille(){
		return grille;
	}

	public ArrayList<Bateau> getFlotte(){
		return flotte;
	}

	public String toStringFlotte(){
		StringBuilder s = new StringBuilder(24);
		int i;
		for(i = 0; i < 5; i++){
		 	s.append(flotte.get(i).toString()+"/");
		}
		return s.toString();
	}

	// @Override
	// public String toString(){


	// }
}
