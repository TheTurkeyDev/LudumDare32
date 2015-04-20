package com.turkey.ld32.entity;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.graphics.Draw2D;

public class EntityBossPig extends Entity
{
	private Texture texture = new Texture("bossPig.png");
	private int delay = 0;

	public EntityBossPig(Room room, float x, float y, int width, int height)
	{
		super(room, x, y, width, height);
		super.health = 100;
		super.maxHealth = 100;
	}

	public void render()
	{
		super.render();

		int dist = (int) Math.sqrt(Math.pow(this.y-room.playerY, 2) + Math.pow(this.x-room.playerX, 2));

		if(dist > 300)
		{
			if(room.playerX > this.x)
			{
				if(room.playerY > this.y)
				{
					this.xVel = this.yVel =(float) ((Math.sqrt(2)/2)*this.maxSpeed);
				}
				else if(room.playerY < this.y)
				{
					this.xVel =(float) ((Math.sqrt(2)/2)*this.maxSpeed);
					this.yVel = -(float) ((Math.sqrt(2)/2)*this.maxSpeed);
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
		}

		if(delay >=120)
		{
			float velX =  (float) (Math.cos(Math.toRadians(this.yaw))*5);
			float velY = (float) (Math.sin(Math.toRadians(this.yaw))*5);
			EntityMudBall mudBall = new EntityMudBall(room, x, y, 16, 16, velX, velY);
			room.addEntity(mudBall);
			delay = 0;
		}
		delay++;

		Draw2D.drawTextured(x, y, 32 , 32, texture, yaw+90);
	}

}
