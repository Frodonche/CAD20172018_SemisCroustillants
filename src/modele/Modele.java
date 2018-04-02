package modele;

import java.util.ArrayList;

import epoques.*;
import views.*;

public class Modele {
	private Game maGame;
	private ArrayList<Epoque> lesEpoques;
	private ArrayList<View> lesVues;
	private boolean enJeu;
	private Epoque epoqueChoisie;
	
	/**
	 * Constructeur du modele
	 */
	public Modele() {
		lesEpoques = new ArrayList<Epoque>();
		lesVues = new ArrayList<View>();
		Epoque tempEp;
		enJeu = false; // initialement dans le menu, donc pas en jeu
		
		// Creation epoque XVIe
		tempEp = new EpoqueXVIe("XVIe siecle");
		lesEpoques.add(tempEp);
		
		// Creation epoque XXe
		tempEp = new EpoqueXXe("XXe siecle");
		lesEpoques.add(tempEp);
		
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
	
	
	/**
	 * Met a jour toutes les vues connues par le modele
	 */
	public void update() {
		for (View v : lesVues)
			v.update();
	}
}
