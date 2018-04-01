package factory;

import java.awt.image.BufferedImage;

public abstract class SpriteFactory {
	public abstract BufferedImage getSprite(String type);
}
