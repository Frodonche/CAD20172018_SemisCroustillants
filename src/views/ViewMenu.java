package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.ControllerMenu;
import factory.SpriteInterface;
import modele.Modele;

/**
 * 
 * @author guillaume
 * Affiche les images pour le choix des epoques dans le menu principal
 */
public class ViewMenu extends JPanel implements View {
	private Modele modele;
	
	public ViewMenu(Modele modele) {
		this.modele = modele;
		int nbEpoques = modele.getNbEpoques();
		this.setLayout(new GridLayout(1, nbEpoques));
		
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
			monBouton.addActionListener(new ControllerMenu(i, modele));
			monBouton.setVisible(true);
			this.add(monBouton);
		}
		this.setPreferredSize(new Dimension(799, 640));
	}
	
	@Override
	public void update() {
		this.setVisible(!modele.estEnJeu());
		
	}

}
