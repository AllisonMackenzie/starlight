package game;

import java.awt.Color;
import java.awt.Graphics;

public class MainMenu 
{
	public void render(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.drawString("Hey", 50, 50);
	}

}
