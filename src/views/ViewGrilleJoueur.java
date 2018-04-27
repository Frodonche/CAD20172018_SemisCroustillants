package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.ControllerCaseGrilleJoueur;
import controllers.ControllerCaseGrilleTir;
import factory.SpriteInterface;
import modele.Modele;

public class ViewGrilleJoueur extends JPanel implements View{
	private Modele modele;
	private JButton[][] lesBoutons;
	private static int LARGEUR_GRILLE = 10;
	private static int HAUTEUR_GRILLE = 10;
	
	// declaration pour update()
	private String orientation;
	private int taille;
	private int x, y;
	private int xSelect;
	private int ySelect;
	
	
	public ViewGrilleJoueur(Modele modele) {
		this.modele = modele;
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(350,350));
		lesBoutons = new JButton[LARGEUR_GRILLE][HAUTEUR_GRILLE];
		declareGrilleJoueur();
		this.setVisible(false);
	}
	
	/**
	 * Declare la liste de JButton de la grille et leur attribue Image et Controller
	 * Puis les ajoute a la vue
	 */
	public void declareGrilleJoueur() {
		for (int ligne = 0; ligne < 10; ligne++) {
			for (int col = 0; col < 10; col++) {
				lesBoutons[col][ligne] = new JButton(new ImageIcon(SpriteInterface.getInstance().getSprite("Water")));
				lesBoutons[col][ligne].addActionListener(new ControllerCaseGrilleJoueur(modele, col, ligne));
				this.add(lesBoutons[col][ligne]);
			}
		}
	}
	
	@Override
	public void update() {
		if(modele.estEnJeu()) {
			// si on n'a pas fini de placer les bateaux
			if(!modele.bateauxTousPlaces()) {
				
				orientation = modele.getOrientation();
				taille = modele.getTaillePlacement();
				xSelect = modele.getXJoueurSelect();
				ySelect = modele.getYJoueurSelect();
				
				if(orientation == "h") {
					x = 1;
					y = 0;
				}else {
					x = 0;
					y = 1;
				}
				
				// on reset l'affichage des lineBorder
				for(int ligne = 0; ligne < 10; ligne++) {
					for(int col = 0; col < 10; col++) {
						lesBoutons[col][ligne].setBorder(null);
					}
				}
				
				// on calcule l'affichage des lines border
				for(int ligne = 0; ligne < 10; ligne++) {
					for(int col = 0; col < 10; col++) {
						for(int i = 0; i < taille; i++) { // si on est sur la case cliquee, on calcule les autres lineborder a afficher
							if(xSelect == col && ySelect == ligne)	
								lesBoutons[col+(i*x)][ligne+(i*y)].setBorder(new LineBorder(Color.GREEN));
						}
					}
				}

			}
			for(int ligne = 0; ligne < 10; ligne++) {
				for(int col = 0; col < 10; col++) {
					if(modele.estBateau(1, col, ligne)) {
						lesBoutons[col][ligne].setIcon(null);
						lesBoutons[col][ligne].setBorder(new LineBorder(Color.BLACK));
						lesBoutons[col][ligne].setBackground(Color.GREEN);
					}
				}
			}
		}
		
		this.setVisible(modele.estEnJeu());	
	}

}
