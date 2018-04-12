package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import modele.Modele;
import views.ViewEnJeu;
import views.ViewImageTitre;
import views.ViewMenu;

public class Launcher {

	public static void main(String[] args) {
		// Mise en place de la fenetre
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Virtual Battleship");
		fenetre.setSize(800, 785); // la barre de l'appli fait 30px
		
		fenetre.setLocationRelativeTo(null); // pour mettre la fenetre au centre de l'ecran
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		////////////////////////////////
		
		// Declaration du modele
		Modele modele = new Modele();
		
		ViewMenu menu = new ViewMenu(modele);
		ViewImageTitre titre = new ViewImageTitre(modele);
		ViewEnJeu enJeu = new ViewEnJeu(modele);
		
		modele.ajouterVue(menu);	
		modele.ajouterVue(titre);
		modele.ajouterVue(enJeu);
		
		fenetre.add(titre, BorderLayout.NORTH); 
		fenetre.add(menu, BorderLayout.SOUTH);
		fenetre.add(enJeu);
		
		fenetre.setVisible(true);
	}
}
