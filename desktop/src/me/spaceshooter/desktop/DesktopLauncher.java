package me.spaceshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import me.spaceshooter.SpaceShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SpaceGame";
		config.height = 640*2;
		config.width = 360*2;
		new LwjglApplication(new SpaceShooterGame(), config);
	}
}
