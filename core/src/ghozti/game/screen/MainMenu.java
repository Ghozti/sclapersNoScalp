package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
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
    //bounding rect for the buttons
    com.badlogic.gdx.math.Rectangle startBtnRect,musicRect, creditsRect,goBackRect,mouseRect;
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
    public static final float WORLD_HEIGHT = Gdx.graphics.getHeight();
    public static final float WORLD_WIDTH = Gdx.graphics.getWidth();

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
        music.setVolume(1f);
        music.setLooping(true);
        music.play();

        //sets the sound effects
        pauseMusic = Gdx.audio.newMusic(Gdx.files.internal("mixkit-quick-jump-arcade-game-239.wav"));
        pauseMusic.setVolume(0.5f);

        //sets the title
        title = new Texture("title.png");

        //sets the background texture
        background = new Texture("menubg.png");

        //sets the start button texture
        startBtn =  new Texture("start.png");
        //sets the start button when it's activated
        startBtnActive = new Texture("start2.png");
        currentButton = startBtn;

        //sets the music on texture
        musicOnT = new Texture("musicOn.png");
        //sets the music-off texture
        musicOffT = new Texture("musicOff.png");
        currentMusic = musicOnT;


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
        viewport = new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);

        //sets batch
        batch = new SpriteBatch();

        //sets the bounding rects
        //startBtnRect = new com.badlogic.gdx.math.Rectangle(currentButton.getWidth() + 680,currentButton.getHeight(),50,50);
        //mouseRect = new com.badlogic.gdx.math.Rectangle(Gdx.input.getX(), Gdx.input.getY(),30,30);

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
        if (Gdx.input.getX() <= WORLD_WIDTH/(1920/1163f) && Gdx.input.getX() >= WORLD_WIDTH/(960/377f)) {
            check1 = true;
        }
        if (Gdx.input.getY() <= WORLD_HEIGHT/(4/3f) && Gdx.input.getY() >= WORLD_HEIGHT/(1080/721f)){
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
        if (Gdx.input.getX() >= WORLD_WIDTH/(16/15f) && Gdx.input.getX() <= WORLD_WIDTH/(48/47f)) {
            musicCheck1 = true;
        }
        if (Gdx.input.getY() >=  WORLD_HEIGHT/(216/187f) && Gdx.input.getY() <=  WORLD_HEIGHT/(540/503f)){
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
                music.setVolume(1f);
                pauseMusic.play();
                currentMusic = musicOnT;
            }
        }

        boolean creditsCheck1 = false,creditsCheck2 = false;

        //same functionality as before except it is for the credits screen
        if (Gdx.input.getX() >= WORLD_WIDTH/(640/261f) && Gdx.input.getX() <= WORLD_WIDTH/(960/563f)) {
            creditsCheck1 = true;
        }
        if (Gdx.input.getY() >= WORLD_HEIGHT/(540/461f) && Gdx.input.getY() <= WORLD_HEIGHT/(72/65f)){
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
        if (Gdx.input.getX() >= WORLD_WIDTH/(640/261f) && Gdx.input.getX() <= WORLD_WIDTH/(960/563f)) {
            gobackCheck1 = true;
        }
        if (Gdx.input.getY() >= WORLD_HEIGHT/(540/461f) && Gdx.input.getY() <= WORLD_HEIGHT/(72/65f)){
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
            batch.draw(currentButton, WORLD_WIDTH/(192/71f), WORLD_HEIGHT/(108/5f), WORLD_WIDTH/(96/25f), WORLD_HEIGHT/(54/25));
            batch.draw(title, WORLD_WIDTH/(96/23f), WORLD_HEIGHT/(36/5f), WORLD_WIDTH/1.9f, WORLD_HEIGHT/1.3f);
            batch.draw(currentMusic, WORLD_WIDTH/(16/15f), WORLD_HEIGHT/(108/5f), WORLD_WIDTH/(96/5f), WORLD_HEIGHT/(54/5f));
            batch.draw(currentCredits,WORLD_WIDTH/(1920/697f), WORLD_HEIGHT-WORLD_HEIGHT-WORLD_HEIGHT/8, WORLD_WIDTH/(96/25f), WORLD_HEIGHT/(54/25));
            batch.end();
        }

        if (showCredits){
          Gdx.gl.glClearColor(0, 0, 0, 0);
          Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          update();
          batch.begin();
          font.draw(batch,"Programmer:",WORLD_WIDTH/384,WORLD_HEIGHT/(36/35f),sectionWidth, Align.left,false);
          font2.draw(batch,"Ghozti",WORLD_WIDTH/384,WORLD_HEIGHT/(108/95f),sectionWidth, Align.left,false);
          font.draw(batch,"Art & assets By:",WORLD_WIDTH/384,WORLD_HEIGHT/(108/85f),sectionWidth, Align.left,false);
          font2.draw(batch,"Brit & Ghozti",WORLD_WIDTH/384,WORLD_HEIGHT/(36/25f),sectionWidth, Align.left,false);
          font.draw(batch,"Music By:",WORLD_WIDTH/384,WORLD_HEIGHT/(108/65f),sectionWidth, Align.left,false);
          font2.draw(batch,"Fesliyan Studios",WORLD_WIDTH/384,WORLD_HEIGHT/(108/55f),sectionWidth, Align.left,false);
          font2.draw(batch,"find them here: https://www.fesliyanstudios.com/",WORLD_WIDTH/384,WORLD_HEIGHT/(54/25f),sectionWidth, Align.left,false);
          batch.draw(currentGoBack,WORLD_WIDTH/(1920/697f), WORLD_HEIGHT-WORLD_HEIGHT-WORLD_HEIGHT/8, WORLD_WIDTH/(96/25f), WORLD_HEIGHT/(54/25));
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
