package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Handler;

public abstract class GameObject 
{
	protected ObjectID id, id2;
	protected float x, y;
	protected float velX = 0;
	protected float velY = 0;
	protected int facing = 1; //1 = right, -1 = left
	
	protected boolean falling;
	protected boolean jumping;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	private int health;
	private int maxHealth;
	
	private int width;
	private int height;
	
	private boolean dispose = false;
	
	@SuppressWarnings("unused")
	private Handler handler;


	public GameObject(float x, float y, ObjectID id, int health, 
			int maxHealth, Handler handler, int width, int height,
			ObjectID id2)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.id2 = id2;
		this.health = health;
		this.maxHealth = maxHealth;
		this.handler = handler;
		this.width = width;
		this.height = height;
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getSightBounds();
	public abstract void collision(LinkedList<GameObject> object);
	
	public void dispose()
	{
		dispose = true;
	}
	
	public boolean getDisposeState()
	{
		return dispose;
	}
	
	public  float getX()
	{
		return x;
	}
	
	public  float getY()
	{
		return y;
	}
	
	public  void setX(float x)
	{
		if(x >= 0){
			this.x = x;
			facing = 1;
		}
		else{
			this.x =x;
			facing = -1;
		}
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void addToX(float x)
	{
		this.x += x;
	}
	
	public void addToY(float y)
	{
		this.y += y;
	}
	
	public  float getVelX()
	{
		return velX;
	}
	
	public  float getVelY()
	{
		return velY;
	}
	
	public  void setVelX(float velX)
	{
		this.velX = velX;
	}
	
	public  void setVelY(float velY)
	{
		this.velY = velY;
	}
	
	public  ObjectID getID()
	{
		return id;
	}
	
	public ObjectID getID2()
	{
		return id2;
	}
	
	public boolean isMovingLeft() 
	{
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) 
	{
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() 
	{
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) 
	{
		this.movingRight = movingRight;
	}

	public boolean isFalling() 
	{
		return falling;
	}

	public void setFalling(boolean falling) 
	{
			this.falling = falling;
	}

	public boolean isJumping() 
	{
		return jumping;
	}

	public void setJumping(boolean jumping) 
	{
		this.jumping = jumping;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public void addHealth(int health)
	{
		this.health = this.health + health;
	}
	
	public void damageHealth(int damage)
	{
		this.health = this.health - damage;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getFacing()
	{
		return facing;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
}
