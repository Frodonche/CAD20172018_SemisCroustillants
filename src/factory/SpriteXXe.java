package factory;

import java.awt.image.BufferedImage;

public class SpriteXXe extends SpriteFactory{
	private static SpriteXXe instance = new SpriteXXe();
	
	private SpriteXXe() {
		
	}
	
	public static SpriteXXe getInstance() {
		return instance;
	}

	@Override
	public BufferedImage getSprite(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
