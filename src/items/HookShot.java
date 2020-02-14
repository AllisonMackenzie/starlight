package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.MouseInput;
import gameobjects.Player;

public class HookShot extends Item
{
	private Handler handler;
	private static MouseInput mouse = Game.getMouse();
	private static Player thePlayer = Game.getThePlayer();
	
	public HookShot(float x, float y, ObjectID id, int health, int maxHealth,
			Handler handler) {
		super(x, y, id, health, maxHealth, handler, 32, 32);
	}
	

	public void tick(LinkedList<GameObject> object) 
	{
		// TODO Auto-generated method stub
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.MAGENTA);
		g.drawLine((int) x, (int) y, 
				(int) thePlayer.getX(),
				(int) thePlayer.getY() + mouse.getYPos());
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void collision(LinkedList<GameObject> object) 
	{
		// TODO Auto-generated method stub
		
	}

	public Rectangle getSightBounds() 
	{
		return null;
	}
}
