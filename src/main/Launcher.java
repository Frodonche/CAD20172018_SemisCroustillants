package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.ControllerMenu;
import factory.SpriteInterface;
import modele.Modele;
import views.ViewImageTitre;
import views.ViewMenu;

public class Launcher {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Virtual Battleship");
		fenetre.setSize(800, 800);
		
		fenetre.setLocationRelativeTo(null); // pour mettre la fenetre au centre de l'ecran
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Declaration du modele
		Modele modele = new Modele();
		
		int nbEpoques = modele.getNbEpoques();
		
		ViewMenu menu = new ViewMenu();
		menu.setLayout(new GridLayout(1, nbEpoques));
		
		JButton monBouton;
		String argButton;
		for(int i = 0; i < nbEpoques; i++) {
			switch(i) {
			case 0:
				argButton = "EpoqueXVIe";
				break;
			case 1:
				argButton = "EpoqueXXe";
				break;
			default:
				argButton = "Default";
			}
			monBouton = new JButton(new ImageIcon(SpriteInterface.getInstance().getSprite(argButton)));
			monBouton.addActionListener(new ControllerMenu(i));
			monBouton.setVisible(true);
			menu.add(monBouton);
		}
		
		ViewImageTitre titre = new ViewImageTitre();
		
		titre.setIcon(new ImageIcon(SpriteInterface.getInstance().getSprite("ImageTitre")));
		titre.setPreferredSize(new Dimension(799, 240));
		
		modele.ajouterVue(titre);
		modele.ajouterVue(menu);	
		
		fenetre.add(titre, BorderLayout.NORTH); 
		fenetre.add(menu, BorderLayout.SOUTH);
		fenetre.setVisible(true);
	}
}
