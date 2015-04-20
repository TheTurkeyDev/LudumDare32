package com.turkey.ld32.screen;

import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.entity.EntityPlayer;
import com.turkey.ld32.game.Room;
import com.turkey.ld32.game.Room.RoomType;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.util.DungeonGenerator;

public class GameScreen extends Screen
{

	private List<Room> rooms;
	private Room currentRoom;
	public static Room startingRoom;

	public static EntityPlayer player;

	private Texture heart = new Texture("heart.png");
	private Texture gold = new Texture("gold.png");
	
	public static int goldAmount = 0;

	public boolean running = true;
	
	private ShopScreen shop;
	public boolean shopOpen = false;

	public GameScreen()
	{
		super("GameScreen");
	}


	public void loadScreen()
	{
		running = true;
		player = new EntityPlayer(this,null,400,250,32,32);
		rooms = DungeonGenerator.Generatedungeon(this);
		goldAmount = 0;
		shop = new ShopScreen(this);
		//Gdx.input.setCursorCatched(true);
	}

	public void render()
	{
		if(shopOpen)
		{
			shop.render();
			return;
		}
		if(!running)
		{
			Draw2D.drawString(350, 300, "-Paused-", 2, Color.RED);
			return;
		}
		Draw2D.drawRect(0, 0, 800, 600, Color.BLACK, true);

		this.currentRoom.render(GameScreen.player.getX(), GameScreen.player.getY());
		player.render();

		Draw2D.drawRect(0, 500, 800, 175, Color.WHITE, true);

		Draw2D.drawString(30, 600, "Lives", 2, Color.BLACK);
		for(int i = 0; i< player.lives;i++)
		{
			Draw2D.drawTextured(10+(i*40), 525, heart);
		}
		Draw2D.drawRect(140, 502, 135, 100, Color.BLACK, true);
		Draw2D.drawString(150, 600, "Weapon", 2, Color.WHITE);
		if(player.getCurrentWeapon()!=null)
		{
			Draw2D.drawTextured(175, 510, player.getCurrentWeapon().getIcon());
		}
		Draw2D.drawString(315, 600, "Gold", 2, Color.BLACK);
		Draw2D.drawString(350, 570, "" + goldAmount, 2, Color.BLACK);
		Draw2D.drawTextured(330, 510, gold);

		//Render MiniMap
		Draw2D.drawRect(700, 502, 135, 100, Color.BLACK, true);
		for(Room r: this.rooms)
		{
			if(r.isPlayerInRoom())
				Draw2D.drawRect((r.getLocation().getX()*4)+750, (r.getLocation().getY()*4)+550, 4, 4, Color.RED, true);
			else if(r.getType().equals(RoomType.Boss))
				Draw2D.drawRect((r.getLocation().getX()*4)+750, (r.getLocation().getY()*4)+550, 4, 4, Color.GREEN, true);
			else
				Draw2D.drawRect((r.getLocation().getX()*4)+750, (r.getLocation().getY()*4)+550, 4, 4, Color.BLUE, true);
		}
	}


	public Room getCurrentRoom()
	{
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom)
	{
		if(this.currentRoom != null)
			this.currentRoom.unloadRoom();
		this.currentRoom = currentRoom;
		this.currentRoom.loadRoom();
		player.setRoom(this.currentRoom);
	}


	@Override
	public boolean keyDown(int keycode)
	{
		if(shopOpen)
		{
			shop.keyDown(keycode);
		}
		else if(keycode == Keys.ESCAPE)
		{
			running = !running;
			//Gdx.input.setCursorCatched(running);
		}
		else if(keycode == Keys.Q)
		{
			this.shopOpen = true;
			running = false;
			shop.loadScreen();
		}

		if(!running)
			return false;

		return player.keyDown(keycode);
	}


	@Override
	public boolean keyUp(int keycode)
	{
		if(!running)
			return false;
		return player.keyUp(keycode);
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(this.shopOpen)
			shop.touchDown(screenX, screenY, pointer, button);
		if(!running)
			return false;
		return player.touchDown(screenX, screenY, pointer, button);
	}
}
