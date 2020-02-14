package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.sound.sampled.Clip;

import framework.BufferedImageLoader;
import framework.ClipLoader;
import framework.GameObject;
import framework.LevelSelectMenu;
import framework.ObjectID;
import framework.Shop;
import gameobjects.Player;
import online.Client;
import online.Server;


public class Game extends Canvas implements Runnable
{
	//Core fields
	private static final long serialVersionUID = -1231136700719031270L;
	private Thread thread;
	private boolean running = false;
	//Normally, I would create all fields as private, however in this particular instance you will need to jump through tons of hoops due to WIDTH and HEIGHT
	//being static variables to get the program to work properly.
	public static int WIDTH, HEIGHT;
	
	//BufferedImage section 1
	private static BufferedImage img_level1 = null;
	private static BufferedImage img_level2 = null;
	private static BufferedImage img_level3 = null;
	private static BufferedImage img_level4 = null;
	
	//BufferedImage section 2
	private BufferedImage img_background = null;
	private BufferedImage img_backgroundTest = null;
	private BufferedImage img_hearts = null;
	private BufferedImage img_heartsHalf = null;
	private BufferedImage img_heart1Quarter = null;
	private BufferedImage img_heart3Quarter = null;
	private BufferedImage img_Emptyheart = null;
	
	//Sound clips
	private Clip musicPoke = null;
	private Clip musicMart = null;
	private Clip musicWindTemple = null;
	private Clip musicPoke2 = null;
	private Clip beeps = null;
	private Clip currentlyPlaying = null;
	
	//RNG
	private Random random = new Random();
	private int randomValue;
	
	//Objects
	private static Camera camera = new Camera(20,20);
	private static Handler handler = new Handler(camera);
	private static Texture tex;
	
	//KeyInput and MouseInput
	private static KeyInput keyInput = new KeyInput(handler);
	private static MouseInput mouse = new MouseInput(handler);
	
	//Player related
	private static Player thePlayer;
	private static int level = 2; 
	private float playerX, playerY;
	private static boolean showXY;
	private static boolean showHP;
	
	//Game Menu and State management	
	private MainMenu mainMenu = new MainMenu();
	private LevelSelectMenu levelMenu = new LevelSelectMenu();
	private static GameState state = GameState.LevelSelectMenu;
	private Shop shop = new Shop();
	private Paused paused = new Paused();
	private Dead dead = new Dead();
	private int fps;
	private static boolean showFPS = true;
	private static boolean debugMode = true;
	
	//Online play
	private static Server server = null;
	private static Client client = null;	
	
	//Misc
	private static String helpMessage = "";
	private static boolean showHelpMessage = false;
	
	public static void main(String[] args)
	{
		//anonymous window
		new Window(800, 600, "StarLight", new Game());;
	}
	
	public synchronized void start()
	{
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() 
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " 
				+ updates);
				
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
				
