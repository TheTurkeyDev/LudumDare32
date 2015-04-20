package com.turkey.ld32.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.turkey.ld32.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "LudumDare 32";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new Main(), config);
	}
}
