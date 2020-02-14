package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.KeyInput;

public class LevelSelectMenu 
{
	private Handler handler = Game.getGameHandler();

	private KeyInput num = Game.getKeyInput();
	
	private boolean error = false;
	
	public void render(Graphics g)
	{
		Font font0 = new Font("arial", Font.BOLD, 50);
		Font font1 = new Font("arial", Font.ITALIC, 20);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Enter a room ID to load",
				+ Game.WIDTH/5, 100);
		
		g.drawRect(Game.WIDTH/3 + 85, Game.HEIGHT/2 - 150, 120, 50);
		g.drawString(num.getEntered(), Game.WIDTH/2 - 45, 200);
		
		g.setFont(font1);
			
		if(error == true){
			g.drawString("Error: Enter a valid room ID"
					+ " (Currently: 1-4)", 
					Game.WIDTH/5 + 80, 250);
		}
		else{
			g.dispose();
		}
		

	}
	
	public void setError()
	{
		error = true;
	}
	
	
	public void setFine()
	{
		error = false;
	}
}
