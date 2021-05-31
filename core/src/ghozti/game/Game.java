package ghozti.game;

import ghozti.game.screen.Screen;

public class Game extends com.badlogic.gdx.Game {

	@Override
	public void create() {
		setScreen(new Screen());
	}

	@Override
	public void render() {
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
