package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.ControllerCaseGrilleTir;
import factory.SpriteInterface;
import modele.Modele;

public class ViewGrilleTir extends JPanel implements View{
	private Modele modele;
	
	public ViewGrilleTir(Modele modele) {
		this.modele = modele;
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(400,400));
		declareGrilleTir();
		this.setVisible(false);
	}
	
	/**
	 * Declare la liste de JButton de la grille et leur attribue Image et Controller
	 * Puis les ajoute a la vue
	 */
	public void declareGrilleTir() {
		JButton tempButton;
		for (int ligne = 0; ligne < 10; ligne++) {
			for (int col = 0; col < 10; col++) {
				tempButton = new JButton(new ImageIcon(SpriteInterface.getInstance().getSprite("Fog")));
				tempButton.addActionListener(new ControllerCaseGrilleTir(modele, tempButton, ligne, col));
				this.add(tempButton);
			}
		}
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
	}

}
