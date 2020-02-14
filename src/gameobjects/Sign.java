package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;

public class Sign extends GameObject 
{
	private Handler handler;
	private String message = null;
	private boolean touching = false;
	private Texture tex = Game.getInstance();

	public Sign(float x, float y, ObjectID id, int health, int maxHealth, 
			Handler handler, String message)
	{
		super(x, y, id, health, maxHealth, handler, 32, 32,
				ObjectID.Misc);
		this.handler = handler;
		this.message = message;
	}

	public void tick(LinkedList<GameObject> object) 
	{		
	}

	public void render(Graphics g) 
	{
		g.drawImage(tex.block[5], (int) x, (int) y, null);
		g.setColor(Color.BLACK);
		g.drawString(getMessage(), (int) x, (int) y - 8);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void collision(LinkedList<GameObject> object) 
	{
	}
	
	public boolean getTouching()
	{
		return touching;
	}
	
	public void setTouching(boolean touching)
	{
		this.touching = touching;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}

}
