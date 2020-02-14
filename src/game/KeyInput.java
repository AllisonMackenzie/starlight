package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import framework.GameObject;
import framework.ObjectID;
import framework.Shop;
import gameobjects.Player;
import items.Arrow;
import items.HookShot;

public class KeyInput extends KeyAdapter
{	
	private Handler handler;
	private String entered = "";
	private static int key;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	
	public String getEntered()
	{
		return entered;
	}
	
	public static int getKeyPressed()
	{
		return key;
	}
	
	public void keyPressed(KeyEvent e)
	{
		key = e.getKeyCode();
		int level = 0;
		Player thePlayer = Game.getThePlayer();
		
		if(Game.getGameState() == GameState.LevelSelectMenu){
			switch(key){
				case KeyEvent.VK_NUMPAD0:
				case KeyEvent.VK_0:
					if(entered.length() < 4){
						entered = entered + "0";
					}
					break;
				case KeyEvent.VK_NUMPAD1:
				case KeyEvent.VK_1:
					if(entered.length() < 4){
						entered = entered + "1";
					}
					break;
				case KeyEvent.VK_NUMPAD2:
				case KeyEvent.VK_2:
					if(entered.length() < 4){
						entered = entered + "2";
					}
					break;
				case KeyEvent.VK_NUMPAD3:
				case KeyEvent.VK_3:
					if(entered.length() < 4){
						entered = entered + "3";
					}
					break;
				case KeyEvent.VK_NUMPAD4:
				case KeyEvent.VK_4:
					if(entered.length() < 4){
						entered = entered + "4";
					}
					break;
				case KeyEvent.VK_NUMPAD5:
				case KeyEvent.VK_5:
					if(entered.length() < 4){
						entered = entered + "5";
					}
					break;
				case KeyEvent.VK_NUMPAD6:
				case KeyEvent.VK_6:
					if(entered.length() < 4){
						entered = entered + "6";
					}
					break;
				case KeyEvent.VK_NUMPAD7:
				case KeyEvent.VK_7:
					if(entered.length() < 4){
						entered = entered + "7";
					}
					break;
				case KeyEvent.VK_NUMPAD8:
				case KeyEvent.VK_8:
					if(entered.length() < 4){
						entered = entered + "8";
					}
					break;
				case KeyEvent.VK_NUMPAD9:
				case KeyEvent.VK_9:
					if(entered.length() < 4){
						entered = entered + "9";
					}
					break;
			}
			
			if(key == KeyEvent.VK_BACK_SPACE){
				if(entered.length() != 0){
					entered = entered.substring(0, entered.length()-1);
				}
			}			
			
			if(key == KeyEvent.VK_ENTER){	
				try{
					level = Integer.parseInt(entered);
				}
				catch(NumberFormatException ex){
					System.out.println("error");
				}
			
				switch(level){
					case 1:
						handler.clearLevel();
						Game.setTheLevel(1);
						handler.switchLevel(1);
						Game.setState(GameState.Game);
						break;
					case 2:
						handler.clearLevel();
						Game.setTheLevel(2);
						handler.switchLevel(2);
						Game.setState(GameState.Game);
						break;
					case 3:
						handler.clearLevel();
						Game.setTheLevel(3);
						handler.switchLevel(3);
						Game.setState(GameState.Game);
						break;
					case 4:
						handler.clearLevel();
						Game.setTheLevel(4);
						handler.switchLevel(4);
						Game.setState(GameState.Game);
				}
			}
		}
		
		else if(Game.getGameState() == GameState.Shop){
			//for(int i = 0; i < handler.object.size(); i++){
			//	GameObject tempObject = handler.object.get(i);
			//}
			
			if(key == KeyEvent.VK_ESCAPE){
				System.out.println(Game.getGameState());
				//Game.setState(GameState.Game);
			}
			
		}
		else{
			
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempObject.setMovingRight(true);
				}

				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempObject.setMovingLeft(true);
				}
				
				//you can currently jump walling falling off a platform
				//decide if you want this .. if not then fix
				if(key == KeyEvent.VK_SPACE /* && !tempObject.isJumping()
						&& !tempObject.isFalling()*/){
						tempObject.setJumping(true);
						tempObject.setFalling(true);
						tempObject.setVelY(-3.5f);
					}
				
				if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !tempObject.isJumping()
						&& !tempObject.isFalling()){
						tempObject.setJumping(true);
						tempObject.setFalling(true);
						tempObject.setVelY(-3.5f);
					}
				
				if(key == KeyEvent.VK_O){
					handler.addObject(new HookShot(tempObject.getX()+30,
							tempObject.getY()+8, ObjectID.HookShot, 0,
							tempObject.getFacing()*5, handler));
				}
				
				if(key == KeyEvent.VK_P){
					handler.addObject(new Arrow(tempObject.getX()+30,
							tempObject.getY()+8, ObjectID.Arrow, 0, 0,
							tempObject.getFacing()*5, handler));
				}
				
				if(key == KeyEvent.VK_B){
					thePlayer.damageHealth(1);
				}
				
				if(key == KeyEvent.VK_N){
					thePlayer.addHealth(1);
				}
				
				if(key == KeyEvent.VK_M){
					if(thePlayer.isDead() == false) {
						thePlayer.increaseMaxHealth(4);
						thePlayer.addHealth(4);
					}
				}
				
				if(key == KeyEvent.VK_F1){
					if(Game.canSeeFPS() == false){
						Game.showFPS(true);
					}
					else{
						Game.showFPS(false);
					}
				}
				
				if(key == KeyEvent.VK_F2){
					if(Game.canSeePlayerXY() == false){
						Game.showPlayerXY(true);
					}
					else{
						Game.showPlayerXY(false);
					}
				}
				
				if(key == KeyEvent.VK_F3){
					if(Game.canSeePlayerHP() == false){
						Game.showPlayerHP(true);
					}
					else{
						Game.showPlayerHP(false);
					}
				}
				
				if(key == KeyEvent.VK_ENTER){
				}
				
				if(key == KeyEvent.VK_END){
					Shop.setShopID(3);
				}
				
				
			}
		}
		}
		
		//If key is escape: call menu - 
		//To exit game from quit option: System.exit(1);
		if(key == KeyEvent.VK_ESCAPE){
			//Game.setTheLevel(0);
			if(Game.getGameState() == GameState.Game){
				Game.setState(GameState.Paused);
			}
			else{
				Game.setState(GameState.Game);
			}
		}
		
		
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		Player thePlayer = Game.getThePlayer();
		
		if(Game.getGameState() == GameState.Game){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempObject.setMovingRight(false);
				}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempObject.setMovingLeft(false);
				}
				
			}
			
		}
		}
		
		
	}

}
