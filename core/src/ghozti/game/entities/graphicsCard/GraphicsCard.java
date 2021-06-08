package ghozti.game.entities.graphicsCard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.screen.Screen;

import java.util.ArrayList;

public class GraphicsCard {

    //attributes
    //graphics
    TextureRegion[] textureRegions;
    TextureRegion currentRegion;
    TextureAtlas atlas;
    //dimensions
    float width,height;
    //bounding
    Rectangle boundingRect;
    int textureW = 100,textureH = 100;
    //dialogue
    String card;
    BitmapFont font;
    float verticalMargin,leftx,rightx,centerx,row1Y,row2Y,sectionWidth;

    public GraphicsCard(TextureAtlas textureAtlas, float width, float height, float worldW, float worldH){
        //sets the texture atlas
        this.atlas = textureAtlas;
        //sets the positions
        float[] positions = setPosition(worldW,worldH);
        //adds all possible textures into the array
        textureRegions = new TextureRegion[10];

        textureRegions[0] = atlas.findRegion("3070");
        textureRegions[1] = atlas.findRegion("suprim");
        textureRegions[2] = atlas.findRegion("3080");
        textureRegions[3] = atlas.findRegion("3090");
        textureRegions[4] = atlas.findRegion("6700xt");
        textureRegions[5] = atlas.findRegion("redDevil");
        textureRegions[6] = atlas.findRegion("6800");
        textureRegions[7] = atlas.findRegion("midnight");
        textureRegions[8] = atlas.findRegion("6900xt");
        textureRegions[9] = atlas.findRegion("3060ti");

        //sets the actual current texture randomly.
        currentRegion = textureRegions[(int) ((Math.random() * ((textureRegions.length)) + 0))];

        if (currentRegion == textureRegions[5]) {
            textureW = 100;
            textureH = 50;
        }

        //sets the boundingrect for collision and positioning.
        boundingRect = new Rectangle(positions[0]+10,positions[1]+30,80,40);

        if (textureRegions[0].equals(currentRegion)) {
            card = "3070 FE";
        }else if (textureRegions[1].equals(currentRegion)) {
            card = "3080 Suprim";
        }else if (textureRegions[2].equals(currentRegion)) {
            card = "3080 FE";
        }else if (textureRegions[3].equals(currentRegion)) {
            card = "3090 DE";
        }else if (textureRegions[4].equals(currentRegion)) {
            card = "6700xt";
        }else if (textureRegions[5].equals(currentRegion)) {
            card = "6700xt red devil";
        }else if (textureRegions[6].equals(currentRegion)) {
            card = "6800";
        }else if (textureRegions[7].equals(currentRegion)) {
            card = "6800xt midnight";
        }else if (textureRegions[8].equals(currentRegion)) {
            card = "6900xt";
        }else if (textureRegions[9].equals(currentRegion)) {
            card = "3060ti";
        }

        //creates a bitmapFont from our file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("NugoSansLight-9YzoK.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //sets font attributes
        fontParameter.size = 172;
        fontParameter.borderWidth = 3.6f;
        fontParameter.color = new Color(1,1,1,.3f);
        fontParameter.borderColor = new Color(1,1,1,.3f);

        font = fontGenerator.generateFont(fontParameter);

        //sets scale of font
        font.getData().setScale(.3f);

        //calculates hud margins,etc
        verticalMargin = font.getCapHeight()/2;
        leftx = verticalMargin;
        rightx = Screen.WORLD_WIDTH * 2 / 3 - leftx;
        centerx = Screen.WORLD_WIDTH/3;
        row1Y = Screen.WORLD_HEIGHT - verticalMargin;
        row2Y = row1Y - verticalMargin - font.getCapHeight();
        sectionWidth = Screen.WORLD_WIDTH/3;
    }

    //getters and setters
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    //collision detection for scalper and player
    public boolean collides(Player player, Scalper scalper){
        //sets scores for both scalper and player when collides
        if(player.getBoundingRect().overlaps(boundingRect)) player.setScore(player.getScore()+player.getScoringNumber());
        else if (scalper.getBoundingRect().overlaps(boundingRect)) scalper.setScore(scalper.getScore()+scalper.getScoringNumber());
        return player.getBoundingRect().overlaps(boundingRect) || scalper.getBoundingRect().overlaps(boundingRect);
    }

    public static void detectCollision(Player player, Scalper scalper, ArrayList<GraphicsCard> graphicsCards){
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

    //creates an array with 2 randomly created floats for the x and y pos
    public float[] setPosition(float worldWidth,float worldHeight){
        float x = (float) ((Math.random() * ((worldWidth - 60) - 60)) + 60);
        float y = (float) ((Math.random() * ((worldHeight - 60) - 60)) + 60);
        return new float[]{x,y};
    }

    Texture scalp = new Texture("22.jpg");

    public void draw(Batch batch){
        batch.draw(currentRegion,boundingRect.x-10,boundingRect.y-30,textureW,textureH);
        //batch.draw(scalp,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
