package game;

import java.util.HashMap;

import items.Item;

public final class Inventory 
{
	private HashMap<String, Item> inventory = new HashMap<String, Item>();
	
	public Inventory()
	{
				
	}
	
	public HashMap<String, Item> getInventory()
	{
		return inventory;
	}
	
	public void add(String key, Item value)
	{
		try{
			inventory.put(key, value);
		}
		catch(NullPointerException e){
			System.out.println("Inventory does not exist?!");
		}
	}
}
