package com.turkey.ld32.screen;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.weapon.Weapon;
import com.turkey.ld32.weapon.WeaponBodypart;
import com.turkey.ld32.weapon.WeaponSpoon;

public class ShopScreen
{

	private Texture buy  = new Texture("buyButton.png");
	private Texture equip  = new Texture("equipButton.png");

	private Weapon[] weapons;

	private Map<Rectangle, Weapon> buttons = new HashMap<Rectangle, Weapon>();

	private GameScreen game;

	public ShopScreen(GameScreen game)
	{
		this.game = game;
		weapons = new Weapon[]{
				new WeaponSpoon(GameScreen.player, 5),
				new WeaponBodypart(GameScreen.player, 10)
		};
	}

	public void loadScreen()
	{
		buttons.clear();
		for(int i = 0; i < weapons.length; i++)
		{
			if(weapons[i].owned)
				buttons.put(new Rectangle(200+(i*100),  355, equip.getWidth(), equip.getHeight()),weapons[i]);
			else
				buttons.put(new Rectangle(225+(i*100),  355, buy.getWidth(), buy.getHeight()),weapons[i]);
		}
	}

	public void render()
	{
		Draw2D.drawRect(140, 90, 520, 420, Color.RED, true);
		Draw2D.drawRect(150, 100, 500, 400, Color.BLACK, true);
		Draw2D.drawString(375, 450, "SHOP", 2, Color.RED);
		Draw2D.drawString(235, 200, "Item Cost: 200", 2, Color.RED);
		for(int i = 0; i < weapons.length; i++)
		{
			Draw2D.drawTextured(200+(i*100), 300, weapons[i].getIcon());
			if(weapons[i].owned)
			{
				Draw2D.drawTextured(200+(i*100), 225, this.equip);
			}
			else
			{
				Draw2D.drawTextured(225+(i*100), 225, this.buy);
			}
		}
	}


	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.Q)
		{
			game.shopOpen = false;
			game.running = true;
		}
		return false;
	}

	public boolean keyUp(int keycode)
	{
		return false;
	}


	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(button == Buttons.LEFT)
		{
			Point p = new Point(screenX, screenY);
			for(Rectangle r : buttons.keySet())
			{
				if(r.contains(p))
				{
					Weapon w = buttons.get(r);
					if(w.owned)
					{
						GameScreen.player.equipWeapon(w);
					}
					else
					{
						if(GameScreen.goldAmount >= 200)
						{
							w.owned = true;
							GameScreen.goldAmount -= 200;
						}
					}
				}
			}
			loadScreen();
		}
		return false;
	}

}
