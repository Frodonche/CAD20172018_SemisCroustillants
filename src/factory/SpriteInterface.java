package factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteInterface extends SpriteFactory{
	private static SpriteInterface instance = new SpriteInterface();
	private static BufferedImage epoqueXVIe;
	private static BufferedImage epoqueXXe;
	private static BufferedImage imageTitre;
	private static BufferedImage water;
	private static BufferedImage fog;
	
	private SpriteInterface() {
		try {
			epoqueXVIe = ImageIO.read(new File("images/EpoqueXVIeMenu.jpeg"));
			imageTitre = ImageIO.read(new File("images/ImageTitre.jpeg"));
			epoqueXXe = ImageIO.read(new File("images/EpoqueXXeMenu.jpg"));
			water = ImageIO.read(new File("images/water.png"));
			fog = ImageIO.read(new File("images/fog.png"));
		} catch (IOException e) {
			System.out.println("Erreur chargement image");
			e.printStackTrace();
		}
	}
	
	public static SpriteInterface getInstance() {
		return instance;
	}
	
	@Override
	public BufferedImage getSprite(String type) {
		if(type == "EpoqueXVIe")
			return epoqueXVIe;
		if(type == "EpoqueXXe")
			return epoqueXXe;
		if(type == "ImageTitre")
			return imageTitre;
		if(type == "Water")
			return water;
		if(type == "Fog")
			return fog;
		return null;
	}

}
