package epoques;

import java.util.ArrayList;

import bateaux.*;
import usines.*;

public class EpoqueXXe implements Epoque{
	// private Usine monUsine
	private String name;
	
	public EpoqueXXe(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public ArrayList<Bateau> creerFlotte() {
		ArrayList<Bateau> flotte = new ArrayList();
		Usine usine = new UsineXX();
		flotte.add(usine.formerBateau(2));
		flotte.add(usine.formerBateau(3));
		flotte.add(usine.formerBateau(3));
		flotte.add(usine.formerBateau(4));
		flotte.add(usine.formerBateau(5));
		return flotte;
	}

	@Override
	public Bateau creerBateau(int type) {
		// TODO Auto-generated method stub
		return null;
	}

}
