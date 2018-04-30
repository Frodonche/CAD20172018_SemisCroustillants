package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;

/**
 * 
 * @author guigu
 * Controller pour les boutons de la grille de Tir
 *
 */
public class ControllerCaseGrilleTir implements ActionListener{
	private Modele modele;
	private int x;
	private int y;
	
	public ControllerCaseGrilleTir(Modele modele, int x, int y) {
		this.modele = modele;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Case cliquee : "+ x + " " + y);
		modele.setCoordsTirSelect(x, y);
	}

}
