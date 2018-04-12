package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.ControllerCaseGrilleTir;
import factory.SpriteInterface;
import modele.Modele;

public class ViewGrilleJoueur extends JPanel implements View{
	private Modele modele;
	
	public ViewGrilleJoueur(Modele modele) {
		this.modele = modele;
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(350,350));
		declareGrilleJoueur();
		this.setVisible(false);
	}
	
	/**
	 * Declare la liste de JButton de la grille et leur attribue Image et Controller
	 * Puis les ajoute a la vue
	 */
	public void declareGrilleJoueur() {
		JButton tempButton;
		for (int ligne = 0; ligne < 10; ligne++) {
			for (int col = 0; col < 10; col++) {
				tempButton = new JButton(new ImageIcon(SpriteInterface.getInstance().getSprite("Water")));
				//tempButton.addActionListener(new ControllerCaseGrilleJoueur(modele, tempButton, ligne, col));
				this.add(tempButton);
			}
		}
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());	
	}

}
