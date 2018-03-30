package epoques;

import java.util.ArrayList;

import bateaux.Bateau;

public interface Epoque {
	public ArrayList<Bateau> creerFlotte();
	public Bateau creerBateau(int type);
}
