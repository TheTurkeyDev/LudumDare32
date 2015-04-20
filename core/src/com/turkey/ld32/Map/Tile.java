package com.turkey.ld32.Map;

import com.turkey.ld32.entity.Entity;
import com.turkey.ld32.util.Location;


public class Tile
{
	public static final int WIDTH = 32;
	public static final int LENGTH = 32;

	public void render(int x, int y)
	{
		
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public boolean onWalkedOn(Entity ent, Location loc)
	{
		return !this.isSolid();
	}
}
