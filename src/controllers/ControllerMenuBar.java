package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

public class ControllerMenuBar implements ActionListener{
	private Modele modele;
	private String nomBouton;
	
	public ControllerMenuBar(Modele modele, String nomBouton) {
		this.modele = modele;
		this.nomBouton = nomBouton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(this.nomBouton == "Save")
			modele.save();
		
		//if(this.nomBouton == "Load")
			//modele.load();
	}

}
