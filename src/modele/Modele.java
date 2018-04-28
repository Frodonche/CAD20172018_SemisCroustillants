package modele;

import java.util.ArrayList;

import epoques.*;
import views.View;

public class Modele {
	private static int LARGEUR_GRILLE = 10;
	private static int HAUTEUR_GRILLE = 10;
	
	private Game maGame;
	private ArrayList<Epoque> lesEpoques;
	private ArrayList<View> lesVues;
	private boolean enJeu;
	private Epoque epoqueChoisie;
	
	// pour le placement des bateaux
	private String orientationPlacement; // v ou h (vertical ou horizontal)
	private int taillePlacement;
	private int nbTailleDeuxPlaces;
	private int nbTailleTroisPlaces;
	private int nbTailleQuatrePlaces;
	private int nbTailleCinqPlaces;
	
	// coordonnees de la case selectionnee dans le grille du joueur
	private int xJoueurSelect;
	private int yJoueurSelect;
	
	// les coordonnees de la case selectionnee dans la grille de tir
	private int xTirSelect;
	private int yTirSelect;
	
	private int xDernierTir;
	private int yDernierTir;
	
	/**
	 * Constructeur du modele
	 */
	public Modele() {
		lesEpoques = new ArrayList<Epoque>();
		lesVues = new ArrayList<View>();
		Epoque tempEp;
		enJeu = false; // initialement dans le menu, donc pas en jeu
		
		orientationPlacement = "v";
		taillePlacement = 0;
		nbTailleDeuxPlaces = 0;
		nbTailleTroisPlaces = 0;
		nbTailleQuatrePlaces = 0;
		nbTailleCinqPlaces = 0;
		
		// Creation epoque XVIe
		tempEp = new EpoqueXVIe("XVIe siecle");
		lesEpoques.add(tempEp);
		
		// Creation epoque XXe
		tempEp = new EpoqueXXe("XXe siecle");
		lesEpoques.add(tempEp);
		
		xTirSelect = -1;
		yTirSelect = -1;
		
		xDernierTir = -2;
		yDernierTir = -2;

	}
	
	/*
	 * setEpoque sera declenche par un l'ActionListener du bouton de l'epoque choisie.
	 */
	public void setEpoque(int choixEpoque) {
		// On recupere l'epoque choisie et on cree la game avec
		Epoque tempEp = lesEpoques.get(choixEpoque);
		epoqueChoisie = tempEp; // on garde une trace de l'epoque en cours
		maGame = new Game(tempEp);
	}
	
	public Epoque getEpoqueChoisie() {
		return this.epoqueChoisie;
	}
	
	/*
	 * Ajout des vues
	 */
	public void ajouterVue(View vue) {
		lesVues.add(vue);
	}
	
	/**
	 * @return le nombre d'epoques = celles stockees dans le modele
	 */
	public int getNbEpoques() {
		return lesEpoques.size();
	}
	
	/**
	 * @return la valeur du booleen pour savoir si on est en jeu ou dans le menu
	 */
	public boolean estEnJeu() {
		return this.enJeu;
	}
	
	/**
	 * @param bool 
	 * Fait varier enJeu pour passer du menu a l'ecran de jeu
	 */
	public void setEnJeu(boolean bool) {
		this.enJeu = bool;
	}

	public void save(){
		GameDAO gdao = new GameDAO("Save");
		System.out.println(maGame.getJoueur(1).toStringFlotte());
		gdao.serialize(maGame);
		gdao.saveToXML();
	}


	public void load(){
		GameDAO gdao = new GameDAO("Save");
		gdao.create("Save");
	}
	
	/**
	 * Oriente le bateau lors du placement au debut
	 * @param orientation
	 */
	public void setOrientation(String orientation) {
		this.orientationPlacement = orientation;
		update();
	}
	
	public String getOrientation() {
		return this.orientationPlacement;
	}
	
