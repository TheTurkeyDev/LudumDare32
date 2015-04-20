package com.turkey.ld32.Map;

public class Tiles
{
	public static Tile basic;
	public static Tile door;
	public static Tile rock;
	public static Tile pit;
	public static Tile mud;
	
	public static void loadTiles()
	{
		basic = new BasicTile();
		door = new DoorTile();
		rock = new RockTile();
		pit = new PitTile();
		mud = new MudTile();
	}
}
