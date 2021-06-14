
package ghozti.game.entities.graphicsCard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    //bools
    boolean collided;

    public GraphicsCard(float worldW, float worldH){
        //sets the texture atlas
        this.atlas = new TextureAtlas("gpu.atlas");
        //sets the positions
        float[] positions = setPosition(worldW,worldH);
        //adds all possible textures into the array
        textureRegions = new TextureRegion[9];

        textureRegions[0] = atlas.findRegion("3070");
        textureRegions[1] = atlas.findRegion("suprim");
        textureRegions[2] = atlas.findRegion("3080");
        textureRegions[3] = atlas.findRegion("3090");
        textureRegions[4] = atlas.findRegion("6700xt");
        textureRegions[5] = atlas.findRegion("redDevil");
        textureRegions[6] = atlas.findRegion("6800");
        textureRegions[7] = atlas.findRegion("midnight");
        textureRegions[8] = atlas.findRegion("6900xt");

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
            card = "3090 FE";
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
        }
    }

    //getters and setters
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    public void reset(float wW, float wH){

        //resets attributes

        float[] positions = setPosition(wW,wH);

        currentRegion = textureRegions[(int) ((Math.random() * ((textureRegions.length)) + 0))];

        if (currentRegion == textureRegions[5]) {
            textureW = 100;
            textureH = 50;
        }

        boundingRect.setPosition(positions[0]+10,positions[1]+30);

        if (textureRegions[0].equals(currentRegion)) {
            card = "3070 FE";
        }else if (textureRegions[1].equals(currentRegion)) {
            card = "3080 Suprim";
        }else if (textureRegions[2].equals(currentRegion)) {
            card = "3080 FE";
        }else if (textureRegions[3].equals(currentRegion)) {
            card = "3090 FE";
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
        }
    }

    //collision detection for scalper and player
    public boolean collides(Player player, Scalper scalper){
        //sets scores for both scalper and player when collides
        if(player.getBoundingRect().overlaps(boundingRect)) {
            player.setScore(player.getScore()+player.getScoringNumber());
            collided = true;
        }
        else if (scalper.getBoundingRect().overlaps(boundingRect)) {
            scalper.setScore(scalper.getScore() + scalper.getScoringNumber());
            collided = true;
        }
        return player.getBoundingRect().overlaps(boundingRect) || scalper.getBoundingRect().overlaps(boundingRect);
    }

    //creates an array with 2 randomly created floats for the x and y pos
    public float[] setPosition(float worldWidth,float worldHeight){
        float x = (float) ((Math.random() * ((worldWidth - 60) - 60)) + 60);
        float y = (float) ((Math.random() * ((worldHeight - 60) - 60)) + 60);
        return new float[]{x,y};
    }

    Texture scalp = new Texture("22.jpg");

    public void draw(Batch batch){
        Screen.font.draw(batch,card,boundingRect.x, currentRegion == textureRegions[5] ? boundingRect.y-30 : boundingRect.y-15, Screen.WORLD_WIDTH/3,false);
        batch.draw(currentRegion,boundingRect.x-10,boundingRect.y-30,textureW,textureH);
        //batch.draw(scalp,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}