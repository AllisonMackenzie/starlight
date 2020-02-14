package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import framework.BufferedImageLoader;

public class Paused 
{
	public void render(Graphics g)
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage("/paused.png");
		
		g.drawImage(image, 150, 50, 512, 512, null);
	}

}