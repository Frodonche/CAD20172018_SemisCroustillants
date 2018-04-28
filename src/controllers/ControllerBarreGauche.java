package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

public class ControllerBarreGauche implements ActionListener{
	private Modele modele;
	private String nomBouton;
	
	public ControllerBarreGauche(Modele modele, String nomBouton) {
		this.modele = modele;
		this.nomBouton = nomBouton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(nomBouton == "T2")
			modele.setTailleBateauTir(2);
		if(nomBouton == "T3")
			modele.setTailleBateauTir(3);
		if(nomBouton == "T4")
			modele.setTailleBateauTir(4);
		if(nomBouton == "T5")
			modele.setTailleBateauTir(5);
	}

}
