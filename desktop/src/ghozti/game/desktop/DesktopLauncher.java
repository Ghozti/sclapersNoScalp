package ghozti.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ghozti.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.width = 1080;
		config.foregroundFPS = 144;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		config.resizable = false;
		config.title = "Silicon Crisis";
		new LwjglApplication(new Game(), config);
	}

	//TODO optimze the game for higher resolutions, refactor, comment and replace textures with texture regions in Screen.java and MainMenu.java
	//TODO do whatever the hell i did in amin menu for the aspect ratios, formula: worldWidth/height/x = width/height/x/y
}
