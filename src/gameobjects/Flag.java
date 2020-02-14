package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;

public class Flag extends GameObject
{
	Texture tex = Game.getInstance();
	private Handler handler;
	private int lvlID;
	
	public Flag(float x, float y, ObjectID id, int health, int maxHealth,
			int velX, int lvlID, Handler handler)
	{
		super(x,y,id, health, maxHealth, handler, 32, 64, ObjectID.Misc);
		this.velX = velX;
		this.handler = handler;
		this.lvlID = lvlID;
	}

	public void tick(LinkedList<GameObject> object) 
	{
	}

	public void render(Graphics g)
	{
		g.drawImage(tex.door[0], (int) getX(), (int) getY(), 
				getWidth(),	getHeight(), null); 
	}

	public Rectangle getBounds() 
	{
		return new Rectangle ((int) x, (int) y, getWidth(), getHeight());
	}
	
	public void collision(LinkedList<GameObject> object)
	{	
	}
	
	public int getlvlID()
	{
		return lvlID;
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}
}