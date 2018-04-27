package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

public class ControllerBarreHorizontale implements ActionListener{
	private Modele modele;
	private String nomBouton;
	
	public ControllerBarreHorizontale(Modele modele, String nomBouton) {
		this.modele = modele;
		this.nomBouton = nomBouton;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(nomBouton == "Tirer") {
			modele.tirer(1);
		}
		if(nomBouton == "Placer" && modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), modele.getTaillePlacement(), modele.getOrientation())) {
			modele.placerBateauJoueur();
		}
	}

}