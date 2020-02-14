package framework;

public enum ObjectID 
{
	//Player and general things
	Player(),
	NPC(),
	Enemy(),
	Item(),
	Relic(),
	Terrain(),
	Collectable(),
	Drop(),
	Misc(),
	
	//Items
	HookShot(),
	Arrow(),
	
	//Terrain and other features of a level
	Block(),
	Flag(),
	Sign(),
	
	//Drops and heart pieces
	Money(),
	Heart(),
	HeartPiece(),
	
	//Enemies
	Bat(),
	ShatteredHalf();
}

