package modele;

import epoques.*;

public class Game {
	Joueur j1; // l'humain
	Joueur j2; // l'IA
	Epoque epoque;
	
	public Game(Epoque epoque) {
		this.epoque = epoque;
		j1 = new Joueur(epoque, 0, false, this); // pour l'instant on laisse la strat à 0
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
	 * @param j
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
	
	/**
	 * Retourne le nombre de munitions pour le bateau de taille t du joueur j
	 * @param j
	 * @param t
	 * @return
	 */
	public int getMunitions(int j, int t) {
		return getJoueur(j).getMunitions(t);
	}
	
	/**
	 * Le joueur j utilise une munition du bateau de taille t
	 * @param j
	 * @param t
	 */
	public void utiliserMunition(int j, int t) {
		getJoueur(j).utiliserMunition(t);
	}
	
	/**
	 * Tirer sur le joueur j aux coordonnees x y
	 * @param j
	 * @param x
	 * @param y
	 */
	public void tirer(int j, int x, int y) {
		getJoueur(j).tirer(x, y);
	}
	
	public boolean estCassee(int j, int x, int y) {
		return getJoueur(j).estCassee(x,y);
	}
	
	/**
	 * Marque la grille du joueur j aux coordonnees x, y comme etant ciblee par un tir
	 * @param j
	 * @param x
	 * @param y
	 */
	public void marquerGrille(int j, int x, int y) {
		getJoueur(j).marquerGrille(x, y);
	}
	
	/**
	 * Retourne vrai si la case de la grille du joueur j a deja ete ciblee par un tir
	 */
	public boolean estMarque(int j, int x, int y) {
		return getJoueur(j).estMarque(x, y);
	}
	
	/**
	 * Defini si le joueur j a gagne en regardant si l'autre joueur a perdu
	 * @param j
	 * @return
	 */
	public boolean aGagne(int j) {
		int adversaire;
		if(j == 1)
			adversaire = 2;
		else
			adversaire = 1;
		return getJoueur(adversaire).aPerdu() && !getJoueur(j).aPerdu();
	}
	
	/**
	 * Met en place les bateaux de l'IA de facon aleatoire
	 */
	public void setBateauxIA() {
		int HAUTEUR_GRILLE = 10;
		int LARGEUR_GRILLE = 10;
		int nbPlace = 0;
		boolean dansLaGrille;
		boolean chevaucheBateau;
		int min = 0;
		int max = 9;
		int x, y, o;
		int taille = 0;
		String orientation;
		
		while(nbPlace < 5) {
			x = min + (int)(Math.random() * ((max - min) + 1));
			y = min + (int)(Math.random() * ((max - min) + 1));
			o = 0 + (int)(Math.random() * ((1 - 0) + 1));
			if(o == 1)
				orientation = "v";
			else
				orientation = "h";
			
			if(nbPlace == 0)
				taille = 2;
			if(nbPlace == 1 || nbPlace == 2) 
				taille = 3;
			if(nbPlace == 3)
				taille = 4;
			if(nbPlace == 4)
				taille = 5;
			
			if(orientation == "v")// si l'orientation courante est verticale
				dansLaGrille =  y + taille - HAUTEUR_GRILLE <= 0;
			else// si l'orientation courante est horizontale
				dansLaGrille =  x + taille - LARGEUR_GRILLE <= 0;
			
			if(dansLaGrille == true) {
				chevaucheBateau = j2.chevaucheBateau(x, y, taille, orientation);
				if(chevaucheBateau == false) {
					j2.placerBateau(taille, x, y, orientation);
					nbPlace += 1;
				}
			}
		}
		
		
	}
	
}
