package epoques;

import java.util.ArrayList;

import bateaux.*;
import usines.*;

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
		ArrayList<Bateau> flotte = new ArrayList();
		Usine usine = new UsineXVI();
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

	@Override
	public String toString(){
		return "EpoqueXVIe";
	}

}
