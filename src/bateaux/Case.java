package bateaux;

public class Case{
	
	int x, y, vie;

	public Case(int x, int y, int vie){
		this.x = x;
		this.y = y;
		this. vie = vie;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void perdreVie() {
		vie -= 1;
	}
	
	public int getVie() {
		return vie;
	}

	public void setVie(int v){
		this.vie = v;
	}
	
	@Override
	public String toString(){
		return x+"-"+y+"-"+vie;
	}
}
