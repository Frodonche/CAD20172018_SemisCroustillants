package usines;

import bateaux.*;

public class UsineXVI extends Usine{

	public Bateau creerBateau(int type){

		Bateau bateau = null;

		switch(type){
			case 2 : bateau = new Fregate();break;
			case 3 : bateau = new Caravelle();break;
			case 4 : bateau = new Gallion(); break;
			case 5 : bateau = new Caraque();break;
		}
		return bateau;
	}

}