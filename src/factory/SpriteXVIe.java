package factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteXVIe extends SpriteFactory{
	private static SpriteXVIe instance = new SpriteXVIe();
	private static BufferedImage tailleDeux; // fregate
	private static BufferedImage tailleTrois; // caravelle
	private static BufferedImage tailleQuatre; // galion
	private static BufferedImage tailleCinq; // caraque
	
	private SpriteXVIe() {
		try {
			tailleDeux = ImageIO.read(new File("fregate.jpeg"));
			tailleTrois = ImageIO.read(new File("caravelle.jpeg"));
			tailleQuatre = ImageIO.read(new File("galion.jpeg"));
			tailleCinq = ImageIO.read(new File("caraque.jpeg"));
		}catch(IOException e){
			
		}
	}
	
	public static SpriteXVIe getInstance() {
		return instance;
	}
	
	@Override
	public BufferedImage getSprite(String type) {
		if(type == "tailleDeux")
			return tailleDeux;
		if(type == "tailleTrois")
			return tailleTrois;
		if(type == "tailleQuatre")
			return tailleQuatre;
		if(type == "tailleCinq")
			return tailleCinq;
		return null;
	}

}
