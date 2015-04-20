package com.turkey.ld32;

import com.badlogic.gdx.ApplicationAdapter;
import com.turkey.ld32.Map.Tiles;
import com.turkey.ld32.graphics.Draw2D;
import com.turkey.ld32.screen.EndScreen;
import com.turkey.ld32.screen.GameScreen;
import com.turkey.ld32.screen.ScreenManager;
import com.turkey.ld32.screen.TitleScreen;

public class Main extends ApplicationAdapter {

	@Override
	public void create() 
	{
		new Draw2D();
		Tiles.loadTiles();
		
		ScreenManager sm = ScreenManager.instance;
		
		sm.addScreen(new TitleScreen());
		sm.addScreen(new GameScreen());
		sm.addScreen(new EndScreen());
		
		sm.setCurrentScreen("TitleScreen");
	}

	@Override
	public void render () 
	{
		Draw2D.updateCamera();
		ScreenManager.instance.getCurrentScreen().render();
	}
}
