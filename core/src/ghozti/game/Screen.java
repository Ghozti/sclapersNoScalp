package ghozti.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class Screen implements com.badlogic.gdx.Screen {

    //screen
    Camera camera;
    Viewport viewport;
    //textures
    TextureAtlas atlas;
    SpriteBatch batch;
    //timing
    //World
    final float WORLD_HEIGHT = 1080;
    final float WORLD_WIDTH = 1920;
    //objects
    Player player;
    LinkedList<GraphicsCard> graphicsCards;

    public Screen(){

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
