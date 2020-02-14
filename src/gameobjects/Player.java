package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;

import framework.Animation;
import framework.GameObject;
import framework.ObjectID;
import game.Camera;
import game.Game;
import game.Handler;
import game.Inventory;
import game.Texture;
import items.Item;

public class Player extends GameObject
{
	//Fields of class: Player
	private float width = 32; //Player is 32 pixels WIDE
	private float height = 32; //Player is 96 pixels HIGH
	private float gravity = 0.1f; //Gravity per tick
	private final float MAX_SPEED = 10; // characters Y MAX speed
	private Handler handler; //Handler object
	private boolean movingLeft = false; //Added for a/d key movement bug
	private boolean movingRight = false; //Added for a/d key movement bug
    private Texture tex = Game.getInstance();
    private Animation playerWalk, playerWalkLeft;
	private Camera cam;
    private int health = 12; //current health (start: 12 AKA 3 hearts)
    private int maxHealth = 12; //max health (max: 80 AKA 20 hearts)
    private boolean dead = false; //Is player alive
    private String name;
    private Inventory inventory; //player's inventory
	private int moneyCount = 0;
	private int heartPieces;
	
	
	private boolean[] heartID = new boolean[68];
    
	/**
	 * Constructor for class: Player
	 * @param x passed to superClass: GameObject
	 * @param y passed to superClass: GameObject
	 * @param id passed to superClass: GameObject
	 * @param handler handler
	 */
	public Player(float x, float y, ObjectID id, Handler handler, 
			Camera cam, String name)
	{
		super(x, y, id, 12, 80, handler, 32, 32, ObjectID.Misc);
		this.handler = handler;
		playerWalk = new Animation(1, tex.player[1], tex.player[2]);
		playerWalkLeft = new Animation(1, tex.player[4], tex.player[5]);
		this.cam = cam;
		this.name = name;
		inventory = new Inventory();
	}
	
	public float getX()
	{
		return super.x;
	}
	
	public float getY()
	{
		return super.y;
	}
	public boolean getCollectedByID(int id)
	{
		return heartID[id];
	}
	
