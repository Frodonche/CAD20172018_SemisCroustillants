package bateaux;

import java.util.ArrayList;

public abstract class Bateau {
	private static int MUNITIONS = 10;

	protected int taille;
	protected int nbMunitions;
	protected ArrayList<Case> cases;
	protected boolean place;

	public Bateau(int t){
		taille = t;
		nbMunitions = MUNITIONS;
		cases = new ArrayList<Case>(taille);
		place = false;
		this.setCases(-1,-1,"h");
	}

	public void setCases(int x, int y, String orientation){
		int vx, vy;
		if(orientation == "v"){
			vx = 0;
			vy = 1;
		}else{
			vx = 1;
			vy = 0;
		}
		for(int i = 0; i < this.taille;i++){
			this.cases.add(new Case(x+i*vx,y+i*vy,1)); //Modifier pour gérer vie
		}
		place = true;
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

	/**
	 * Return true si le bateau possede une case aux coordonnees x y
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean estTouche(int x, int y) {
		if(cases.size() > 0) {
			for(Case c : cases) {
				if(c.getX() == x && c.getY() == y)
					return true;
			}
		}
		return false;
	}

	public int getNbMunitions() {
		return this.nbMunitions;
	}

	public void utiliserMunition() {
		this.nbMunitions -= 1;
	}

	public int getTaille() {
		return taille;
	}

	public boolean estPlace() {
		return this.place;
	}
}
