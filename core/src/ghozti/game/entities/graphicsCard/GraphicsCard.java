package ghozti.game.entities.graphicsCard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.player.Player;

public class GraphicsCard {

    //attributes
    //graphics
    TextureRegion textureRegion;
    //dimensions
    float width,height;
    //bounding
    Rectangle boundingRect;

    public GraphicsCard(TextureRegion textureRegion,float width, float height,float worldW, float worldH){
        this.textureRegion = textureRegion;
        float[] positions = setPosition(worldW,worldH);
        boundingRect = new Rectangle(positions[0],positions[1],width,height);
    }

    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    public boolean collides(Player player){
        return player.getBoundingRect().overlaps(boundingRect);
    }

    public float[] setPosition(float worldWidth,float worldHeight){
        float x = (float) ((Math.random() * (worldWidth - 10)) + 10);
        float y = (float) ((Math.random() * (worldHeight - 10)) + 10);
        return new float[]{x,y};
    }

    public void draw(Batch batch){
        batch.draw(textureRegion,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
        boundingRect.setPosition(boundingRect.x, boundingRect.y);
        //System.out.println("x" + "[" + boundingRect.x + "]");
        //System.out.println("y" + "[" + boundingRect.y + "]");
    }
}
