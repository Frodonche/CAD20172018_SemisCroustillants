package modele;

import epoques.Epoque;

public class Game {
	Joueur j1; // l'humain
	Joueur j2; // l'IA
	Epoque epoque;
	
	public Game(Epoque epoque) {
		this.epoque = epoque;
		j1 = new Joueur(epoque, 0, false); // pour l'instant on laisse la strat à 0
		j2 = new Joueur(epoque, 0, true);
	}
}
