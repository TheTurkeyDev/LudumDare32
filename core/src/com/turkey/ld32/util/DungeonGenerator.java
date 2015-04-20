package com.turkey.ld32.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.turkey.ld32.game.Room;
import com.turkey.ld32.game.Room.RoomType;
import com.turkey.ld32.screen.GameScreen;

public class DungeonGenerator
{
	private static List<Room> rooms;
	private static Room starting;

	public static List<Room> Generatedungeon(GameScreen s)
	{
		rooms = new ArrayList<Room>();
		System.out.println("Initialize dugeon generation");
		initGen();
		System.out.println("Assigning dugeon rooms adjacent counterparts");
		assignAdjacent();
		System.out.println("Randomly removing rooms for randomness");
		removeRooms();
		System.out.println("Dungeon generation complete");
		s.setCurrentRoom(starting);
		GameScreen.startingRoom = starting;
		return rooms;
	}

	private static void initGen()
	{
		starting = new Room(0,0,RoomType.Starting);
		rooms.add(starting);

		/*
		 * North
		 */
		for(int x = 0; x < 7; x++)
		{
			for(int y = 0; y > -7; y--)
			{
				Room room = new Room(x-3,y-4, RoomType.Basic);
				rooms.add(room);
				if(x==3 && y==0)
				{
					starting.addAjacentRoom(room, 0);
					room.addAjacentRoom(starting,2);
				}
			}
		}
		rooms.add(new Room(0,-11,RoomType.Boss));

		/*
		 * East
		 */
		for(int x = 0; x < 7; x++)
		{
			for(int y = 0; y < 7; y++)
			{
				Room room = new Room(x+4,y-3, RoomType.Basic);
				rooms.add(room);
				if(x==0 && y==3)
				{
					starting.addAjacentRoom(room, 1);
					room.addAjacentRoom(starting,3);
				}
			}
		}
		rooms.add(new Room(11,0,RoomType.Boss));

		/*
		 * South
		 */
		for(int x = 0; x < 7; x++)
		{
			for(int y = 0; y < 7; y++)
			{
				Room room = new Room(x-3,y+4, RoomType.Basic);
				rooms.add(room);
				if(x==3 && y==0)
				{
					starting.addAjacentRoom(room, 2);
					room.addAjacentRoom(starting,0);
				}
			}
		}
		rooms.add(new Room(0,11,RoomType.Boss));

		/*
		 * west
		 */
		for(int x = 0; x > -7; x--)
		{
			for(int y = 0; y < 7; y++)
			{
				Room room = new Room(x-4,y-3, RoomType.Basic);
				rooms.add(room);
				if(x==0 && y==3)
				{
					starting.addAjacentRoom(room, 3);
					room.addAjacentRoom(starting,1);
				}
			}
		}
		rooms.add(new Room(-11,0,RoomType.Boss));

	}

	private static void assignAdjacent()
	{
		for(Room room: rooms)
		{
			for(Room room2: rooms)
			{
				int dir = isAdjacent(room.getLocation(),room2.getLocation());
				if(dir > -1)
				{
					room.addAjacentRoom(room2, dir);
					room2.addAjacentRoom(room, (dir+2)%4);
				}
			}
		}
	}

	private static void removeRooms()
	{
		Random r = new Random();
		for(int i = 0; i < 75; i++)
		{
			Room room = rooms.get(r.nextInt(rooms.size()));
			if(room.getType().equals(RoomType.Basic))
			{
				if(room.canDestroy())
				{
					room.destroyRoom();
					rooms.remove(room);
				}
			}
			else
			{
				i--;
			}
		}
	}

	private static int isAdjacent(Location loc1, Location loc2)
	{
		float xdist = loc2.getX()-loc1.getX();
		float ydist = loc2.getY()-loc1.getY();
		if(Math.abs(xdist) == 1)
		{
			if(Math.abs(ydist) == 0)
				return xdist < 0 ? 3 : 1;
		}
		else if(Math.abs(ydist) == 1)
		{
			if(Math.abs(xdist) == 0)
				return ydist < 0 ? 0 : 2;
		}
		return -1;
	}
}