package objects.relics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Handler;

public abstract class Relic extends GameObject
{

	public Relic(float x, float y, ObjectID id, 
			int health, int maxHealth, Handler handler, 
			int width, int height) 
	{
		super(x, y, id, health, maxHealth, handler, width, height, 
				ObjectID.Relic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collision(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void act();

}
