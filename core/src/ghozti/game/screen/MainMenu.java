package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {

    public static boolean startGame = false;
    public boolean showCredits = false;

    //textures
    //buttons
    Texture startBtn;
    Texture startBtnActive;
    Texture currentButton;
    Texture musicOnT, musicOffT,currentMusic;
    Texture credits,creditsActive, currentCredits;
    Texture goBack1, goBack2, currentGoBack;
    //title
    Texture title;
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

    //fonts
    BitmapFont font;
    float verticalMargin,leftx,rightx,centerx,row1Y,row2Y,sectionWidth;

    BitmapFont font2;

    public MainMenu() {

        //sets the background music
        music = Gdx.audio.newMusic(Gdx.files.internal("2019-01-02_-_8_Bit_Menu_-_David_Renda_-_FesliyanStudios.com.wav"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        //sets the sound effects
        pauseMusic = Gdx.audio.newMusic(Gdx.files.internal("mixkit-quick-jump-arcade-game-239.wav"));
        pauseMusic.setVolume(.5f);

        //sets the title
        title = new Texture("title.png");

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

        //sets credits
        credits = new Texture("credits1.png");
        creditsActive = new Texture("credits2.png");
        currentCredits = credits;

        //sets the go back button in credits
        goBack1 = new Texture("goBack1.png");
        goBack2 = new Texture("goBack2.png");
        currentGoBack = goBack1;

        //the current music texture is on by default
        currentMusic = musicOnT;

        //sets camera, viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //sets batch
        batch = new SpriteBatch();


        //font stuff
        //creates a bitmapFont from our file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("NugoSansLight-9YzoK.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //sets font1 attributes
        fontParameter.size = 172;
        fontParameter.borderWidth = 3.6f;
        fontParameter.color = new Color(1,1,1,1);
        fontParameter.borderColor = new Color(1,1,1,1);

        font = fontGenerator.generateFont(fontParameter);

        //sets font2 attributes
        fontParameter2.size = 172;
        fontParameter2.borderWidth = 3.6f;
        fontParameter2.color = new Color(1,1,1,1);
        fontParameter2.borderColor = new Color(1,1,1,1);

        font2 = fontGenerator.generateFont(fontParameter2);

        //sets scale of font
        font.getData().setScale(.3f);
        font2.getData().setScale(.21f);

        //calculates hud margins,etc
        verticalMargin = font.getCapHeight()/2;
        leftx = verticalMargin;
        rightx = ghozti.game.screen.Screen.WORLD_WIDTH * 2 / 3 - leftx;
        centerx = ghozti.game.screen.Screen.WORLD_WIDTH/3;
        row1Y = ghozti.game.screen.Screen.WORLD_HEIGHT - verticalMargin;
        row2Y = row1Y - verticalMargin - font.getCapHeight();
        sectionWidth = ghozti.game.screen.Screen.WORLD_WIDTH/3;
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

        boolean creditsCheck1 = false,creditsCheck2 = false;

        //same functionality as before except it is for the credits screen
        if (Gdx.input.getX() >= 786 && Gdx.input.getX() <= 1125) {
            creditsCheck1 = true;
        }
        if (Gdx.input.getY() >= 900 && Gdx.input.getY() <= 959){
            creditsCheck2 = true;
        }

        if (creditsCheck1 && creditsCheck2){
            currentCredits = creditsActive;
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                showCredits = true;
                pauseMusic.play();
            }
        }else {
            currentCredits = credits;
        }

        boolean gobackCheck1 = false, gobackCheck2 = false;

        //same functionality as before except it is for the credits screen
        if (Gdx.input.getX() >= 790 && Gdx.input.getX() <= 1107) {
            gobackCheck1 = true;
        }
        if (Gdx.input.getY() >= 901 && Gdx.input.getY() <= 956){
            gobackCheck2 = true;
        }

        if (gobackCheck1 && gobackCheck2){
            currentGoBack = goBack2;
            if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
                showCredits = false;
                pauseMusic.play();
            }
        }else {
            currentGoBack = goBack1;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //once the game starts nothing will be rendered
        if (!startGame || showCredits) {
            Gdx.gl.glClearColor(.128f, .128f, .128f, .1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            update();
            batch.begin();
            batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

            //DRAW EVERYTHING ELSE BELOW:
            batch.draw(title, 430, 150, 1050, 1050);
            batch.draw(currentMusic, 1800, 50, 100, 100);
            batch.draw(currentButton, 710, 50, 500, 500);
            batch.draw(currentMusic, 1800, 50, 100, 100);
            batch.draw(currentCredits,697,-100,500,500);
            batch.end();
        }

        if (showCredits){
          Gdx.gl.glClearColor(0, 0, 0, 0);
          Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          update();
          batch.begin();
          font.draw(batch,"Programmer:",0,1050,sectionWidth, Align.left,false);
          font2.draw(batch,"Ghozti",0,950,sectionWidth, Align.left,false);
          font.draw(batch,"Art & assets By:",0,850,sectionWidth, Align.left,false);
          font2.draw(batch,"Brit & Ghozti",0,750,sectionWidth, Align.left,false);
          font.draw(batch,"Music By:",0,650,sectionWidth, Align.left,false);
          font2.draw(batch,"Fesliyan Studios",0,550,sectionWidth, Align.left,false);
          font2.draw(batch,"find them here: https://www.fesliyanstudios.com/",0,500,sectionWidth, Align.left,false);
          batch.draw(currentGoBack,697,-100,500,500);
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
