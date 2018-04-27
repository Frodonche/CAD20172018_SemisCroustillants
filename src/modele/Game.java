package modele;

import epoques.*;
import bateaux.Bateau;
import java.util.ArrayList;

public class Game {
	Joueur j1; // l'humain
	Joueur j2; // l'IA
	Epoque epoque;
	
	public Game(Epoque epoque) {
		this.epoque = epoque;
		j1 = new Joueur(epoque, 0, false,this); // pour l'instant on laisse la strat à 0
		j2 = new Joueur(epoque, 0, true, this);
	}

	public Epoque getEpoque(){
		return this.epoque;
	}

	public Joueur getJoueur(int i){
		if(i == 1){
			return j1;
		}
		if(i == 2){
			return j2;
		}
		return null;
	}
	
	/**
	 * Defini les coordoonees sur bateau numero taille du joueur j
	 * @param joueur
	 * @param taille
	 * @param x
	 * @param y
	 * @param orientation
	 */
	public void placerBateau(int j, int taille, int x, int y, String orientation) {
		this.getJoueur(j).placerBateau(taille, x, y , orientation); 
	}

	/**
	 * Defnini si le joueur j possede un bateau aux coordonnees x y 
	 */
	public boolean estBateau(int j, int x, int y) {
		return getJoueur(j).estBateau(x, y);
	}
	
}
