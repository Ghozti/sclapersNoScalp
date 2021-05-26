package ghozti.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ghozti.game.screen.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.width = 1080;
		config.foregroundFPS = 144;
		//config.fullscreen = true;
		//config.vSyncEnabled = true;
		new LwjglApplication(new Game(), config);
	}
}