					if(tempObject.getID() == ObjectID.Player){
						System.out.println("X: " + tempObject.getX()
								+ " Y: " + tempObject.getY());
						System.out.println("CamX: " + camera.getX()
								+ " CamY: " + camera.getY());
						System.out.println("MouseX: " + mouse.getXPos()
						+ " MouseY: " + mouse.getYPos());
						System.out.println("Current level ID: " 
								+ level);
						System.out.println();
					}
					
				}
				fps = frames;
				frames = 0;
				updates = 0;
			}
		}
	}

	private void init()
	{
		setWIDTH(getWidth());
		setHEIGHT(getHeight());
		
		tex = new Texture();
		thePlayer = new Player(0, 0, ObjectID.Player, 
				handler, camera, "Amelia");
		
		BufferedImageLoader loader = new BufferedImageLoader();
		ClipLoader clipLoader = new ClipLoader();
		
		img_level1 = loader.loadImage("/level_01.png");
		img_level2 = loader.loadImage("/level_02.png");
		img_level3 = loader.loadImage("/level_ray's_shop.png");
		img_level4 = loader.loadImage("/level_04.png");
		
		img_background = loader.loadImage("/background_clouds.png"); 
		img_backgroundTest = loader.loadImage("/img_backgroundTest.jpg");
		img_hearts = loader.loadImage("/16x16 heart.png"); 
		img_heartsHalf = loader.loadImage("/half heart.png");
		img_heart1Quarter = loader.loadImage("/1 quarter heart.png"); 
		img_heart3Quarter = loader.loadImage("/3 quarters heart.png"); 
		img_Emptyheart = loader.loadImage("/Empty heart.png");
		
		musicPoke = clipLoader.loadClip("/poke.wav");
		musicMart = clipLoader.loadClip("/music_mart.wav");
		musicWindTemple = clipLoader.loadClip("/music_windTemple.wav");
		musicPoke2 = clipLoader.loadClip("/Catch Me If You Can - Angela Via - Pokemon the First Movie.wav");
		beeps = clipLoader.loadClip("/beep.wav");	
		
		handler.loadImageLevel(img_level2); // loads the level
		
		this.addKeyListener(keyInput);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseWheelListener(mouse);
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int height) {
		HEIGHT = height;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}
	
	public static void showFPS(boolean value)
	{
		showFPS = value;
	}
	
	public static boolean canSeeFPS()
	{
		return showFPS;
	}
	
	public static void showPlayerXY(boolean value)
	{
		showXY = value;
	}
	
	public static boolean canSeePlayerXY()
	{
		return showXY;
	}
	
	public static void showPlayerHP(boolean value)
	{
		showHP = value;
	}
	
	public static boolean canSeePlayerHP()
	{
		return showHP;
	}
	
	/**
	 * Tick method 
	 * FPS: 60 - Ticks 
	 */
	public void tick()
	{
		if(state == GameState.Game){
			handler.tick();
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getID() == ObjectID.Player){
					camera.tick(handler.object.get(i));
					playerX = handler.object.get(i).getX();
					playerY = handler.object.get(i).getY();
				}
			}
		}
	}
	
	/**
	 * Render method
	 */
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		ClipLoader clipLoader = new ClipLoader();
		
		/////////////////////
		//
		//Draw Here
		
		if(state == GameState.Game){
		g.setColor(new Color(100, 255, 255)); //background color
		g.fillRect(0, 0, getWidth(), getHeight());

		g.translate(camera.getX(), camera.getY()); // begin of camera

		int viewport_padding = 0;
		Rectangle viewport = new Rectangle
		 (
			(int)-camera.getX() + viewport_padding,
			(int)-camera.getY() + viewport_padding,
			getWidth() - viewport_padding*2,
			getHeight() - viewport_padding*2
		 );

		for(int xx = 0; xx< img_background.getWidth() * 4; 
				xx += img_background.getWidth())
		{
			Rectangle bgBoundaries = new 
					Rectangle(xx, 50, img_background.getWidth(), 
							img_background.getHeight());

			if(!viewport.contains(bgBoundaries) && 
					!viewport.intersects(bgBoundaries))
				continue;

			g.setColor(Color.BLACK);
			
			// clouds behind player 
			g.drawImage(img_background, xx, 50, this);
		}

		handler.render(g, viewport); // Player/blocks/enemies etc
		                             //rendered in front of clouds

		g.translate(-camera.getX(), -camera.getY()); // end of camera

		g.setColor(new Color(0, 0, 0, 127));
		g.fillRect(0, 0, viewport_padding, getHeight());
		g.fillRect(viewport_padding, 0, getWidth()-viewport_padding, 
				viewport_padding);
		g.fillRect(getWidth()-viewport_padding, viewport_padding, 
				viewport_padding, getHeight()-viewport_padding);
		g.fillRect(viewport_padding, getHeight()-viewport_padding, 
				getWidth()-viewport_padding*2, viewport_padding);
		
		//Draw Here
		//
		////////////////////
		
		//HUD AREA
		

		
		//If you want to see number of money in bottom left
		g.drawString("Money Count: "+
				thePlayer.getMoneyCount(), 10, 600);
		
		if(showFPS == true){						
			g.setColor(Color.BLACK);
			g.drawString("FPS: " + fps, 700, 32);
		}
		
		if(showXY == true){
			g.setColor(Color.WHITE);
			g.fillRect(695, 20, 75, 15);
			g.setColor(Color.BLACK);
			g.drawString("XPos: " + playerX, 700, 47);
			g.drawString("YPos: " + playerY, 700, 62);
			g.drawString("CamX: " + camera.getX(), 700, 77);
			g.drawString("CamY: " + camera.getY(), 700, 92);
			g.drawString("MouseX: " + mouse.getXPos(), 700, 107);
			g.drawString("MouseY: " + mouse.getYPos(), 700, 122);
		}
		
		if(showHP == true){
			g.setColor(Color.BLACK);
			g.drawString("HP: " + thePlayer.getHealth(), 700, 137);
			g.drawString("MaxHP: " + thePlayer.getMaxHealth(), 700, 152);
		}
		
	       
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            int offsetX = 10;
            int offsetY = 10;
            int health = Game.thePlayer.getHealth();
            
            if(tempObject.getID() == ObjectID.Player) {
                for(int ii = 0;
                		ii < thePlayer.getMaxHealth(); ii += 4){
 
                    int x = offsetX + (((ii/4) % 10) * 
                    		img_hearts.getWidth());
                    int y = offsetY + (((ii/4) / 10) * 
                    		img_hearts.getHeight());
                   
                    if(ii+4 <= health){
                        g.drawImage(img_hearts, x, y, this);
                    }
                    else if(ii <= health && ii+4 >= health) {
                        int remainingHP = health%4;
                        BufferedImage remainingHeartImg = null;
                        switch(remainingHP) {
                            case 0:
                                remainingHeartImg = img_Emptyheart;
                                break;
                            case 1:
                                remainingHeartImg = img_heart1Quarter;
                                break;
                            case 2:
                                remainingHeartImg = img_heartsHalf;
                                break;
                            case 3:
                                remainingHeartImg = img_heart3Quarter;
                                break;
                        }
                        g.drawImage(remainingHeartImg, x, y, this);
                    }
                    else {
                        g.drawImage(img_Emptyheart, x, y, this);
                    }
                }
            }
        }

				if(thePlayer.getHealth() <= 4){
					if(!beeps.isRunning()){
						clipLoader.playClip(beeps);
					}
				}
				if(thePlayer.getHealth() > 4 
						|| thePlayer.getHealth() == 0){
					if(beeps.isRunning()){
						clipLoader.stopClip(beeps);
					}
				}
												
				if(getTheLevel() == 1){
					img_background = 
							img_backgroundTest;
					
					while(!musicWindTemple.isRunning()){
						try{
							clipLoader.stopClip(currentlyPlaying);
						}
						catch(NullPointerException e){
						}
						clipLoader.playClip(musicWindTemple);
						currentlyPlaying = musicWindTemple;
					}
				}
				
				if(getTheLevel() == 2){
					img_background =
							img_backgroundTest;
					
					while(!musicPoke.isRunning()){
						try{
							clipLoader.stopClip(currentlyPlaying);
						}
						catch(NullPointerException e){
						}
						clipLoader.playClip(musicPoke);
						currentlyPlaying = musicPoke;
					}
				}
				
				if(getTheLevel() == 3){
					img_background = 
							img_backgroundTest;
					
					if(showHelpMessage == true){
						g.setColor(Color.BLACK);
						g.drawString(helpMessage, WIDTH/2, HEIGHT/2);
					}
					
					while(!musicMart.isRunning()){
						try{
							clipLoader.stopClip(currentlyPlaying);
						}
						catch(NullPointerException e){
						}
						clipLoader.playClip(musicMart);
						currentlyPlaying = musicMart;
					}
				}
				
				if(getTheLevel() == 4){
					img_background = img_backgroundTest;
					
					while(!musicPoke2.isRunning()){
						try{
							clipLoader.stopClip(currentlyPlaying);
						}
						catch(NullPointerException e){
						}
						clipLoader.playClip(musicPoke2);
						currentlyPlaying = musicPoke2;
					}
				}
			
			if(thePlayer.isDead() == true){
				state = GameState.Dead;
			}
		
		//END HUD AREA
		}// end of if state == GameState.Game
		
		if(state == GameState.Dead) {
			dead.render(g);
		}
		
		if(state == GameState.MainMenu){
			mainMenu.render(g);
		}
		
		if(state == GameState.Shop){
			shop.render(g);
		}
		
		if(state == GameState.Paused) {
			paused.render(g);
			
		}
		
		if(state == GameState.LevelSelectMenu){
			if(getTheLevel() == 0){
				try{
					clipLoader.stopClip(currentlyPlaying);
				}
				catch(NullPointerException e){
				}
				currentlyPlaying = null;
			}
			
			g.fillRect(0, 0, getWidth(), getHeight());
			levelMenu.render(g);
			
			int entered = 0;
			try{
				entered = Integer.parseInt(keyInput.getEntered());
			}
			catch(NumberFormatException ex){
			}
			
			
			if(entered > 4 ){
				levelMenu.setError();
			}
			else{
				levelMenu.setFine();
			}
			
			if(entered > 0 && entered < 4){
				levelMenu.setFine();
			}
		}
		
		g.dispose();
		bs.show();
	}


	
	
	
	public static Texture getInstance()
	{
		return tex;
	}
	
	public static Player getThePlayer()
	{
		return thePlayer;
	}
	
	public static int getTheLevel()
	{
		return level;
	}
	
	public static void setTheLevel(int newLevel)
	{
		level = newLevel;
	}
	
	public static Camera getTheCamera()
	{
		return camera;
	}
	
	public static BufferedImage getLevel1()
	{
		return img_level1;
	}
	
	public static BufferedImage getLevel2()
	{
		return img_level2;
	}
	
	public static BufferedImage getLevel3()
	{
		return img_level3;
	}
	
	public static BufferedImage getLevel4()
	{
		return img_level4;
	}
	
	public static void setState(GameState newState)
	{
		state = newState;
	}
	
	public static GameState getGameState()
	{
		return state;
	}
	
	public static KeyInput getKeyInput()
	{
		return keyInput;
	}
	
	public static Handler getGameHandler()
	{
		return handler;
	}
	
	public static void setServer()
	{
		server = new Server();
	}
	
	public static void nullServer()
	{
		server = null;
	}
	
	public static void setClient()
	{
		client = new Client();
	}
	
	public static void nullClient()
	{
		client = null;
	}
	
	public static void setHelpMessage(String message)
	{
		if(!helpMessage.equals(message)){
			helpMessage = message;
		}
	}
	
	public static void clearHelpMessage()
	{
		if(!helpMessage.equals("")){
			helpMessage = "";
		}
	}
	
	public static void showHelpMessage(boolean value)
	{
		
		if(value == false && showHelpMessage == false){
		}
		
		else if(value == false && showHelpMessage == true){
			showHelpMessage = false;
		}
		
		else if(value == true && showHelpMessage == false){
			showHelpMessage = true;
		}
		
		else if(value == true && showHelpMessage == true){
		}
		
	}
	
	public static boolean getHelpMessageStatus()
	{
		return showHelpMessage;
	}
	
	public static int getHelpMessageLength()
	{
		return helpMessage.length();
	}
	
	public static MouseInput getMouse()
	{
		return mouse;
	}
	
	public static boolean getDebugModeState()
	{
		return debugMode;
	}

}