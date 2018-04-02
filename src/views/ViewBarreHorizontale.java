package views;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import modele.Modele;

public class ViewBarreHorizontale extends JPanel implements View {
	private Modele modele;
	
	public ViewBarreHorizontale(Modele modele) {
		this.modele = modele;
		JButton tirer = new JButton("Tirer");
		this.add(tirer);
		//this.setPreferredSize(new Dimension(400, 40));
	}
	
	@Override
	public void update() {
		this.setVisible(modele.estEnJeu());
	}

}
