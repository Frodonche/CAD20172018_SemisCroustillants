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

	@Override
	public String toString(){
		return x+"-"+y+"-"+vie;
	}
}
