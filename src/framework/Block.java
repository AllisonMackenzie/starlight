package framework;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Game;
import game.Handler;
import game.Texture;

public class Block extends GameObject
{
	Texture tex = Game.getInstance();
	private int type;
	
	/**
	 * Constructor for class: Block
	 * @param x passed to superClass: GameObject
	 * @param y passed to superClass: GameObject
	 * @param type
	 * @param id passed to superClass: GameObject
	 */
	public Block(float x, float y, int type, ObjectID id, int health, 
			int maxHealth, Handler handler, int width, int height)
	{
		super(x, y, id, health, maxHealth, handler, width, height, 
				ObjectID.Terrain);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) 
	{
		
	}

	public void render(Graphics g) 
	{
		//Grass Block
		if(type == 0){ 
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		
		//Dirt Block
		if(type == 1){
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		}
		
		//Gravel Block
		if(type == 2){
			g.drawImage(tex.block[2], (int) x, (int) y, null);
		}
		
		if(type == 3){
			g.drawImage(tex.block[3], (int) x, (int) y, null);
		}
		
		if(type == 4){
			g.drawImage(tex.block[4], (int) x, (int) y, null);
		}
		
		//Furniture: Desk
		if(type == 5){
			g.drawImage(tex.furniture[0], (int) x, (int) y, null);
		}
		
		//Grass Block Right
		if(type == 6){
			g.drawImage(tex.block[6], (int) x, (int) y, null);
		}
		
		//Grass Block Left
		if(type == 7){
			g.drawImage(tex.block[7], (int) x, (int) y, null);
		}
		
		//Grass block down
		if(type == 8){
			g.drawImage(tex.block[8], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, getWidth(), getHeight());
	}
	
	public void collision(LinkedList<GameObject> object)
	{
		
	}

	public Rectangle getSightBounds() 
	{
		return null;
	}
}
