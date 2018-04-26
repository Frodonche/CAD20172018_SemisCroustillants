package views;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.ControllerBarreHorizontale;
import modele.Modele;

public class ViewBarreHorizontale extends JPanel implements View {
	private Modele modele;
	private JButton tirer;
	private JButton placer;
	
	public ViewBarreHorizontale(Modele modele) {
		this.modele = modele;
		
		tirer = new JButton("Tirer");
		tirer.addActionListener(new ControllerBarreHorizontale(modele, "Tirer"));
		this.add(tirer);
	
		placer = new JButton("Placer");
		placer.addActionListener(new ControllerBarreHorizontale(modele, "Placer"));
		this.add(placer);
		
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
		tirer.setEnabled(modele.getXTirSelect() >= 0 && modele.bateauxTousPlaces()); // bouton actif si une case est selectionnee et que tous les bateaux sont places
		placer.setEnabled(modele.getTaillePlacement() >= 2 && modele.getTaillePlacement() <= 5); // bouton actif si un bouton de taille est selectionne
	}

}
