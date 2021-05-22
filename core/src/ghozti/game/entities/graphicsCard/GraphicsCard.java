package ghozti.game.entities.graphicsCard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

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
        this.atlas = textureAtlas;
        float[] positions = setPosition(worldW,worldH);
        boundingRect = new Rectangle(positions[0],positions[1],width,height);

        textureRegions = new TextureRegion[8];

        textureRegions[0] = atlas.findRegion("3070");
        textureRegions[1] = atlas.findRegion("msi3080");
        textureRegions[2] = atlas.findRegion("3080");
        textureRegions[3] = atlas.findRegion("3090");
        textureRegions[4] = atlas.findRegion("6700xt");
        textureRegions[5] = atlas.findRegion("myKid");
        textureRegions[6] = atlas.findRegion("6800");
        textureRegions[7] = atlas.findRegion("black6800xt");

        currentRegion = textureRegions[(int) ((Math.random() * ((textureRegions.length - 0)) + 0))];
    }

    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    public boolean collides(Player player, Scalper scalper){
        return player.getBoundingRect().overlaps(boundingRect) || scalper.getBoundingRect().overlaps(boundingRect);
    }

    public float[] setPosition(float worldWidth,float worldHeight){
        float x = (float) ((Math.random() * ((worldWidth - 60) - 60)) + 60);
        float y = (float) ((Math.random() * ((worldHeight - 60) - 60)) + 60);
        return new float[]{x,y};
    }

    public void draw(Batch batch){
        batch.draw(currentRegion,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
