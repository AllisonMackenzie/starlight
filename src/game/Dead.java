package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.BufferedImageLoader;

public class Dead 
{
	public void render(Graphics g)
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage("/img_youAreDead.png");
		
		g.drawImage(image, 150, 50, 512, 512, null);
	}

}