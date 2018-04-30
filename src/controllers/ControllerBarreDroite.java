package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import modele.Modele;

public class ControllerBarreDroite implements ActionListener{
	private Modele modele;
	private String nomBouton;
	
	public ControllerBarreDroite(Modele modele, String nomBouton) {
		this.modele = modele;
		this.nomBouton = nomBouton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(nomBouton == "Vertical")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), modele.getTaillePlacement(), "v"))
				modele.setOrientation("v");
		if(nomBouton == "Horizontal")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), modele.getTaillePlacement(), "h"))
				modele.setOrientation("h");

		if(nomBouton == "tailleDeux")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), 2, modele.getOrientation()))
				modele.setTaillePlacement(2);
		if(nomBouton == "tailleTrois")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), 3, modele.getOrientation()))
				modele.setTaillePlacement(3);
		if(nomBouton == "tailleQuatre")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), 4, modele.getOrientation()))
				modele.setTaillePlacement(4);
		if(nomBouton == "tailleCinq")
			if(modele.estPlacable(modele.getXJoueurSelect(), modele.getYJoueurSelect(), 5, modele.getOrientation()))
				modele.setTaillePlacement(5);
	}

}
