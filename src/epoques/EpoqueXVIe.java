package epoques;

import java.util.ArrayList;

import bateaux.Bateau;

public class EpoqueXVIe implements Epoque{
	//private Usine monUsine
	private String name;
	
	public EpoqueXVIe(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public ArrayList<Bateau> creerFlotte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bateau creerBateau(int type) {
		// TODO Auto-generated method stub
		return null;
	}

}