package me.spaceshooter.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import me.spaceshooter.SpaceShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode((int)(320*2),(int)(640*2));
		new Lwjgl3Application(new SpaceShooterGame(), config);
	}
}
