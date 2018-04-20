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
	private char orientationPlacement; // v ou h : pour le placement des bateaux
	
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
		
		// TODO
		
		// Maj des coordonnees du dernier tir
		this.xDernierTir = this.xTirSelect;
		this.yDernierTir = this.yTirSelect;
		
		update();
	}
	
	public int getXDernierTir() {
		return this.xDernierTir;
	}
	
	public int getYDernierTir() {
		return this.yDernierTir;
	}
	
	/**
	 * Met a jour toutes les vues connues par le modele
	 */
	public void update() {
		for (View v : lesVues)
			v.update();
	}
}
