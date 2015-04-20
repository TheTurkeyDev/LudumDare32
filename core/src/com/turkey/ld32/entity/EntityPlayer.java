package com.turkey.ld32.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.Map.Tile;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.screen.GameScreen;
import com.turkey.ld32.screen.ScreenManager;
import com.turkey.ld32.util.Location;
import com.turkey.ld32.weapon.Weapon;
import com.turkey.ld32.weapon.WeaponSpoon;

public class EntityPlayer extends Entity
{
	private Texture texture = new Texture("Player.png");
	private boolean wdown;
	private boolean adown;
	private boolean sdown;
	private boolean ddown;
	
	private boolean isOnDoor = false;
	private boolean justSwitched = false;

	public GameScreen game;
	
	public int lives = 3;

	public EntityPlayer(GameScreen game, Room room, float x, float y, int width, int height)
	{
		super(room, x, y, width, height);
		this.game = game;
		super.maxSpeed = 3;
		super.health = 30;
		super.maxHealth = 30;
		weaponHeld = new WeaponSpoon(this, 5);
	}

	public void render()
	{
		super.render();
		double xDist = Gdx.input.getX() - x;
		double yDist = (Gdx.graphics.getHeight()-Gdx.input.getY()) - y;
		
		yaw = (int) Math.toDegrees(Math.atan2(yDist,xDist));
		
		this.xVel = 0;
		this.yVel = 0;
		
		if(this.adown && !this.wdown && !this.sdown)
		{
			this.xVel = -this.maxSpeed;
		}
		else if(this.adown && this.wdown)
		{
			this.xVel = -(int) ((Math.sqrt(2)/2)*this.maxSpeed);
			this.yVel = (int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		else if(this.adown && this.sdown)
		{
			this.xVel = -(int) ((Math.sqrt(2)/2)*this.maxSpeed);
			this.yVel = -(int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		else if(this.ddown && !this.wdown && !this.sdown)
		{
			this.xVel = this.maxSpeed;
		}
		else if(this.ddown && this.wdown)
		{
			this.xVel = (int) ((Math.sqrt(2)/2)*this.maxSpeed);
			this.yVel = (int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		else if(this.ddown && this.sdown)
		{
			this.xVel = (int) ((Math.sqrt(2)/2)*this.maxSpeed);
			this.yVel = -(int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		else if(this.wdown)
		{
			this.yVel = (int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		else if(this.sdown)
		{
			this.yVel = -(int) ((Math.sqrt(2)/2)*this.maxSpeed);
		}
		
		if(x+xVel < 35)
		{
			xVel=0;
		}
		else if(x+xVel+this.width > 770)
		{
			xVel=0;
		}
		if(y+yVel < 10)
		{
			yVel=0;
		}
		else if(y+yVel+this.height > 490)
		{
			yVel=0;
		}
		
		int x1 = (int) ((x-40+xVel)/Tile.WIDTH);
		int x2 = (int) ((x-40+xVel+this.width)/Tile.WIDTH);
		int y1 = (int) ((y-15+yVel)/Tile.LENGTH);
		int y2 = (int) ((y-15+yVel+this.height)/Tile.LENGTH);
		
		Tile t1 = room.getMap().getTileAt(x1, y1);
		Tile t2 = room.getMap().getTileAt(x2, y1);
		Tile t3 = room.getMap().getTileAt(x1, y2);
		Tile t4 = room.getMap().getTileAt(x2, y2);
		
		this.maxSpeed = 3;
		this.isOnDoor = false;
		if(t1.onWalkedOn(this, new Location(x1,y1)) && t2.onWalkedOn(this, new Location(x2,y1)) && t3.onWalkedOn(this, new Location(x1,y2)) && t4.onWalkedOn(this, new Location(x2,y2)))
		{
			x+=xVel;
			y+=yVel;
		}
		
		
		if(this.justSwitched && !this.isOnDoor)
			this.justSwitched = false;

		Draw2D.drawTextured(x, y, texture, yaw+90);
	}

	public void setRoom(Room room)
	{
		super.room = room;
		justSwitched = true;
		this.xVel = 0;
		this.yVel = 0;
	}

	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setOnDoor()
	{
		this.isOnDoor = true;
	}
	
	public boolean canUseDoor()
	{
		return !this.justSwitched;
	}
	
	public Weapon getCurrentWeapon()
	{
		return this.weaponHeld;
	}
	
	public void damage(int amount)
	{
		this.health-=amount;
		if(this.health<=0)
		{
			this.lives--;
			this.health = this.maxHealth;
			game.setCurrentRoom(GameScreen.startingRoom);
			this.x = 400;
			this.y = 250;
			if(lives == 0)
				ScreenManager.instance.setCurrentScreen("EndScreen");
		}
	}

	public void equipWeapon(Weapon w)
	{
		this.weaponHeld = w;
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.W || keycode == Keys.UP)
		{
			this.wdown = true;
			return true;
		}
		else if(keycode == Keys.A || keycode == Keys.LEFT)
		{
			this.adown = true;
			return true;
		}
		else if(keycode == Keys.S || keycode == Keys.DOWN)
		{
			this.sdown = true;
			return true;
		}
		else if(keycode == Keys.D || keycode == Keys.RIGHT)
		{
			this.ddown = true;
			return true;
		}
		return false;
	}

	public boolean keyUp(int keycode)
	{
		if(keycode == Keys.W || keycode == Keys.UP)
		{
			this.wdown = false;
			return true;
		}
		else if(keycode == Keys.A|| keycode == Keys.LEFT)
		{
			this.adown = false;
			return true;
		}
		else if(keycode == Keys.S || keycode == Keys.DOWN)
		{
			this.sdown = false;
			return true;
		}
		else if(keycode == Keys.D || keycode == Keys.RIGHT)
		{
			this.ddown = false;
			return true;
		}
		return false;
	}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(button == Buttons.LEFT)
		{
			if(weaponHeld!=null)
				weaponHeld.onActivate();
		}
		return true;
	}
}