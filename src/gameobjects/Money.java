package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;

public class Money extends GameObject
{
	 private Handler handler;
	 private int value;
	 private Texture tex = Game.getInstance();
	
	public Money(float x, float y, ObjectID id, int health, int maxHealth,
			int velY, Handler handler, int value)
	{
		super(x,y,id, health, maxHealth, handler, 32, 32, 
				ObjectID.Drop);
		this.velY = velY;
		this.handler = handler;
		this.value = value;
	}

	public void tick(LinkedList<GameObject> object) 
	{
		this.y += velY;
		collision(object);
	}

	public void render(Graphics g)
	{
		if(getDisposeState() == false){
			g.drawImage(tex.misc[2], (int) getX(), (int) getY(), null);
		}
	}

	public Rectangle getBounds() 
	{
		if(getDisposeState() == false){
			return new Rectangle ((int) x, (int) y, 16, 16);
		}
		else{
			return new Rectangle((int) x, (int) y, 0, 0);
		}
	}
	
	
	public int getValue()
	{
		return value;
	}
	
	public void collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i <handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ObjectID.Block){
				
				if(getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					x = tempObject.getX();
					y = tempObject.getY() - getHeight();
				}
			}
		}
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}
}