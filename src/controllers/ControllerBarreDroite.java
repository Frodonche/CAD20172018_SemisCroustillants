package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Modele;

public class ControllerBarreDroite implements ActionListener{
	private Modele modele;
	private JButton self;
	private String nomBouton;
	
	public ControllerBarreDroite(Modele modele, JButton self, String nomBouton) {
		this.modele = modele;
		this.self = self;
		this.nomBouton = nomBouton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
