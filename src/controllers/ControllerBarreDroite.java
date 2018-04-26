package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
			modele.setOrientation("v");
		if(nomBouton == "Horizontal")
			modele.setOrientation("h");

		if(nomBouton == "tailleDeux")
			modele.setTaillePlacement(2);
		if(nomBouton == "tailleTrois")
			modele.setTaillePlacement(3);
		if(nomBouton == "tailleQuatre")
			modele.setTaillePlacement(4);
		if(nomBouton == "tailleCinq")
			modele.setTaillePlacement(5);
	}

}
