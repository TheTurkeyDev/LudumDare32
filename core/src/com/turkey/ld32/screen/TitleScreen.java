package com.turkey.ld32.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.graphics.Draw2D;

public class TitleScreen extends Screen
{
	private Texture screen = new Texture("TitleScreen.png");
	
	public TitleScreen()
	{
		super("TitleScreen");
	}
	
	public void render()
	{
		Draw2D.drawTextured(0, 0, screen);
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.SPACE)
			ScreenManager.instance.setCurrentScreen("GameScreen");
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
