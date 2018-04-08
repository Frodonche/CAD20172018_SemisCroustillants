package usines;

import bateaux.*;

public class UsineXX extends Usine{

	public Bateau creerBateau(int type){

		Bateau bateau = null;

		switch(type){
			case 2 : bateau = new SsMarin();break;
			case 3 : bateau = new Torpilleur();break;
			case 4 : bateau = new PorteAvion(); break;
			case 5 : bateau = new Croiseur();break;
		}
		return bateau;
	}

}