package ghozti.game.screen;

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
