package com.turkey.ld32.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class ScreenManager
{
	public static ScreenManager instance = new ScreenManager();
	
	private Screen currentScreen;

	public List<Screen> screens = new ArrayList<Screen>();
	
	public void addScreen(Screen s)
	{
		screens.add(s);
	}
	
	public void setCurrentScreen(Screen s)
	{
		this.currentScreen = s;
		currentScreen.loadScreen();
		Gdx.input.setInputProcessor(currentScreen);
	}
	
	public boolean setCurrentScreen(String name)
	{
		for(Screen s: screens)
		{
			if(s.getName().equalsIgnoreCase(name))
			{
				this.currentScreen = s;
				currentScreen.loadScreen();
				Gdx.input.setInputProcessor(currentScreen);
				return true;
			}
		}
		return false;
	}
	
	public Screen getCurrentScreen()
	{
		return this.currentScreen;
	}
}
