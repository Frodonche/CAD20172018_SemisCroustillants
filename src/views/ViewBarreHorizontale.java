package views;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.ControllerBarreHorizontale;
import modele.Modele;

public class ViewBarreHorizontale extends JPanel implements View {
	private Modele modele;
	private JButton tirer;
	
	public ViewBarreHorizontale(Modele modele) {
		this.modele = modele;
		tirer = new JButton("Tirer");
		tirer.addActionListener(new ControllerBarreHorizontale(modele, "Tirer"));
		this.add(tirer);
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
		tirer.setEnabled(modele.getXTirSelect() >= 0); // bouton active si une case est selectionnee
	}

}
