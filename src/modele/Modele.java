package modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	
	private int tailleBateauTir;
	
	private int gagnant; // 0 : personne,  1: j1, 2 : j2
	
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
		
		tailleBateauTir = 0;

	}
	
	/*
	 * setEpoque sera declenche par un l'ActionListener du bouton de l'epoque choisie.
	 */
	public void setEpoque(int choixEpoque) {
		// On recupere l'epoque choisie et on cree la game avec
		Epoque tempEp = lesEpoques.get(choixEpoque);
		epoqueChoisie = tempEp; // on garde une trace de l'epoque en cours
		maGame = new Game(tempEp);
		
		// mise en place des bateaux de l'IA de facon aleatoire
		maGame.setBateauxIA();
		
		update();
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
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Choisissez un nom de sauvegarde", "Sauvegarde", JOptionPane.QUESTION_MESSAGE);
	    if(nom == null || nom.length() < 1)
	    	nom = "default";
		
		GameDAO gdao = new GameDAO(nom);
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
		
		chevaucheUnAutreBateau = this.maGame.getJoueur(1).chevaucheBateau(x, y, taille, orientation);
		
		return dansLaGrille && !chevaucheUnAutreBateau;
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
	
	/**
	 * Le joueur j tire
	 * @param j
	 */
	public void tirer(int j) {
		int tireur = j;
		int victime;
		if(tireur == 1)
			victime = 2;
		else
			victime = 1;
		
		// Maj des coordonnees du dernier tir
		this.xDernierTir = this.xTirSelect;
		this.yDernierTir = this.yTirSelect;
		
		maGame.tirer(victime, xDernierTir, yDernierTir);
		maGame.utiliserMunition(tireur, tailleBateauTir);
		
		maGame.marquerGrille(victime, this.xDernierTir, this.yDernierTir);
		
		// On reset le tir courant
		this.xTirSelect = -1;
		this.yTirSelect = -1;
		
		this.tailleBateauTir = 0;
		
		update();
		
		// on verifie si J1 gagne sur le tir courant
		if(aGagne(1))
			this.gagnant = 1;
		
		// Si le joueur vient de tirer, l'IA tire a un endroit aleatoire ou elle n'a pas deja tire
		if(tireur == 1 && gagnant != 1) {
			int x, y;
			boolean estMarque = true;
			
			while(estMarque) {
				x = 0 + (int)(Math.random() * ((LARGEUR_GRILLE-1 - 0) + 1));
				y = 0 + (int)(Math.random() * ((HAUTEUR_GRILLE-1 - 0) + 1));
				estMarque = this.estMarque(1, x, y);
				if(estMarque == false) {
					xTirSelect = x;
					yTirSelect = y;
					tirer(2);
				}
			}
			// on verifie si J2 (l'IA) gagne sur le tir courant
			if(aGagne(2))
				this.gagnant = 2;
		}
		
		if(estTermine())
			messageFin();
		
	}
	
	public int getXDernierTir() {
		return this.xDernierTir;
	}
	
	public int getYDernierTir() {
		return this.yDernierTir;
	}
	
	/**
	 * Retourne vrai si la case de la grille du joueur j a deja ete ciblee par un tir
	 * @param j
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean estMarque(int j, int x, int y) {
		if(x >= 0 && x < LARGEUR_GRILLE && y >= 0 && y < HAUTEUR_GRILLE)
			return maGame.estMarque(j, x, y);
		return false;
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
		// et les coords selecitonnees
		setTaillePlacement(0);
		setCoordJoueurSelect(-1, -1);
		
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
	
	/*
	 * Defini si la case du joueur j correspond a un bateau et est touchee
	 */
	public boolean estCassee(int j, int x, int y) {
		return this.maGame.estCassee(j, x, y);
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
	 * Retourne le nombre de munitions pour le bateau de taille t pour le joueur j
	 * @param j
	 * @param taille
	 * @return
	 */
	public int getMunitions(int j, int t){
		int result = maGame.getMunitions(j, t);
		if(t == 3) // on n'oublie pas qu'on a 2 bateau de taille 3
			result *= 2;
		return result;
	}
	
	/**
	 * Retourne la taille du bateau selectionne pour tirer
	 * @return
	 */
	public int getTailleBateauTir() {
		return tailleBateauTir;
	}
	
	public void setTailleBateauTir(int taille) {
		this.tailleBateauTir = taille;
		update();
	}
	
	/**
	 * Defini si oui ou non le joueur j a gagne
	 * @param j
	 * @return
	 */
	public boolean aGagne(int j) {
		return maGame.aGagne(j);
	}
	
	/**
	 * retourne le numero du joueur gagnant (ou 0 si personne n'a encore gagne)
	 * @return
	 */
	public int getGagnant() {
		return this.gagnant;
	}
	
	/**
	 * le jeu est termine si il y a un gagnant
	 * @return
	 */
	public boolean estTermine() {
		return this.gagnant > 0;
	}
	
	/**
	 * Fait apparaître une boite de dialogue pour rejouer si la partie est terminee
	 */
	public void messageFin() {
		String message;
		if(getGagnant() == 1)
			message = "Felicitations, vous avez gagne !";
		else
			message = "Dommage, vous avez perdu...";
		
		 JOptionPane jop = new JOptionPane();    
		 jop.showMessageDialog(null, message, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	/**
	 * Met a jour toutes les vues connues par le modele
	 */
	public void update() {
		for (View v : lesVues)
			v.update();
	}

}
