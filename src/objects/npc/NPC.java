package objects.npc;

import framework.GameObject;
import framework.ObjectID;
import game.Handler;

public abstract class NPC extends GameObject 
{
	private boolean shop;
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private NPCClass job;

	public NPC(float x, float y, ObjectID id, int health, int maxHealth,
			Handler handler, int width, int height, boolean shop,
			String name, NPCClass job) 
	{
		super(x, y, id, health, maxHealth, handler, width, height,
				ObjectID.NPC);
		this.shop = shop;
		this.job = job;
	}
	
	public boolean isShop()
	{
		return shop;
	}
	
	public void setShop(boolean shop)
	{
		this.shop = shop;
	}
}
