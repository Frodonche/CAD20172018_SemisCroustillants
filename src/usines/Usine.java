package usines;

import bateaux.*;

public abstract class Usine {
	
	public Bateau formerBateau(int type){
		return this.creerBateau(type);
	}

	public abstract Bateau creerBateau(int type);
	
}