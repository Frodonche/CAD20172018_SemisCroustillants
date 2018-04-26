package modele;

import java.util.ArrayList;

import epoques.*;
import views.View;

public class Modele {
	private Game maGame;
	private ArrayList<Epoque> lesEpoques;
	private ArrayList<View> lesVues;
	private boolean enJeu;
	private Epoque epoqueChoisie;
	
	// pour le placement des bateaux
	private char orientationPlacement; // v ou h (vertical ou horizontal)
	private int taillePlacement;
	private int nbTailleDeuxPlaces;
	private int nbTailleTroisPlaces;
	private int nbTailleQuatrePlaces;
	private int nbTailleCinqPlaces;
	
	private int xPlacementSelect;
	private int yPlacementSelect;
	
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
		
		orientationPlacement = 'v';
		taillePlacement = 0;
		nbTailleDeuxPlaces = 0;
		nbTailleTroisPlaces = 0;
		nbTailleQuatrePlaces = 0;
		nbTailleCinqPlaces = 0;
		
		xPlacementSelect = -1;
		yPlacementSelect = -1;
		
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
		gdao.serialize(maGame);
		gdao.saveToXML();
	}
	
	/**
	 * Oriente le bateau lors du placement au debut
	 * @param orientation
	 */
	public void setOrientation(char orientation) {
		this.orientationPlacement = orientation;
		update();
	}
	
	public char getOrientation() {
		return this.orientationPlacement;
	}
	
	
	public int getXTirSelect() {
		return this.xTirSelect;
	}
	
	public int getYTirSelect() {
		return this.yTirSelect;
	}
	
	public void setCoordsTirSelect(int x, int y) {
		this.xTirSelect = x;
		this.yTirSelect = y;
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
	 * Selectionne une case pour placer un bateau
	 * @param x
	 * @param y
	 */
	public void selectionnerCasePlacement(int x, int y) {
		this.xPlacementSelect = x;
		this.yPlacementSelect = y;
		
		update();
	}
	
	/**
	 * Place un bateau de taille taillePlacement a la colonne xPlacementSelect et la ligne yPlacementSelect
	 */
	public void placerBateau() {
		
		// TODO
		
		ajouterNbTailleXPlaces(this.taillePlacement);
		
		// une fois le bateau place, on reset la taille de bateau selectionnee
		setTaillePlacement(0);
		
		update();
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
