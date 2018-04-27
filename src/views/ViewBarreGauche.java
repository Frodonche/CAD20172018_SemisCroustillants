package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ControllerBarreGauche;
import modele.Modele;

/**
 * 
 * @author guigu
 * La barre d'interface situee a gauche de l'ecran
 *
 */
public class ViewBarreGauche extends JPanel implements View {
	private Modele modele;
	
	private JButton bateauT2;
	private JButton bateauT3;
	private JButton bateauT4;
	private JButton bateauT5;
	
	private boolean tousPlaces;
	
	public ViewBarreGauche(Modele modele) {
		this.modele = modele;
		this.setPreferredSize(new Dimension(220, 750));
		//this.setBackground(Color.BLACK);
		
		this.tousPlaces = false;
		
		this.setLayout(new GridLayout(6, 1));
		
		JLabel placerTexte = new JLabel("Tirer avec bateau");
		
		bateauT2 = new JButton("Bateau T2");
		bateauT3 = new JButton("Bateau T3");
		bateauT4 = new JButton("Bateau T4");
		bateauT5 = new JButton("Bateau T5");
		
		this.add(placerTexte);
		this.add(bateauT2);
		this.add(bateauT3);
		this.add(bateauT4);
		this.add(bateauT5);
		
		bateauT2.addActionListener(new ControllerBarreGauche(modele, "T2"));
		bateauT3.addActionListener(new ControllerBarreGauche(modele, "T3"));
		bateauT4.addActionListener(new ControllerBarreGauche(modele, "T4"));
		bateauT5.addActionListener(new ControllerBarreGauche(modele, "T5"));
	}
	
	@Override
	public void update() {
		if(modele.estEnJeu()) {
			tousPlaces = modele.bateauxTousPlaces();
			bateauT2.setEnabled(tousPlaces && modele.getMunitions(1, 2) > 0 && modele.getTailleBateauTir() != 2);
			bateauT3.setEnabled(tousPlaces && modele.getMunitions(1, 3) > 0 && modele.getTailleBateauTir() != 3);
			bateauT4.setEnabled(tousPlaces && modele.getMunitions(1, 4) > 0 && modele.getTailleBateauTir() != 4);
			bateauT5.setEnabled(tousPlaces && modele.getMunitions(1, 5) > 0 && modele.getTailleBateauTir() != 5);
			
			bateauT2.setText("Bateau T2 ("+modele.getMunitions(1, 2)+")");
			bateauT3.setText("Bateau T3 ("+modele.getMunitions(1, 3)+")");
			bateauT4.setText("Bateau T4 ("+modele.getMunitions(1, 4)+")");
			bateauT5.setText("Bateau T5 ("+modele.getMunitions(1, 5)+")");
			
			
		}
		this.setVisible(modele.estEnJeu());
	}

}