	public void setCollectedByID(int id, boolean value)
	{
		heartID[id] = value;
	}
	
	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0){
			facing = -1; // facing left
		}
		else if (velX > 0){
			facing = 1; // facing right
		}
		
		/*change to fix clunkyness with moving between left and right fast
		 * Right overrides left if both a and d are held down
		 */
		if (movingRight == true && movingLeft == false) { 
			velX = 3.55f; 
			}
		if (movingLeft == true && movingRight == false) { 
			velX = -3.55f; 
			} 
		if (movingLeft && movingRight) { 
			velX = 3.55f;
			} 
		if (movingRight == false && movingLeft == false) { 
			velX = 0; 
			}	
		
		
		if(falling || jumping){
			velY += gravity;
		}
		else if(velY >= MAX_SPEED){
				velY = MAX_SPEED;
			}
		else{
			velY = 0;
		}
		collision(object);
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
	}
	
	public void collision(LinkedList<GameObject> object)
	{
		boolean fallInvert = false;
	    Player thePlayer = Game.getThePlayer();
		
		for(int i = 0; i <handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ObjectID.Block){
				
				//top
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
					velY = 0;
				}
				
				//below
				if(getBoundsBot().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = (float) 0.9999999; //<-- CAUSES GLITCHY JUMP BUG
					jumping = false;
					if(isFalling()){
						fallInvert = true;
					}
					
				else{
					if(!isFalling()){
						fallInvert = true;
					}
				}
				}
				
				//right
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + width;
				}
				
				if(fallInvert){
					setFalling(false);
				}
				else{
					falling = true;
				}				
			}
			
			else if(tempObject.getID() == ObjectID.Flag){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.clearLevel();
					handler.switchLevel(((Flag) tempObject).getlvlID());
				}
			}
			
			else if(tempObject.getID() == ObjectID.Heart){
				
				//If head touches heart
				if(getBounds().intersects(tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addHealth(4);
				}
				
				//If side touches heart
				else if(getBoundsLeft().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addHealth(4);
					
				}
				
				//if side touches heart
				else if(getBoundsRight().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addHealth(4);
				}
				
				//ifBot touches heart
				else if(getBoundsBot().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addHealth(4);
				}
			}
			
			
			else if(tempObject.getID() == ObjectID.HeartPiece){
				
				//If head touches heart piece
				if(getBounds().intersects(tempObject.getBounds())){
					int id = ((HeartPiece) tempObject).getHeartID();
					tempObject.dispose();
					if(thePlayer.heartID[id] == false){
						thePlayer.addHeartPiece();
						thePlayer.addHealth(80);
					}
					thePlayer.setCollectedByID(id, true);
					System.out.println("Value at " + id + ": " 
							+ thePlayer.heartID[id]);;
					
				}
				
				//If side touches heart piece
				else if(getBoundsLeft().intersects(
						tempObject.getBounds())){
					int id = ((HeartPiece) tempObject).getHeartID();
					tempObject.dispose();
					if(thePlayer.heartID[id] == false){
						thePlayer.addHeartPiece();
						thePlayer.addHealth(80);
					}
					thePlayer.setCollectedByID(id, true);
					System.out.println("Value at " + id + ": " 
							+ thePlayer.heartID[id]);;
				}
				
				//if side touches heart piece
				else if(getBoundsRight().intersects(
						tempObject.getBounds())){
					int id = ((HeartPiece) tempObject).getHeartID();
					tempObject.dispose();
					if(thePlayer.heartID[id] == false){
						thePlayer.addHeartPiece();
						thePlayer.addHealth(80);
					}
					thePlayer.setCollectedByID(id, true);
					System.out.println("Value at " + id + ": " 
							+ thePlayer.heartID[id]);;;
				}
				
				//ifBot touches heart piece
				else if(getBoundsBot().intersects(
						tempObject.getBounds())){
					int id = ((HeartPiece) tempObject).getHeartID();
					tempObject.dispose();
					if(thePlayer.heartID[id] == false){
						thePlayer.addHeartPiece();
						thePlayer.addHealth(80);
					}
					thePlayer.setCollectedByID(id, true);
					System.out.println("Value at " + id + ": " 
							+ thePlayer.heartID[id]);;
				}
			}
			
			
			else if(tempObject.getID() == ObjectID.Sign){
				if(getBounds().intersects(tempObject.getBounds())){
					((Sign) tempObject).setTouching(true);
				}
				
				if(!getBounds().intersects(tempObject.getBounds())){
					((Sign) tempObject).setTouching(false);
				}
			}
			
			else if(tempObject.getID() == ObjectID.Bat){
				if(getBounds().intersects(tempObject.getBounds())){
					setVelX(10);
					if(thePlayer.getHealth() > 0){
						thePlayer.damageHealth(1);
					}
				}
			}
			
			else if(tempObject.getID() == ObjectID.Money){
				
				//If head touches money
				if(getBounds().intersects(tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addMoney(((Money) tempObject).getValue());
				}
				
				//If side touches money
				else if(getBoundsLeft().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addMoney(((Money) tempObject).getValue());
					
				}
				
				//if side touches money
				else if(getBoundsRight().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addMoney(((Money) tempObject).getValue());
				}
				
				//if Bot touches money
				else if(getBoundsBot().intersects(
						tempObject.getBounds())){
					tempObject.dispose();
					thePlayer.addMoney(((Money) tempObject).getValue());
				}
			}
			
			else if(tempObject.getID() == ObjectID.Player
					&& this != tempObject){
				
				if(getBounds().intersects(tempObject.getBounds())){
					System.out.println("player 1 is col with player 2");
				}
			}
		}
	}

	/**
	 * This renders the player
	 */
	public void render(Graphics g) {		
		g.setColor(Color.blue);
		if(velX != 0){
			if(facing ==1){
				playerWalk.drawAnimation(g, (int) x, (int) y, 32, 32);
			}
			else{
				playerWalkLeft.drawAnimation(g, (int) x, (int) y, 32, 32);
			}
		}
		else{
			if(facing == 1){ // right idle
				g.drawImage(tex.player[0], (int) x, (int) y, 32, 32, null);
			}
			else if (facing == -1){	// left idle
				g.drawImage(tex.player[3], (int) x, (int) y, 32, 32, null);
			}
		}
	}

	/**
	 * Collision for player
	 */
	public Rectangle getBoundsBot() 
	{
		return new Rectangle((int) ((int) x + (width/2) - ((width/2)/2)), 
				(int) (y+(height/2)), (int) width/2, (int) height/2);
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int) ((int) x + (width/2) - ((width/2)/2)), 
				(int) y, (int) width/2, (int) height/2);
	}
	
	public Rectangle getBoundsRight() 
	{
		return new Rectangle((int) ((int) x + width-5), (int) y+5, 
				(int) 5, (int) height-10);
	}
	
	public Rectangle getBoundsLeft() 
	{
		return new Rectangle((int) x, (int) y+5, (int) 5, (int) height-10);
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
		if(this.health < maxHealth){
			this.health = this.health + health;
			setAlive();
		}
		
		if(health == 80){
			this.health = maxHealth;
			setAlive();
		}
		
		//80 - 77 78 79
		if(((maxHealth - health) == 1)|| ((maxHealth - health) == 2) || ((maxHealth - health) == 3)){
			System.out.println("Triggered");
			this.health = maxHealth;
			setAlive();
		}
	}
	
	public void damageHealth(int damage)
	{
		if(health != 0){
			this.health = this.health - damage;
		}
		if(health == 0){
			setDead();
		}
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public void increaseMaxHealth(int increase)
	{
		if(maxHealth > 80){
			maxHealth = 80;
		}
		else if(maxHealth <= 76){
			maxHealth = maxHealth + increase;
		}
	}
	
	public void decreaseMaxHealth(int decrease)
	{
		if(maxHealth <= 0){
			maxHealth = 4;
		}
		else if(maxHealth >= 4){
			maxHealth = maxHealth - decrease;
		}
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public void setDead()
	{
		if(health == 0){
			dead = true;
		}
	}
	
	public void setAlive()
	{
		if(health > 0){
			dead = false;
		}
	}
	
	public HashMap<String, Item> getInventory()
	{
		return inventory.getInventory();
	}
	
	public void addItem(String key, Item value)
	{
		try{
			inventory.add(key, value);
		}
		catch(NullPointerException e){
			System.out.println("Inventory does not exist?!");
		}
	} 
	
	public void addMoney(int amount)
	{
		moneyCount = moneyCount + amount;
		
		if(moneyCount < 0){
			moneyCount = 0;
		}
		else if(moneyCount >= 500){
			moneyCount = 500;
		}
	}
	
	public int getMoneyCount()
	{
		return moneyCount;
	}
	
	public void addHeartPiece()
	{
		heartPieces++;
		
		if(Game.getThePlayer().getMaxHealth() == 80){
			heartPieces = 0;
		}
		
		if(heartPieces <= 0){
			heartPieces = 0;
		}
		else if(heartPieces >= 4){
			heartPieces = 0;
			increaseMaxHealth(4);
		}
	}
	
	public Rectangle getSightBounds() 
	{
		return null;
	}
}
