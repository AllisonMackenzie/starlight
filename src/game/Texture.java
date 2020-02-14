package game;

import java.awt.image.BufferedImage;

import framework.BufferedImageLoader;

public class Texture 
{
	SpriteSheet bs, ps, bats, hearts, heartP, rupee;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage bat_sheet = null;
	private BufferedImage heart_sheet = null;
	private BufferedImage heartP_sheet = null;
	private BufferedImage rupee_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[9];
	public BufferedImage[] player = new BufferedImage[6];
	public BufferedImage[] enemy = new BufferedImage[1];
	public BufferedImage[] misc = new BufferedImage[3];
	public BufferedImage[] door = new BufferedImage[1];
	public BufferedImage[] furniture = new BufferedImage[1];

	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			bat_sheet = loader.loadImage("/bat.png");
			heart_sheet = loader.loadImage("/heartz.png");
			heartP_sheet = loader.loadImage("/Heart Container.png");
			rupee_sheet = loader.loadImage("/Rupee.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		bats = new SpriteSheet(bat_sheet);
		hearts = new SpriteSheet(heart_sheet);
		heartP = new SpriteSheet(heartP_sheet);
		rupee = new SpriteSheet(rupee_sheet);
		
		getTextures();
	}
	
	/**
	 * Here is how you grab images from block_sheet.png!!!!
	 * Also works for player!
	 */
	private void getTextures()
	{
		block[0] = bs.grabImage(4, 1, 32, 32); //Grass block
		block[1] = bs.grabImage(3, 1, 32, 32); //Dirt Block
		block[2] = bs.grabImage(4, 2, 32, 32); //Gravel Block
		block[3] = bs.grabImage(5, 2, 32, 32); //Wood Pillar
		block[4] = bs.grabImage(6, 2, 32, 32); //Wood Top
		block[5] = bs.grabImage(4,  3, 32, 32); //Sign
		block[6] = bs.grabImage(2, 2, 32, 32); //Grass right
		block[7] = bs.grabImage(4, 2, 32, 32); //Grass Left
		block[8] = bs.grabImage(3, 2, 32, 32); //Grass Down
		
		player[0] = ps.grabImage2(8, 7, 32, 32); //idle frame right
		player[1] = ps.grabImage2(9, 7, 32, 32); //walk 1 right
		player[2] = ps.grabImage2(7, 7, 32, 32); //walk 2 right
		player[3] = ps.grabImage2(8, 6, 32, 32); //idle frame left
		player[4] = ps.grabImage2(9, 6, 32, 32); //walk 1 left
		player[5] = ps.grabImage2(7, 6, 32, 32); //walk 2 right
		
		enemy[0] = bats.grabImage(1, 1, 32, 32); //Bat
		
		furniture[0] = bs.grabImage(1, 5, 32, 32);
		
		misc[0] = hearts.grabImage(1, 1, 16, 16); // pick up heart
		misc[1] = heartP.grabImage(1, 1, 32, 32); // heart piece
		misc[2] = rupee.grabImage(1, 1, 32, 32); //rupee
		
		door[0] = bs.grabImage(2, 3, 32, 64);		
	}

}
