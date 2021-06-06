package ghozti.game.entities.graphicsCard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
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

    public GraphicsCard(TextureAtlas textureAtlas, float width, float height, float worldW, float worldH){
        //sets the texture atlas
        this.atlas = textureAtlas;
        //sets the positions
        float[] positions = setPosition(worldW,worldH);
        //sets the boundingrect for collision and positioning.
        boundingRect = new Rectangle(positions[0],positions[1],width,height);

        //adds all possible textures into the array
        textureRegions = new TextureRegion[8];

        textureRegions[0] = atlas.findRegion("3070");
        textureRegions[1] = atlas.findRegion("msi3080");
        textureRegions[2] = atlas.findRegion("3080");
        textureRegions[3] = atlas.findRegion("3090");
        textureRegions[4] = atlas.findRegion("6700xt");
        textureRegions[5] = atlas.findRegion("myKid");
        textureRegions[6] = atlas.findRegion("6800");
        textureRegions[7] = atlas.findRegion("black6800xt");

        //sets the actual current texture randomly.
        currentRegion = textureRegions[(int) ((Math.random() * ((textureRegions.length - 0)) + 0))];
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

    public void draw(Batch batch){
        batch.draw(currentRegion,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
