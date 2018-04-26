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
			modele.tirer();
		}
		if(nomBouton == "Placer") {
			modele.placerBateau();
		}
	}

}