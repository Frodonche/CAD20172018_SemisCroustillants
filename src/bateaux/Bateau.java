package bateaux;

import java.util.ArrayList;

public abstract class Bateau {
	
	protected int taille;
	protected int nbMunitions;
	protected ArrayList<Case> cases;

	public Bateau(int t){
		taille = t;
		cases = new ArrayList(taille);
		this.setCases(0,0,0); //A SUPPRIMER
	}

	public void setCases(int x, int y, int orientation){
		int vx, vy;
		if(orientation == 0){	
			vx = 0;
			vy = 1;
		}else{
			vx = 1;
			vy = 0;
		}
		for(int i = 0; i < this.taille;i++){
			this.cases.add(new Case(x+i*vx,y+i*vy,1)); //Modifier pour gérer vie
		}
	}

	@Override
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.nbMunitions + ":");
        for (int i = 0; i < this.taille; i++) {
            s.append(cases.get(i).toString() + ":");
        }
		return s.toString();
	}
}
