package modele;

import java.util.ArrayList;

import epoques.*;
import views.*;

public class Modele {
	Game maGame;
	ArrayList<Epoque> lesEpoques;
	ArrayList<View> lesVues;
	
	public Modele() {
		lesEpoques = new ArrayList<Epoque>();
		lesVues = new ArrayList<View>();
		Epoque tempEp;
		
		// Creation epoque XVIe
		tempEp = new EpoqueXVIe();
		lesEpoques.add(tempEp);
		
		// Creation epoque XXe
		tempEp = new EpoqueXXe();
		lesEpoques.add(tempEp);
	}
	
	/*
	 * setEpoque sera declenche par un l'ActionListener du bouton de l'epoque choisie.
	 */
	public void setEpoque(int choixEpoque) {
		// On recupere l'epoque choisie et on cree la game avec
		Epoque tempEp = lesEpoques.get(choixEpoque);
		maGame = new Game(tempEp);
	}
	
	public void ajouterVue(View vue) {
		lesVues.add(vue);
	}
}
