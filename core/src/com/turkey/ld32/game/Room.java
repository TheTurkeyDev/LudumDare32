package com.turkey.ld32.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.turkey.ld32.Map.Map;
import com.turkey.ld32.entity.Entity;
import com.turkey.ld32.entity.EntityBossPig;
import com.turkey.ld32.entity.EntityGoblin;
import com.turkey.ld32.entity.EntityPizzaMonster;
import com.turkey.ld32.util.Location;

public class Room
{
	private Room[] roomsAdjacent = new Room[4];

	private Location location;

	private RoomType type;

	private boolean playerInRoom = false;

	private Map map;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> toAdd = new ArrayList<Entity>();

	public float playerX;
	public float playerY;

	private Random r = new Random();

	public Room(int x, int y, RoomType type)
	{
		location = new Location(x,y);
		this.type = type;
		map = new Map();
	}

	public void addAjacentRoom(Room room, int dir)
	{
		this.roomsAdjacent[dir] = room;
	}

	public void removeAjacentRoom(int dir)
	{
		this.roomsAdjacent[dir] = null;
	}

	public Room getAdjacentRoom(int dir)
	{
		return this.roomsAdjacent[dir];
	}

	public boolean canDestroy()
	{
		for(int i = 0; i < this.roomsAdjacent.length;i++)
		{
			Room r = this.roomsAdjacent[i];
			if(r == null)
				continue;
			if(r.getType().equals(RoomType.Boss) || r.getType().equals(RoomType.Starting))
				return false;
			if(r.numberofAdjacentRooms() < 3)
				return false;
		}
		return true;
	}

	public void destroyRoom()
	{
		for(int i = 0; i < this.roomsAdjacent.length; i++)
		{
			Room room = this.roomsAdjacent[i];
			if(room != null)
				room.removeAjacentRoom((i+2)%4);
		}
	}


	public void loadRoom()
	{
		if(!map.isGenerated())
		{
			map.GenMap(this, 23, 15);

			if(this.type.equals(RoomType.Basic))
			{
				for(int i = 0; i < r.nextInt(10); i++)
				{
					int num = r.nextInt(2);
					if(num == 0)
						entities.add(new EntityPizzaMonster(this, r.nextInt(500) + 35, r.nextInt(300) + 15, 32, 32));
					else
						entities.add(new EntityGoblin(this, r.nextInt(500) + 35, r.nextInt(300) + 15, 32, 32));
				}
			}
			else if(this.type.equals(RoomType.Boss))
			{
				entities.add(new EntityBossPig(this, r.nextInt(500) + 35, r.nextInt(300) + 15, 64, 64));
			}
		}

		this.playerInRoom = true;
	}

	public void unloadRoom()
	{
		this.playerInRoom = false;
	}

	public void render(float px, float py)
	{
		this.playerX = px;
		this.playerY = py;
		map.render();

		List<Entity> toremove = new ArrayList<Entity>();
		for(Entity ent: entities)
		{
			if(ent.isAlive())
				ent.render();
			else
				toremove.add(ent);
		}
		entities.removeAll(toremove);
		entities.addAll(toAdd);
		toAdd.clear();
		toremove.clear();
	}

	public int numberofAdjacentRooms()
	{
		int i = 0;
		for(Room r: this.roomsAdjacent)
			if(r!=null)
				i++;
		return i;
	}

	public Location getLocation()
	{
		return this.location;
	}

	public boolean isPlayerInRoom()
	{
		return this.playerInRoom;
	}

	public Map getMap()
	{
		return this.map;
	}

	public List<Entity> getEntites()
	{
		return this.entities;
	}

	public void addEntity(Entity ent)
	{
		toAdd.add(ent);
	}

	public RoomType getType()
	{
		return this.type;
	}

	public enum RoomType
	{
		Basic,
		Starting,
		Boss;
	}
}