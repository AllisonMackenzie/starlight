package game;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * width) - (width),
				(row * height) - (height) , width, height);
		return img;                                          
	}
	
	public BufferedImage grabImage2(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * width) - (width)/2,
				(row * height) - (height) , width, height);
		return img;
	}

}
