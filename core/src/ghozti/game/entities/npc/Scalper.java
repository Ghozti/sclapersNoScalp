package ghozti.game.entities.npc;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Scalper {

    //attributes

    //speed
    float speed;
    //texture
    TextureRegion texture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;

    public Scalper(float speed, TextureRegion region,float x, float y,float width, float height){
        this.speed = speed;
        texture = region;
        boundingRect = new Rectangle(x,y,width,height);
    }
}
