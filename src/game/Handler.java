package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.Block;
import framework.GameObject;
import framework.ObjectID;
import gameobjects.Flag;
import gameobjects.Heart;
import gameobjects.HeartPiece;
import gameobjects.Money;
import gameobjects.Player;
import gameobjects.Sign;
import objects.enemy.Bat;
import objects.npc.Jay;

public class Handler 
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject temporaryObject;
	private Camera cam;
	private static Player thePlayer = Game.getThePlayer();
	
	public Handler(Camera cam)
	{
		this.cam = cam;
	}
	
	public Handler()
	{
	}
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++){
			temporaryObject = object.get(i);
			temporaryObject.tick(object);
		}
	}
	
	public void render(Graphics g, Rectangle viewport)
	{
		for(int i = 0; i < object.size(); i++){
			temporaryObject = object.get(i);
			if(viewport.contains(temporaryObject.getBounds()) || 
					viewport.intersects(temporaryObject.getBounds()))
			{
				temporaryObject.render(g);
			}
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	public void clearLevel()
	{
		object.clear();
	}
	
	public void loadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
				
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx,yy);
				int red  = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				thePlayer = new Player(xx*32, yy*32, ObjectID.Player, 
						this, cam, "Amelia");
				
				//Player Spawn
				if(red == 0 && green == 0 && blue == 255){
					addObject(thePlayer);
				}
				
				//Grass block  normal
				if(red == 0 && green == 255 && blue == 0){
					addObject(new Block(xx*32, yy*32, 0, 
							ObjectID.Block,	0, 0, this, 32, 32));
				}	
				
				//Grass Block (grass on right)
				if(red == 25 && green == 255 && blue == 50){
					addObject(new Block(xx*32, yy*32, 6,
							ObjectID.Block, 0, 0, this, 32, 32));
				}
				
				//Grass Block (grass on left)
				if(red == 50 && green == 255 && blue == 100){
					addObject(new Block(xx*32, yy*32, 7,
							ObjectID.Block, 0, 0, this, 32, 32));
				}
				
				//grass block (down)
				if(red == 75 && green == 255 && blue == 150){
					addObject(new Block(xx*32, yy*32, 8,
							ObjectID.Block, 0, 0, this, 32, 32));
				}
				
				//Invisible Wall
				if(red == 178 && green == 0 && blue == 255){
					addObject(new Block(xx*32, yy*32, -1, 
							ObjectID.Block,	0, 0, this, 32, 32));
				}

				//Money
				if(red == 255 && green == 0 && blue == 0){
					addObject(new Money(xx*32, yy*32, ObjectID.Money,
							0, 0, 1, this, 5));
				}
				
				//Heart
				if(red == 255 && green == 10 && blue == 10){
					addObject(new Heart(xx*32, yy*32, ObjectID.Heart, 
							0, 0, this));
				}
				
				//Heart Pieces
				if(red == 138 && green == 138 && blue == 138){
					if(Game.getThePlayer()
							.getCollectedByID(1) == false){
						addObject(new HeartPiece(xx*32, yy*32, 
								ObjectID.HeartPiece, 0, 0, this, 1));
					}
				}
				
				//Dirt Block
				if(red == 127 && green == 51 && blue == 0){
					addObject(new Block(xx*32, yy*32, 1, 
							ObjectID.Block,	0, 0, this, 32, 32));
				}
				
				//Gravel Block
				if(red == 63 && green == 127 && blue == 98){
					addObject(new Block(xx*32, yy*32, 2,
							ObjectID.Block, 0, 0, this, 32, 32));
					
				}
				
				//Wood Pillar block
				if(red == 165 && green == 138 && blue == 0){
					addObject(new Block(xx*32, yy*32, 3,
							ObjectID.Block, 0, 0, this, 32, 32));
					
				}
				
				//Wood Floor block
				if(red == 63 && green == 127 && blue == 98){
					addObject(new Block(xx*32, yy*32, 4,
							ObjectID.Block, 0, 0, this, 32, 32));
					
				}
				
				//Flag transfer to area 1
				if(red == 100 && green == 100 && blue == 100){
					addObject(new Flag(xx*32, yy*32, ObjectID.Flag, 
							0, 0, 0, 1, this));
				}
				
				//flag transfer to area 2
				if(red == 100 && green == 100 && blue == 101){
					addObject(new Flag(xx*32, yy*32, ObjectID.Flag, 
							0, 0, 0, 2, this));
				}
				
				//flag transfer to area 3
				if(red == 100 && green == 100 && blue == 102){
					addObject(new Flag(xx*32, yy*32, ObjectID.Flag, 
							0, 0, 0, 3, this));
				}
				
				//Sign 1
				if(red == 120 && green == 120 && blue == 120){
					addObject(new Sign(xx*32, yy*32, ObjectID.Sign, 
							0, 0, this,	"Area 1"));
				}
				
				//Sign 2
				if(red == 120 && green == 120 && blue == 121){
					addObject(new Sign(xx*32, yy*32, ObjectID.Sign, 
							0, 0, this,	"Area 2"));
				}
				
				//Sign 3
				if(red == 120 && green == 120 && blue == 122){
					addObject(new Sign(xx*32, yy*32, ObjectID.Sign, 
							0, 0, this,	"Shop 1"));
				}
				
				//Bat
				if(red == 117 && green == 117 && blue == 117){
					addObject(new Bat(xx*32, yy*32, ObjectID.Bat,
							12, 12, this));
				}
				
				//Jay
				if(red == 200 && green == 200 && blue == 200){
					addObject(new Jay(xx*32, yy*32, ObjectID.NPC, 
							1, 1, this, 32, 32, 0));
				}
				
				//Furniture: Desk
				if(red == 177 && green == 177 && blue == 0){
					addObject(new Block(xx*32, yy*32, 5,
							ObjectID.Block, 0, 0, this, 32, 32));
				}
				
			}
		}
	}
	
	public void switchLevel(int lvlID)
	{
		clearLevel();
		cam.setX(0);
		Game.setTheLevel(lvlID);
		
		
		switch(Game.getTheLevel()){
			case 1:
				loadImageLevel(Game.getLevel1());
				break;
			case 2:
				loadImageLevel(Game.getLevel2());
				break;
			case 3:
				loadImageLevel(Game.getLevel3());
				break;
			case 4:
				loadImageLevel(Game.getLevel4());
				break;
		}
	}
}
