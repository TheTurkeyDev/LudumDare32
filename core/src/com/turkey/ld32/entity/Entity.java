package com.turkey.ld32.entity;

import com.badlogic.gdx.graphics.Color;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.screen.GameScreen;
import com.turkey.ld32.weapon.Weapon;

public class Entity
{
	protected boolean isAlive = true;
	protected int health;
	protected int maxHealth;
	
	protected Room room;
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	public int yaw = 0;
	
	protected Weapon weaponHeld;
	
	protected float maxSpeed = 2;
	protected float xVel = 0;
	protected float yVel = 0;
	
	public Entity(Room room, float x, float y, int width ,int height)
	{
		this.room = room;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render()
	{
		if(weaponHeld != null)
			weaponHeld.render();
		
		double xDist = room.playerX - x;
		double yDist = room.playerY - y;
		
		yaw = (int) Math.toDegrees(Math.atan2(yDist,xDist));
		
		Draw2D.drawRect(x+5, y+height, 25, 5, Color.RED, true);
		Draw2D.drawRect(x+5, y+height, (int)(((double)this.health/(double)this.maxHealth)*25), 5, Color.GREEN, true);
	}
	
	public boolean isAlive()
	{
		return this.isAlive;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Room getRoom()
	{
		return this.room;
	}

	public void kill()
	{
		this.isAlive = false;
	}
	
	public void damage(int amount)
	{
		this.health-=amount;
		if(this.health<=0)
		{
			this.kill();
			GameScreen.goldAmount+=this.maxHealth;
		}
	}

	public void setMaxSpeed(int i)
	{
		this.maxSpeed = i;
	}
}
