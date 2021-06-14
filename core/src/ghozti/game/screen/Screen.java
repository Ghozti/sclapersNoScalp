package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ghozti.game.font.Font;
import ghozti.game.additionals.*;
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
    TextureAtlas gpuAtlas;
    SpriteBatch batch;
    Texture background = new Texture("22.jpg");
    Texture pausedT = new Texture("paused.png");

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

    //bools
    boolean paused = false;

    //music
    Music music;
    Music pauseMusic;

    //testing
    public static Font font;

    public Screen(){
        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets texture atlas and batch
        atlas = new TextureAtlas("general.atlas");
        batch = new SpriteBatch();

        //initializes the game objects

        graphicsCards.add(new GraphicsCard(WORLD_WIDTH,WORLD_HEIGHT));
        graphicsCards.add(new GraphicsCard(WORLD_WIDTH,WORLD_HEIGHT));
        graphicsCards.add(new GraphicsCard(WORLD_WIDTH,WORLD_HEIGHT));

        powerUps.add(new SpeedBoost(100,100));
        powerUps.add(new StockCrash(100,100));
        powerUps.add(new ScalperSlower(100,100));
        powerUps.add(new ScoreBooster(100,100));

        currentPowerUp = powerUps.get((int) ((Math.random() * (powerUps.size() - 0)) + 0));
        player = new Player(350,100,100,1000,1000);
        scalper = new Scalper(380,100,100,10,10);
        hud = new Hud();

        //plays the background music
        //2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav

        music = Gdx.audio.newMusic(Gdx.files.internal("2020-03-22_-_A_Bit_Of_Hope_-_David_Fesliyan.wav"));
        music.setVolume(1f);
        music.setLooping(true);
        if(MainMenu.musicOn) {
            music.play();
        }
        pauseMusic = Gdx.audio.newMusic(Gdx.files.internal("mixkit-quick-jump-arcade-game-239.wav"));
        pauseMusic.setVolume(.5f);

        font = new Font();
    }

    public void updateGame(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.P)){
            music.pause();
            if(MainMenu.musicOn) {
                pauseMusic.play();
            }
            paused = true;
            Gdx.graphics.setContinuousRendering(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R) && paused){
            music.play();
            if(MainMenu.musicOn) {
                music.play();
            }
            pauseMusic.play();
            paused = false;
            Gdx.graphics.setContinuousRendering(true);
        }

        player.move(delta);
        updatePowerUp(delta);
        updateCards();
    }

    public void renderPowerUp(Batch batch){
        currentPowerUp.draw(batch);
    }

    public void updatePowerUp(float delta){
        if(currentPowerUp.spawnNew){
            switch ((int) ((Math.random() * (5 - 1) + 1))){
                case 1:
                    currentPowerUp = powerUps.get(0);
                    break;
                case 2:
                    currentPowerUp = powerUps.get(1);
                    break;
                case 3:
                    currentPowerUp = powerUps.get(2);
                    break;
                case 4:
                    currentPowerUp = powerUps.get(3);
                    break;
            }
            currentPowerUp.reset();
        }
        currentPowerUp.detectCollision(scalper,player);
        currentPowerUp.startEffectTimer(delta,scalper,player);
        currentPowerUp.startSpawnerTimer(delta);
    }

    public void renderCards(Batch batch){

    }

    public void updateCards(){


    }

    @Override
    public void show() {

    }

    float currentInd;

    @Override
    public void render(float delta) {
        if (paused){
            delta = 0;
        }
        //System.out.println(Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClearColor(.128f,.128f,.128f,.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateGame(delta);

        batch.begin();

        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);

        renderCards(batch);
        renderPowerUp(batch);

        player.draw(batch,delta);
        scalper.draw(batch,delta,graphicsCards.get((int) currentInd));

        hud.render(batch,player.getScore(),scalper.getScore());

        if(paused) batch.draw(pausedT,590,100,800,800);

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

    //TODO make the cards work like powerups
}