	/**
	 * Defini si oui ou non un bateau est placable sur la case cliquee en fonction
	 * de son orientation et de des coordonees
	 * (pour l'instant on verifie seulement qu'on ne sort pas de la grille
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean estPlacable(int x, int y, int taille, String orientation) {
		boolean dansLaGrille;
		boolean chevaucheUnAutreBateau;
		
		if(orientation == "v") { // si l'orientation courante est verticale
			dansLaGrille =  y + taille - HAUTEUR_GRILLE <= 0;
		}else { // si l'orientation courante est horizontale
			dansLaGrille =  x + taille - LARGEUR_GRILLE <= 0;
		}
		
		//chevaucheUnAutreBateau = this.maGame.getJoueur(1).chevaucheBateau(x, y, taille, orientation);
		
		return dansLaGrille;// && !chevaucheUnAutreBateau;
	}
	
	public int getXTirSelect() {
		return this.xTirSelect;
	}
	
	public int getYTirSelect() {
		return this.yTirSelect;
	}
	
	/**
	 * Enregistre les coordonnees de tir correspondant a la case cliquee par le joueur
	 * @param x
	 * @param y
	 */
	public void setCoordsTirSelect(int x, int y) {
		this.xTirSelect = x;
		this.yTirSelect = y;
		update();
	}
	
	public int getXJoueurSelect() {
		return this.xJoueurSelect;
	}
	
	public int getYJoueurSelect() {
		return this.yJoueurSelect;
	}
	
	/**
	 * Enregistre les coordonnees de tir correspondant a la case cliquee par le joueur
	 * @param x
	 * @param y
	 */
	public void setCoordJoueurSelect(int x, int y) {
		this.xJoueurSelect = x;
		this.yJoueurSelect = y;
		
		update();
	}
	
	public void tirer() {
		
		// Maj des coordonnees du dernier tir
		this.xDernierTir = this.xTirSelect;
		this.yDernierTir = this.yTirSelect;
		
		// TODO
		
		// On reset le tir courant
		this.xTirSelect = -1;
		this.yTirSelect = -1;
		
		update();
	}
	
	public int getXDernierTir() {
		return this.xDernierTir;
	}
	
	public int getYDernierTir() {
		return this.yDernierTir;
	}
	
	/**
	 * 
	 * @return la taille de bateau a placer selectionnee
	 */
	public int getTaillePlacement() {
		return this.taillePlacement;
	}
	
	/**
	 * 
	 * @param t la taille de bateau a placer selectionnee
	 */
	public void setTaillePlacement(int t) {
		this.taillePlacement = t;
		update();
	}
	
	/**
	 * 
	 * @param x la taille de bateau
	 * @return return le nombre de bateaux de taille x places
	 */
	public int getNbTailleXPlaces(int x) {
		int result;
		switch(x) {
		case 2:
			result = this.nbTailleDeuxPlaces;
			break;
		case 3:
			result = this.nbTailleTroisPlaces;
			break;
		case 4:
			result = this.nbTailleQuatrePlaces;
			break;
		case 5:
			result = this.nbTailleCinqPlaces;
			break;
		default:
			result =  0;
			break;
		}
		return result;
	}
	
	/**
	 * Ajoute un au nombre de bateaux de taille x
	 * @param x la taille de bateau
	 */
	public void ajouterNbTailleXPlaces(int x) {
		switch(x) {
		case 2:
			this.nbTailleDeuxPlaces += 1;
			break;
		case 3:
			this.nbTailleTroisPlaces += 1;
			break;
		case 4:
			this.nbTailleQuatrePlaces += 1;
			break;
		case 5:
			this.nbTailleCinqPlaces += 1;
			break;
		default:
			break;
		}
		
		update();
	}
	
	
	/**
	 * Place un bateau de taille taillePlacement a la colonne xPlacementSelect et la ligne yPlacementSelect
	 * Methode pour les bateaux de joueur uniquement
	 */
	public void placerBateauJoueur() {
		this.maGame.placerBateau(1, taillePlacement, this.xJoueurSelect, this.yJoueurSelect, this.orientationPlacement);

		ajouterNbTailleXPlaces(this.taillePlacement);
		
		// une fois le bateau place, on reset la taille de bateau selectionnee
		setTaillePlacement(0);
		
		update();
	}
	
	/**
	 * Defini si un bateau du joueur j se trouve sur les coordonnees x, y
	 * @param j
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean estBateau(int j, int x, int y) {
		return this.maGame.estBateau(j, x, y);
	}
	
	/**
	 * 
	 * @return true si tous les bateaux sont places, false sinon
	 */
	public boolean bateauxTousPlaces() {
		return nbTailleDeuxPlaces == 1
				&& nbTailleTroisPlaces == 2
				&& nbTailleQuatrePlaces == 1
				&& nbTailleCinqPlaces == 1;
	}
	
	/**
	 * Met a jour toutes les vues connues par le modele
	 */
	public void update() {
		for (View v : lesVues)
			v.update();
	}

}
