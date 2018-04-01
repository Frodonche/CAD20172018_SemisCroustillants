package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener{
	private int numEpoque; // le numero de l'epoque sur le menu, et aussi dans le modele
	
	public ControllerMenu(int num) {
		numEpoque = num;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Epoque numero "+numEpoque);
	}

}
