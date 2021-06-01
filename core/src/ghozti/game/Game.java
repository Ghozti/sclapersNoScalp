package ghozti.game;

import ghozti.game.screen.MainMenu;
import ghozti.game.screen.Screen;

public class Game extends com.badlogic.gdx.Game {

	boolean gameStarted = false;

	@Override
	public void create() {
		setScreen(new MainMenu());
	}

	@Override
	public void render() {
		if (MainMenu.startGame && !gameStarted) {
			gameStarted = true;
			setScreen(new Screen());
		}
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		screen.dispose();
	}

	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}
}
