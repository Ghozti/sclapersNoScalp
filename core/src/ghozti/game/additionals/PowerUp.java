package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class PowerUp {

    TextureRegion texture;
    float width,height,x,y;
    Rectangle boundingRect;

    public PowerUp(TextureRegion texture, float width, float height){

    }
}
