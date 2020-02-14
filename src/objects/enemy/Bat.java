package objects.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import framework.GameObject;
import framework.ObjectID;
import game.Game;
import game.Handler;
import game.Texture;
import gameobjects.Player;

/**
 * Makes a bat enemy object
 * 
 * @author Allison Johnson
 * @version March 25th, 2016
 */
public class Bat extends Enemy
{
	private static Player thePlayer = null;
	Texture tex = Game.getInstance();
	private Handler handler;
	private Random random = new Random();
	private int randomValue = random.nextInt(3);
	private String description;
	private float originalXPos, originalYPos;

	public Bat(float x, float y, ObjectID id, int health, int maxHealth,
			Handler handler) 
	{
		super(x, y, id, health, maxHealth, 1, handler, 32, 32, 90);
		this.handler = handler;
		description = "An ordinary bat.";
		originalXPos = super.getX();
		originalYPos = super.getY();
	}

	public void tick(LinkedList<GameObject> object) 
	{
		if(thePlayer == null){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
			
				if(tempObject.getID() == ObjectID.Player){
					thePlayer = (Player) tempObject;
				}
			}
		}
		
		switch(randomValue){
			case 0: 
				dispose();
				break;
			
			case 1:
				if(this.getSightBounds().intersects(thePlayer.getBounds())){
					this.velX = 1;
					x += velX;
				}
					break;
				
			case 2:
				if(this.getDisposeState() == false){
				}
				if((int) thePlayer.getY() == y){
					this.velX = 1;
					x += velX;
				}
				break;
		}
		
		
		
		if(velX > 0){
			facing = 1;
		}
		else if(velX < 0){
			facing = -1;
		}
		
		if(getHealth() <= 0){
			this.dispose();
		}
		else if(getHealth() > 0){
			collision(object);
		}
	}

	public void render(Graphics g) 
	{
		if(getDisposeState() == false){
			g.drawImage(tex.enemy[0], (int) getX(), (int) getY(), null);
			
			if(Game.getDebugModeState() == true) {
				g.setColor(Color.MAGENTA);
				g.drawRect((int) x-96, (int) y-96, 256, 256);
			}
		}
	}

	public Rectangle getBounds()
	{
		if(getDisposeState() == false){
			return new Rectangle ((int) x, (int) y, getWidth(), 
					getHeight());
		}
		else{
			return new Rectangle((int) x, (int) y, 0, 0);
		}
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	
	public void collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					if(facing == 1){
						x = tempObject.getX() - tempObject.getWidth();
					}
					if(facing == -1){
						x = tempObject.getX() + tempObject.getWidth();
					}
				}
			}
			
			else if(tempObject.getID() == ObjectID.Bat
					&& this != tempObject){
				
				if(getBounds().intersects(tempObject.getBounds())){
					if(facing == 1 && tempObject.getFacing() == 1){
						x = tempObject.getX() - tempObject.getWidth();
					}
					
					if(facing == 1 && tempObject.getFacing() == -1){
						x = tempObject.getX() + tempObject.getWidth();
					}
					
					if(facing == -1 && tempObject.getFacing() == 1){
						x = tempObject.getX() - tempObject.getWidth();
					}
					
					if(facing == -1 && tempObject.getFacing() == -1){
						x = tempObject.getX() - tempObject.getWidth();
					}
				}
			}
			
			else if(tempObject.getID() == ObjectID.Arrow){
				if(getBounds().intersects(tempObject.getBounds())){
					if(getHealth() > 0){
						damageHealth(2);
					}
				}
			}
			
		}
	}
	
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Bats can "see" using echolocation for 224 pixel wise, 128 wide
	 */
	public Rectangle getSightBounds() 
	{
		return new Rectangle((int) x, (int) y, 224, 128);
	}
}