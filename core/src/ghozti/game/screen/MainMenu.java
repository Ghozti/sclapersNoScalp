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
    Music music;
    boolean musicOn;

    public MainMenu() {

        music = Gdx.audio.newMusic(Gdx.files.internal("2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        background = new Texture("menubg.jpg");

        startBtn =  new Texture("start.png");
        startBtnActive = new Texture("start2.png");

        musicOnT = new Texture("musicOn.png");
        musicOffT = new Texture("musicOff.png");

        currentMusic = musicOnT;

        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets batch
        batch = new SpriteBatch();
    }

    public void update(){
        boolean check1 = false,check2 = false;
        boolean musicCheck1,musicCheck2;

        if (Gdx.input.getX() <= 1162 && Gdx.input.getX() >= 755) {
            check1 = true;
        }
        if (Gdx.input.getY() <= 823 && Gdx.input.getY() >= 736){
           check2 = true;
        }

        if(check1 && check2) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                startGame = true;
                music.stop();
            }
            currentButton = startBtnActive;
        } else {
            currentButton = startBtn;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
