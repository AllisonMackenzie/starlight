package objects.npc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import framework.Shop;
import game.Game;
import game.GameState;
import game.Handler;
import game.KeyInput;
import game.Texture;

public class Jay extends NPC implements ShopKeeper
{
	private Handler handler;
	private Texture tex = Game.getInstance();
	private int key;

	public Jay(float x, float y, ObjectID id, int health, int maxHealth, 
			Handler handler, int width, int height, int facing)
	{
		super(x, y, id, health, maxHealth, handler, width, height, false, 
				"Jay", NPCClass.Knight);
		this.handler = handler;
		super.facing = facing;
	}

	public void tick(LinkedList<GameObject> object) 
	{
		collision(object);
	}

	public void render(Graphics g) 
	{
		if(getDisposeState() == false){
			g.drawImage(tex.enemy[0], (int) getX(), (int) getY(), null);
			
			g.setColor(Color.WHITE);
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
	
	public void act()
	{
		Shop.setShopID(3);
		Game.setState(GameState.Shop);
	}

	public void collision(LinkedList<GameObject> object) 
	{		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
				}
			}
			
			else if(tempObject.getID() == ObjectID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					key = KeyInput.getKeyPressed();
					
					if(Game.getHelpMessageStatus() == false){
						Game.showHelpMessage(true);
						Game.setHelpMessage("Press [ Enter ] to "
								+ "access shop!");
					}
					
					if(key == KeyEvent.VK_ENTER){
						act();
					}
				}
				
				if(getSightBounds().intersects
						(tempObject.getBounds())){
					
					if(Game.getHelpMessageStatus() == false){
						Game.showHelpMessage(true);
						Game.setHelpMessage("Press [ Enter ] to "
								+ "access shop!");
					}					
					
					key = KeyInput.getKeyPressed();
					
					if(key == KeyEvent.VK_ENTER){
						act();
					}
					
				}
			
				if(Game.getHelpMessageLength() != 0 && 
						!getSightBounds().intersects
						(tempObject.getBounds())){
					
					Game.setHelpMessage("harro");
					if(Game.getHelpMessageStatus() == true){
						Game.showHelpMessage(false);
					}
				}
			}
		}
	}
	
	public Rectangle getSightBounds()
	{		
		return new Rectangle((int) x-96, (int) y, getWidth()+160, 
				getHeight()+32);
	}

}
