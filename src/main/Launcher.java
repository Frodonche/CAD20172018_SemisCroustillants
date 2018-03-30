package main;

import javax.swing.JFrame;

import modele.Modele;

public class Launcher {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Virtual Battleship");
		fenetre.setSize(1280, 720);
		
		fenetre.setLocationRelativeTo(null); // pour mettre la fenetre au centre de l'ecran
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		
		// Declaration du modele
		Modele modele = new Modele();
		
	}
}
