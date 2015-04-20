package com.turkey.ld32.Map;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.entity.Entity;
import com.turkey.ld32.entity.EntityPlayer;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.util.Location;

public class DoorTile extends Tile
{
	private Texture texture = new Texture("doorTile.png");
	public void render(int x, int y)
	{
		Draw2D.drawTextured(x, y,texture);
	}
	
	public boolean onWalkedOn(Entity ent, Location loc)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) ent;
			player.setOnDoor();
			if(!player.canUseDoor())
				return true;
			
			if(loc.getX() == 0)
			{
				player.game.setCurrentRoom(player.getRoom().getAdjacentRoom(3));
				player.setLocation(22*Tile.WIDTH+35, 7*Tile.LENGTH);
			}
			else if(loc.getX() == 11)
			{
				if(loc.getY() == 0)
				{
					player.game.setCurrentRoom(player.getRoom().getAdjacentRoom(0));
					player.setLocation(12*Tile.WIDTH, 450);
				}
				else
				{
					player.game.setCurrentRoom(player.getRoom().getAdjacentRoom(2));
					player.setLocation(12*Tile.WIDTH, 15);
				}
			}
			else if(loc.getX() == 22)
			{
				player.game.setCurrentRoom(player.getRoom().getAdjacentRoom(1));
				player.setLocation(35, 7*Tile.LENGTH);
			}
		}
		return true;
	}
}
