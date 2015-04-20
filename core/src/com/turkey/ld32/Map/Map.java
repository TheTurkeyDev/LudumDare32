package com.turkey.ld32.Map;

import java.util.Random;

import com.turkey.ld32.game.Room;
import com.turkey.ld32.game.Room.RoomType;

public class Map
{
	private Tile[] tiles;

	private boolean generated = false;

	private int length;
	private int width;

	public void GenMap(Room room, int width, int length)
	{
		this.length = length;
		this.width = width;
		tiles = new Tile[length*width];
		Random r = new Random();
		for(int i = 0; i < tiles.length; i++)
		{
			if((i == 11 && room.getAdjacentRoom(0) != null) || (i == 161 && room.getAdjacentRoom(3) != null) || (i == 183 && room.getAdjacentRoom(1) != null) || (i == 333 && room.getAdjacentRoom(2) != null))
			{
				tiles[i] = Tiles.door;
			}
			else if(r.nextInt(100) > 2 || room.getType().equals(RoomType.Starting))
			{
				tiles[i] = Tiles.basic;
			}
			else
			{
				int j = r.nextInt(3);
				if(j == 0)
					tiles[i] = Tiles.rock;
				else if(j == 1)
					tiles[i] = Tiles.pit;
				else
					tiles[i] = Tiles.mud;
			}
		}
		generated = true;
	}

	public void render()
	{
		if(!this.generated)
			return;
		for(int y = 0; y < this.length; y++)
		{
			for(int x = 0; x < this.width; x++)
			{
				Tile t = tiles[(this.width*y)+x];
				t.render((x*Tile.WIDTH)+35, (y*Tile.LENGTH)+10);
			}
		}
	}

	public boolean canMoveTo(int x, int y, int width , int height)
	{
		int x1 = (x-35)/Tile.WIDTH;
		int x2 = (x-35+width)/Tile.WIDTH;
		int y1 = (y-10)/Tile.LENGTH;
		int y2 = (y-10+height)/Tile.LENGTH;

		Tile t1 = tiles[(this.width*y1)+x1];
		Tile t2 = tiles[(this.width*y1)+x2];
		Tile t3 = tiles[(this.width*y2)+x1];
		Tile t4 = tiles[(this.width*y2)+x2];

		if(t1.isSolid() || t2.isSolid() || t3.isSolid() || t4.isSolid())
			return false;
		return true;
	}

	public Tile getTileAt(int x, int y)
	{
		return tiles[(this.width*y)+x];
	}

	public boolean isGenerated()
	{
		return this.generated;
	}
}
