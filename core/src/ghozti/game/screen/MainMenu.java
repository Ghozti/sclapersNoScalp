package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.internal.vm.compiler.libgraal.LibGraal;

public class MainMenu implements Screen {

    public static boolean startGame = false;

    //textures
    //buttons
    Texture startBtn;
    Texture startBtnActive;
    Texture currentButton;
    Texture musicOnT, musicOffT,currentMusic;
    //background
    Texture background;
    //screen
    Camera camera;
    Viewport viewport;
    //batch
    SpriteBatch batch;
    //World
    public static final float WORLD_HEIGHT = 1080;
    public static final float WORLD_WIDTH = 1920;

    //music
    Music music;//current background music
    Music pauseMusic;//sound effect when the player clicks the mute button/start button
    boolean musicOn = true;

    public MainMenu() {

        //sets the background music
        music = Gdx.audio.newMusic(Gdx.files.internal("2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        //sets the sound effects
        pauseMusic = Gdx.audio.newMusic(Gdx.files.internal("mixkit-quick-jump-arcade-game-239.wav"));
        pauseMusic.setVolume(.5f);

        //sets the background texture
        background = new Texture("menubg.png");

        //sets the start button texture
        startBtn =  new Texture("start.png");
        //sets the start button when it's activated
        startBtnActive = new Texture("start2.png");

        //sets the music on texture
        musicOnT = new Texture("musicOn.png");
        //sets the music-off texture
        musicOffT = new Texture("musicOff.png");

        //the current music texture is on by default
        currentMusic = musicOnT;

        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets batch
        batch = new SpriteBatch();
    }

    public void update(){
        //updates the screen

        //booleans for if something is on/off
        boolean check1 = false,check2 = false;
        boolean musicCheck1 = false,musicCheck2 = false;

        //the mouse must be positioned in a certain coordinate range for both check1 and check2 to be true
        if (Gdx.input.getX() <= 1162 && Gdx.input.getX() >= 755) {
            check1 = true;
        }
        if (Gdx.input.getY() <= 823 && Gdx.input.getY() >= 736){
           check2 = true;
        }

        //if check1 and check2 are true the music will stop and the sound effect will play the static "startgame" boolean will be true (this is the start game functionality)
        if(check1 && check2) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                music.stop();
                pauseMusic.play();
                startGame = true;
            }
            //if the mouse hovers over the start game texture it will change it's texture into the blue or "active" button
            currentButton = startBtnActive;
        } else {
            currentButton = startBtn;
        }

        //same functionality as before except it is for the mute functionilty
        if (Gdx.input.getX() >= 1800 && Gdx.input.getX() <= 1880) {
            musicCheck1 = true;
        }
        if (Gdx.input.getY() >= 935 && Gdx.input.getY() <= 1006){
            musicCheck2 = true;
        }

        if(musicCheck1 && musicCheck2) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && musicOn){
                musicOn = false;
                music.setVolume(0);
                pauseMusic.play();
                currentMusic = musicOffT;
            }
            if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !musicOn){
                musicOn = true;
                music.setVolume(.5f);
                pauseMusic.play();
                currentMusic = musicOnT;
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //once the game starts nothing will be rendered
        if (!startGame) {
            Gdx.gl.glClearColor(.128f, .128f, .128f, .1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            update();
            batch.begin();
            batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(currentButton, 710, 50, 500, 500);
            batch.draw(currentMusic, 1800, 50, 100, 100);
            batch.end();
        }
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
