package com.turkey.ld32.screen;

import com.badlogic.gdx.InputProcessor;

public abstract class Screen implements InputProcessor
{
	private String name;
	
	public Screen(String name)
	{
		this.name = name;
	}
	
	public void render()
	{
		
	}
	
	public void loadScreen()
	{
		
	}
	
	public String getName()
	{
		return this.name;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
