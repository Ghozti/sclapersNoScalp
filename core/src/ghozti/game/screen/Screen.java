package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ghozti.game.entities.graphicsCard.GraphicsCard;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Screen implements com.badlogic.gdx.Screen {

    //screen
    Camera camera;
    Viewport viewport;

    //textures
    TextureAtlas atlas;
    SpriteBatch batch;
    Texture background = new Texture("22.jpg");

    //timing

    //World
    public static final float WORLD_HEIGHT = 1080;
    public static final float WORLD_WIDTH = 1920;

    //objects
    Player player;
    //GraphicsCard card;
    Scalper scalper;
    ArrayList<GraphicsCard> graphicsCards = new ArrayList<>();

    public Screen(){
        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets texture atlas and batch
        atlas = new TextureAtlas("unnamed.atlas");
        batch = new SpriteBatch();

        //initializes the game objects
        //card = new GraphicsCard(atlas.findRegion("3070"),100,50,WORLD_WIDTH,WORLD_HEIGHT);
        for (int i = 0; i < 3; i++) {
            graphicsCards.add(new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
        }

        player = new Player(atlas.findRegion("amogus"),275,100,100,1000,1000);
        scalper = new Scalper(270,100,100,10,10);
    }

    public void renderCards(Batch batch){
        if(graphicsCards.get(0) != null) graphicsCards.get(0).draw(batch);
        if(graphicsCards.get(1) != null) graphicsCards.get(1).draw(batch);
        if(graphicsCards.get(2) != null) graphicsCards.get(2).draw(batch);
    }

    public void createCards(){
        detectCollision();

        if(graphicsCards.get(0) == null){
            graphicsCards.set(0, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
        }
        if(graphicsCards.get(1) == null){
            graphicsCards.set(1, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
        }
        if(graphicsCards.get(2) == null){
            graphicsCards.set(2, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
        }
    }

    public void detectCollision(){
        if(graphicsCards.get(0).collides(player,scalper)) graphicsCards.set(0,null);
        if(graphicsCards.get(1).collides(player,scalper)) graphicsCards.set(1,null);
        if(graphicsCards.get(2).collides(player,scalper)) graphicsCards.set(2,null);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.128f,.128f,.128f,.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        createCards();

        batch.begin();
        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);

        renderCards(batch);
        player.draw(batch,delta);
        //scalper.draw(batch,delta,card);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);//tells the viewport to update accordingly
        batch.setProjectionMatrix(camera.combined);
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
