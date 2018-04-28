package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.ControllerMenuBar;
import modele.Modele;

public class ViewMenuBar extends JMenuBar implements View{
	private Modele modele;
	JMenu actions;
	JMenuItem save;
	JMenuItem load;
	
	public ViewMenuBar(Modele modele) {
		this.modele = modele;
		
		actions = new JMenu("Actions");
		save = new JMenuItem("Save");
		save.addActionListener(new ControllerMenuBar(this.modele, "Save"));
		
		load = new JMenuItem("Load");
		load.addActionListener(new ControllerMenuBar(this.modele, "Load"));
		
		actions.add(save);
		actions.add(load);
		
		this.add(actions);
	}
	
	@Override
	public void update() {
		save.setEnabled(modele.estEnJeu());
		load.setEnabled(modele.estEnJeu());
	}

}
