package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;

public class Heart extends GameObject 
{
	private Handler handler;
	private Texture tex = Game.getInstance();

	public Heart(float x, float y, ObjectID id, int health, int maxHealth,
			Handler handler)
	{
		super(x, y, id, health, maxHealth, handler, 16, 16, 
				ObjectID.Drop);
		this.handler = handler;
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
			g.drawImage(tex.misc[0], (int) getX(), (int) getY(), null);
		}
		
	}

	public Rectangle getBounds() 
	{
		if(getDisposeState() == false){
			return new Rectangle((int) x, (int) y, 16, 16);
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
