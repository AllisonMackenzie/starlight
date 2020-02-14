package items;

import framework.GameObject;
import framework.ObjectID;
import game.Handler;

public abstract class Item extends GameObject 
{
	public Item(float x, float y, ObjectID id, int health, int maxHealth,
			Handler handler, int width, int height) {
		super(x, y, id, health, maxHealth, handler, width, height,
				ObjectID.Item);
	}

}
