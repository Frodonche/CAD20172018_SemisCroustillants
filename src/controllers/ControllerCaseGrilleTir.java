package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import factory.SpriteInterface;
import modele.Modele;

/**
 * 
 * @author guigu
 * Controller pour les boutons de la grille de Tir
 *
 */
public class ControllerCaseGrilleTir implements ActionListener{
	private Modele modele;
	private JButton self;
	private int x;
	private int y;
	
	public ControllerCaseGrilleTir(Modele modele, JButton self, int x, int y) {
		this.modele = modele;
		this.x = x;
		this.y = y;
		this.self = self;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Case cliquee : "+ x + " " + y);
		self.setIcon(new ImageIcon(SpriteInterface.getInstance().getSprite("Water")));
		modele.update();
		modele.save(); // /!\ A RETIRER /!\
	}

}
