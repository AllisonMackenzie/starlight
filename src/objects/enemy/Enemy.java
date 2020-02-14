package objects.enemy;

import framework.GameObject;
import framework.ObjectID;
import game.Handler;

/**
 * The enemy class is a base abstract class for which the actual enemies
 * in the game can extend from.
 *
 * @author Allison Johnson
 * @version July 1st, 2016
 */
public abstract class Enemy extends GameObject
{
	private int knockback;
	private int invincibilityFrames;
	private int count = 0;
	protected boolean canBeDamaged = true;
	
	
	/**
     * Constructor for objects of class Enemy
     * 
     * @param x 
     * @param y
     * @param id
     * @param health 
     * @param maxHealth
     * @param knockback
     * @param handler
     * @param width
     * @param height
     * @param frames 
     */
	public Enemy(float x, float y, ObjectID id, int health, int maxHealth,
			int knockback, Handler handler, int width, int height, 
			int frames) 
	{
		super(x, y, id, health, maxHealth, handler, width, height, 
				ObjectID.Bat);
		this.knockback = knockback;
		this.invincibilityFrames = frames;
	}
	
	public abstract String getDescription();
	
	public void updateTimers()
	{
		count = 0;
	}
		
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public int getKnockback()
	{
		return knockback;
	}

}
