package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import modele.Modele;

/**
 * 
 * @author guigu
 * La barre d'interface situee a droite de l'ecran
 *
 */
public class ViewBarreDroite extends JPanel implements View {
	private Modele modele;
	
	public ViewBarreDroite(Modele modele) {
		this.modele = modele;
		this.setPreferredSize(new Dimension(190, 750));
		//this.setBackground(Color.BLACK);
		
		
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
	}

}
