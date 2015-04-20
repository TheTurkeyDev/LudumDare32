package com.turkey.ld32.entity;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.screen.GameScreen;

public class EntityPepperoni extends Entity
{
	private float xVel;
	private float yVel;

	private Texture texture = new Texture("pepperoni.png");

	public EntityPepperoni(Room room, float x, float y, int width, int height, float xVel, float yVel)
	{
		super(room, x, y, width, height);
		this.xVel = xVel;
		this.yVel = yVel;
	}

	public void render()
	{
		this.x+=this.xVel;
		this.y+=this.yVel;
		
		if(x < 0 || x > 800 || y > 600  || y < 0)
		{
			this.kill();
			return;
		}

		float xDist = room.playerX-x;
		float yDist = room.playerY-y;

		double dist = Math.sqrt(Math.pow(yDist, 2) + Math.pow(xDist, 2));

		if(dist < 16)
		{
			GameScreen.player.damage(5);
			this.kill();
			return;
		}

		Draw2D.drawTextured(x, y, texture);
	}

}
