package objects.relics;

import java.awt.Rectangle;

import framework.ObjectID;
import game.Handler;

public class PegasusBoots extends Relic 
{

	public PegasusBoots(float x, float y, ObjectID id, int health, 
			int maxHealth, Handler handler, int width,
			int height) 
	{
		super(x, y, id, health, maxHealth, handler, width, height);
	}
	
	public void act()
	{
		//Pegasus boots allow you to double jump
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}
}
