package com.turkey.ld32.Map;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.entity.Entity;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.util.Location;

public class MudTile extends Tile
{
	private Texture texture = new Texture("mudTile.png");
	
	public void render(int x, int y)
	{
		Draw2D.drawTextured(x, y, texture);
	}
	
	public boolean onWalkedOn(Entity ent, Location loc)
	{
		ent.setMaxSpeed(2);
		return true;
	}
}
