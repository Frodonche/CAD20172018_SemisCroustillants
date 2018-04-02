package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import modele.Modele;

public class ViewEnJeu extends JPanel implements View{
	private Modele modele;
	
	public ViewEnJeu(Modele modele) {
		this.modele = modele;
		
		ViewGrilleTir grilleTir = new ViewGrilleTir(modele);
		ViewBarreDroite barreDroite = new ViewBarreDroite(modele);
		ViewBarreGauche barreGauche = new ViewBarreGauche(modele);
		ViewGrilleJoueur grilleJoueur = new ViewGrilleJoueur(modele);
		ViewBarreHorizontale barreHori = new ViewBarreHorizontale(modele);
		
		modele.ajouterVue(grilleTir);
		modele.ajouterVue(barreDroite);
		modele.ajouterVue(barreGauche);
		modele.ajouterVue(grilleJoueur);
		modele.ajouterVue(barreHori);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
			
		this.setLayout(new GridBagLayout());
		
		
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		this.add(grilleTir,c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 1;
		this.add(barreHori, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 2;
		this.add(grilleJoueur, c);		
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		this.add(barreDroite, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		this.add(barreGauche, c);
		
		this.setVisible(false);
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
	}

}
