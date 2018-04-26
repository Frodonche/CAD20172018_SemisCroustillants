package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import factory.SpriteInterface;
import modele.Modele;

/**
 * 
 * @author guigu
 * Controller pour les boutons de la grille de Tir
 *
 */
public class ControllerCaseGrilleJoueur implements ActionListener{
	private Modele modele;
	private int x;
	private int y;
	
	public ControllerCaseGrilleJoueur(Modele modele, int x, int y) {
		this.modele = modele;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Case cliquee : "+ x + " " + y);
		if(modele.estPlacable(x, y))
			modele.setCoordJoueurSelect(x, y);
		modele.update();
	}

}
