package ghozti.game;

public class Game extends com.badlogic.gdx.Game {

	Screen screen = new Screen();

	@Override
	public void create() {
		setScreen(screen);
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
