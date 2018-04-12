package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
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
		this.setPreferredSize(new Dimension(220, 750));
		//this.setBackground(Color.BLACK);
		
		this.setLayout(new GridLayout(2, 1));
		
		JPanel vueHaut = new JPanel();
		vueHaut.setLayout(new GridLayout(5, 1));
		
		JPanel vueBas = new JPanel();
		vueBas.setLayout(new GridLayout(4, 1));
		
		JLabel placerTexte = new JLabel("Placer bateaux");
		JButton tailleDeux = new JButton("Taille 2");
		JButton tailleTrois = new JButton("Taille 3");
		JButton tailleQuatre = new JButton("Taille 4");
		JButton tailleCinq = new JButton("Taille 5");
		
		JLabel orientation = new JLabel("Orientation");
		JButton vertical = new JButton("Vertical");
		JButton horizontal = new JButton("Horizontal");
		
		vueHaut.add(placerTexte);
		vueHaut.add(tailleDeux);
		vueHaut.add(tailleTrois);
		vueHaut.add(tailleQuatre);
		vueHaut.add(tailleCinq);
		
		vueBas.add(new JLabel());
		
		vueBas.add(orientation);
		vueBas.add(vertical);
		vueBas.add(horizontal);
		
		this.add(vueHaut);
		this.add(vueBas);
		
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
	}

}
