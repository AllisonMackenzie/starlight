package items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.BufferedImageLoader;
import framework.GameObject;
import framework.ObjectID;
import game.Handler;

public class Arrow extends GameObject
{
	private int count = 0;
	private Handler handler;
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	public Arrow(float x, float y, ObjectID id, int health, int maxHealth,
			int velX, Handler handler)
	{
		super(x,y,id, health, maxHealth, handler, 16, 16, 
				ObjectID.Item);
		this.velX = velX;
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) 
	{
		x += velX * 1.5;
		
		if(count < 300){
			count++;
			collision(object);
		}
		else if(count >= 300){
			dispose();
		}
	}

	public void render(Graphics g)
	{
		if(getDisposeState() == false){
			BufferedImage image = loader.loadImage("/img_arrow.png");
			
			g.drawImage(image, (int) x, (int) y, 32, 32, null);
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
	
	public void collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 50;
					dispose();
				}
			}
		}
		
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}
}
