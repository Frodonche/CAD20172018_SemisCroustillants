package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.ControllerCaseGrilleTir;
import factory.SpriteInterface;
import modele.Modele;

public class ViewGrilleTir extends JPanel implements View{
	private Modele modele;
	private JButton[][] lesBoutons;
	
	public ViewGrilleTir(Modele modele) {
		this.modele = modele;
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(350,350));
		lesBoutons = new JButton[10][10];
		declareGrilleTir();
		this.setVisible(false);
	}
	
	/**
	 * Declare la liste de JButton de la grille et leur attribue Image et Controller
	 * Puis les ajoute a la vue
	 */
	public void declareGrilleTir() {
		for (int ligne = 0; ligne < 10; ligne++) { // /!\ On n'oublie pas que ligne = y et colonne = x /!\
			for (int col = 0; col < 10; col++) {
				lesBoutons[col][ligne] = new JButton(new ImageIcon(SpriteInterface.getInstance().getSprite("Fog")));
				lesBoutons[col][ligne].addActionListener(new ControllerCaseGrilleTir(modele, col, ligne));
				this.add(lesBoutons[col][ligne]);
			}
		}
	}
	
	@Override
	public void update() {
		if(modele.estEnJeu()) {
			for(int ligne = 0; ligne < 10; ligne++) {
				for(int col = 0; col < 10; col++) {
					if(modele.getXTirSelect() == col && modele.getYTirSelect() == ligne)	
						lesBoutons[col][ligne].setBorder(new LineBorder(Color.RED));
					else
						lesBoutons[col][ligne].setBorder(null);
					
					// si on vient de tirer sur la case
					if(modele.getXDernierTir() == col && modele.getYDernierTir() == ligne)
						lesBoutons[col][ligne].setIcon(new ImageIcon(SpriteInterface.getInstance().getSprite("Water")));
					
					if(modele.estBateau(2, col, ligne))
						lesBoutons[col][ligne].setBorder(new LineBorder(Color.RED));
				}
			}
		}
		this.setVisible(modele.estEnJeu());
	}

}
