package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ghozti.game.additionals.PowerUp;
import ghozti.game.additionals.ScalperSlower;
import ghozti.game.additionals.SpeedBoost;
import ghozti.game.additionals.StockCrash;
import ghozti.game.entities.graphicsCard.GraphicsCard;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.entities.player.hud.Hud;
import java.util.ArrayList;

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

    //hud
    Hud hud;

    //objects
    Player player;
    Scalper scalper;
    ArrayList<GraphicsCard> graphicsCards = new ArrayList<>();
    ArrayList<PowerUp> powerUps = new ArrayList<>();
    PowerUp currentPowerUp;

    public Screen(){
        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets texture atlas and batch
        atlas = new TextureAtlas("general.atlas");
        batch = new SpriteBatch();

        //initializes the game objects
        for (int i = 0; i < 3; i++) {
            graphicsCards.add(new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
        }
        powerUps.add(new SpeedBoost(100,100));
        powerUps.add(new StockCrash(100,100));
        powerUps.add(new ScalperSlower(100,100));

        currentPowerUp = powerUps.get((int) ((Math.random() * (powerUps.size() - 0)) + 0));
        player = new Player(375,100,100,1000,1000);
        scalper = new Scalper(375,100,100,10,10);
        hud = new Hud();

        //plays the background music
        //2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav

        Music music = Gdx.audio.newMusic(Gdx.files.internal("2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        //TODO **************COMMENT EVERYWHERE*************
    }

    public void renderPowerUp(Batch batch){
        currentPowerUp.draw(batch);
    }

    public void updatePowerUp(float delta){
        if(currentPowerUp.spawnNew){
            switch ((int) ((Math.random() * (3 - 1) + 1))){
                case 1:
                    currentPowerUp = powerUps.get(0);
                    break;
                case 2:
                    currentPowerUp = powerUps.get(1);
                    break;
                case 3:
                    currentPowerUp = powerUps.get(2);
                    break;
            }
            currentPowerUp.reset();
        }
        currentPowerUp.detectCollision(scalper,player);
        currentPowerUp.startEffectTimer(delta,scalper,player);
        currentPowerUp.startSpawnerTimer(delta);
    }

    public void renderCards(Batch batch){
        if(graphicsCards.get(0) != null) graphicsCards.get(0).draw(batch);
        if(graphicsCards.get(1) != null) graphicsCards.get(1).draw(batch);
        if(graphicsCards.get(2) != null) graphicsCards.get(2).draw(batch);
    }

    float currentInd;

    public void createCards(){
        detectCollision();

        if(graphicsCards.get(0) == null){
            graphicsCards.set(0, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
            currentInd = (float) ((Math.random() * (2 - 0) + 0));
        }
        if(graphicsCards.get(1) == null){
            graphicsCards.set(1, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
            currentInd = (float) ((Math.random() * (2 - 0) + 0));
        }
        if(graphicsCards.get(2) == null){
            graphicsCards.set(2, new GraphicsCard(atlas,100,50,WORLD_WIDTH,WORLD_HEIGHT));
            currentInd = (float) ((Math.random() * (2 - 0) + 0));
        }
    }

    public void detectCollision(){
        if(graphicsCards.get(0).collides(player,scalper)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("unlock.wav"));
            sound.play(1.0f);
            graphicsCards.set(0,null);
        }
        if(graphicsCards.get(1).collides(player,scalper)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("unlock.wav"));
            sound.play(1.0f);
            graphicsCards.set(1,null);
        }
        if(graphicsCards.get(2).collides(player,scalper)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("unlock.wav"));
            sound.play(1.0f);
            graphicsCards.set(2,null);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //System.out.println(Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClearColor(.128f,.128f,.128f,.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.move(delta);
        updatePowerUp(delta);
        createCards();

        batch.begin();

        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);

        renderCards(batch);
        renderPowerUp(batch);
        player.draw(batch,delta);
        scalper.draw(batch,delta,graphicsCards.get((int) currentInd));
        hud.render(batch,player.getScore(),scalper.getScore());

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
