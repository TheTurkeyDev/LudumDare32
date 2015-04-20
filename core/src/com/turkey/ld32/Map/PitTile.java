package com.turkey.ld32.Map;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.graphics.Draw2D;

public class PitTile extends Tile
{
	private Texture texture = new Texture("pitTile.png");
	
	public void render(int x, int y)
	{
		Draw2D.drawTextured(x, y, texture);
	}
}
