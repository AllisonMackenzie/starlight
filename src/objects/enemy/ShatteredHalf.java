package objects.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Handler;

public class ShatteredHalf extends Enemy 
{
	private Handler handler;
	private String description;

	public ShatteredHalf(float x, float y, ObjectID id, 
			int health, int maxHealth, int knockback, Handler handler)
	{
		super(x, y, id, health, maxHealth, knockback, handler, 32, 64,
				90);
		this.handler = handler;
		description = "A demonic entity who tore her personality in two."
				+ " All that remains is the evil from within her.";
	}

	@Override
	public void tick(LinkedList<GameObject> object) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collision(LinkedList<GameObject> object) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public String getDescription()
	{
		return description;
	}

	public Rectangle getSightBounds() 
	{
		return null;
	}
}
