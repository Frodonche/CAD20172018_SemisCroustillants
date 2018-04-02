package views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import factory.SpriteInterface;
import modele.Modele;

/**
 * 
 * @author guillaume
 * Affichage l'image de titre du menu
 */
public class ViewImageTitre extends JLabel implements View{
	private Modele modele;
	
	public ViewImageTitre(Modele modele) {
		this.modele = modele;
		
		this.setIcon(new ImageIcon(SpriteInterface.getInstance().getSprite("ImageTitre")));
		//this.setPreferredSize(new Dimension(799, 160));
	}
	
	@Override
	public void update() {
		this.setVisible(!modele.estEnJeu());
	}

}
