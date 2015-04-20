package com.turkey.ld32.entity;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.screen.GameScreen;

public class EntityGoblin extends Entity
{
	private Texture texture = new Texture("goblin.png");

	private int delay = 0;
	private int delayMax = 0;
	
	public EntityGoblin(Room room, float x, float y, int width, int height)
	{
		super(room, x, y, width, height);
		super.health = 3;
		super.maxHealth = 3;
		delayMax = 45;
		super.setMaxSpeed(3);
	}
	
	public void render()
	{
		super.render();
		
		if(room.playerX > this.x)
		{
			if(room.playerY > this.y)
			{
				this.xVel = this.yVel =(int) ((Math.sqrt(2)/2)*this.maxSpeed);
			}
			else if(room.playerY < this.y)
			{
				this.xVel =  (float) ((Math.sqrt(2)/2)*this.maxSpeed);
				this.yVel = - (float)(Math.sqrt(2)/2)*this.maxSpeed;
			}
			else
			{
				this.xVel = this.maxSpeed;
				this.yVel = 0;
			}
		}
		else if(room.playerX < this.x)
		{
			if(room.playerY > this.y)
			{
				this.xVel = -(float) ((Math.sqrt(2)/2)*this.maxSpeed);
				this.yVel = (float) ((Math.sqrt(2)/2)*this.maxSpeed);
			}
			else if(room.playerY < this.y)
			{
				this.xVel = -(float) ((Math.sqrt(2)/2)*this.maxSpeed);
				this.yVel = -(float) ((Math.sqrt(2)/2)*this.maxSpeed);
			}
			else
			{
				this.xVel = -this.maxSpeed;
				this.yVel = 0;
			}
		}
		else
		{
			if(room.playerY > this.y)
			{
				this.xVel = 0;
				this.yVel = this.maxSpeed;
			}
			else if(room.playerY < this.y)
			{
				this.xVel = 0;
				this.yVel = -this.maxSpeed;
			}
			else
			{
				this.xVel = this.yVel = 0;
			}
		}
		
		this.x+=this.xVel;
		this.y+=this.yVel;
		
		if(delay >= delayMax)
		{
			int dist = (int) Math.sqrt(Math.pow(this.y-room.playerY, 2) + Math.pow(this.x-room.playerX, 2));
			if(dist < 16)
			{
				GameScreen.player.damage(3);
			}
			delay = 0;
		}
		delay++;
		
		Draw2D.drawTextured(x, y, texture, yaw+90);
	}
}
