package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Handler;

public class Slope extends GameObject implements SlopeInterface
{
	
	//http://higherorderfun.com/
	//blog/2012/05/20/the-guide-to-implementing-2d-platformers/
	
	private float centerX = getWidth() / 2;
	private float tileX = getX();
	private float tileSize = getWidth();
	private float leftFloorY;
	private float rightFloorY;
	
	private float t = (centerX - tileX) / tileSize;
	private float floorY = (1-t) * leftFloorY + t * rightFloorY;

	public Slope(float x, float y, ObjectID id, int health, int maxHealth, Handler handler, int width, int height,
			ObjectID id2) 
	{
		super(x, y, id, health, maxHealth, handler, width, height, id2);
	}
	


	public void tick(LinkedList<GameObject> object) 
	{
		
	}

	public void render(Graphics g) 
	{
		
	}

	public Rectangle getBounds() 
	{
		return null;
	}

	public void collision(LinkedList<GameObject> object) 
	{

		
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}

}
