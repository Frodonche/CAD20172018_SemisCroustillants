package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

public class ControllerMenu implements ActionListener{
	private int numEpoque; // le numero de l'epoque sur le menu, et aussi dans le modele
	private Modele modele;
	
	public ControllerMenu(int num, Modele modele) {
		numEpoque = num;
		this.modele = modele;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		modele.setEnJeu(true);
		modele.setEpoque(numEpoque);
		System.out.println("Epoque choisie : "+modele.getEpoqueChoisie().getName());
		modele.update();
	}

}
