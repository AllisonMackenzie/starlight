package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;

public class HeartPiece extends GameObject
{
	private Handler handler;
	private Texture tex = Game.getInstance();
	private boolean isCollected = false;
	private int heartID;

	public HeartPiece(float x, float y, ObjectID id, int health, 
			int maxHealth, Handler handler, int heartID) 
	{
		super(x, y, id, health, maxHealth, handler, 32, 32, 
				ObjectID.Collectable);
		this.handler = handler;
		this.heartID = heartID;
	}
	
	public int getHeartID()
	{
		return heartID;
	}
	
	public void setCollected()
	{
		isCollected = true;
	}
	public boolean getCollectedState()
	{
		return isCollected;
	}

	public void tick(LinkedList<GameObject> object)
	{
		velY = 1;
		this.y += velY;
		collision(object);		
	}

	public void render(Graphics g) 
	{
		if(getDisposeState() == false){
			g.drawImage(tex.misc[1], (int) getX(), (int) getY(), null);
		}
		
	}

	public Rectangle getBounds() 
	{
		if(getDisposeState() == false){
			return new Rectangle((int) x, (int) y, 32, 32);
		}
		else{
			return new Rectangle((int) x, (int) y, 0, 0);
		}
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
