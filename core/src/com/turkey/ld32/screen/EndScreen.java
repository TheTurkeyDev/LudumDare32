package com.turkey.ld32.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.graphics.Draw2D;

public class EndScreen extends Screen
{
	
	private Texture screen = new Texture("EndScreen.png");
	
	public EndScreen()
	{
		super("EndScreen");
	}
	
	public void render()
	{
		Draw2D.drawTextured(0, 0, screen);
		Draw2D.drawString(350, 300, "Score", 2, Color.WHITE);
		Draw2D.drawString(350, 250, ""+ GameScreen.goldAmount, 2, Color.WHITE);
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.SPACE)
			ScreenManager.instance.setCurrentScreen("TitleScreen");
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

}
