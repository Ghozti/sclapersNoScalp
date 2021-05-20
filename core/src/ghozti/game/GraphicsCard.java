package ghozti.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GraphicsCard {

    //attributes
    //graphics
    TextureRegion textureRegion;
    //dimensions
    float width,height;
    //bounding
    Rectangle boundingRect;

    public GraphicsCard(TextureRegion textureRegion,float x, float y,float width, float height){
        this.textureRegion = textureRegion;
        boundingRect = new Rectangle(x,y,width,height);
    }
}
