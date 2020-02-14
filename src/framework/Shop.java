package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import game.Game;

public class Shop 
{
	private static int shopID = 4;
	private String shopName = "";
	private HashMap<String, Integer> test = new HashMap<>();
	private Collection col;
	private Iterator it;
	
	public void render(Graphics g)
	{
		Font font0 = new Font("arial", Font.BOLD, 50);
		Font font1 = new Font("arial", Font.ITALIC, 20);
		Font font2 = new Font("arial", Font.PLAIN, 20);
		
		switch(shopID){
			case 3:
				shopName = "Ray's Cosmetic Shop - Press [ Escape ] to "
						+ "exit this screen.";
				test.clear();
				test.put("Item 1: ", 10);
				test.put("Item 2: ", 20);
				test.put("Item 3: ", 30);
				test.put("Item 4: ", 40);
				test.put("Item 5: ", 50);
				test.put("Item 6: ", 60);
				test.put("Item 7: ", 70);
				test.put("Item 8: ", 80);
				test.put("Item 9: ", 90);
				test.put("Item 10: ", 100);
				break;
			
			case 4:
				shopName = "Missy's Cosmetic Shop  - Press [ Escape ] to "
						+ "exit this screen.";
				test.clear();
				test.put("Item 1: ", 11);
				test.put("Item 2: ", 21);
				test.put("Item 3: ", 31);
				test.put("Item 4: ", 41);
				test.put("Item 5: ", 51);
				test.put("Item 6: ", 61);
				test.put("Item 7: ", 71);
				test.put("Item 8: ", 81);
				test.put("Item 9: ", 91);
				test.put("Item 10: ", 111);
				break;
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.setFont(font2);
		g.drawString(shopName, 10, 30);
		
		
		col = test.values();
		it = col.iterator();
		
		//System.out.println(col);
		//while(it.hasNext()){
			//for(int i = 200; i <= 2000; i += 20){
				//String item = it.next();
				//g.drawString(item, 30, i);
			//}
		//}
	}
	
	public static void setShopID(int ID)
	{
		shopID = ID;
	}
}
