package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class PowerUp {

    TextureRegion texture;
    float width,height,x,y;
    Rectangle boundingRect;

    public PowerUp(TextureRegion texture, float width, float height){
        this.texture = texture;
        this.width = width;
        this.height = height;
        boundingRect = new Rectangle(100,100,width,height);
    }

    public float getWidth(){return width;};
    public float getHeight(){return height;};
    public float getX(){return x;}
    public float getY() {return y;}
    public Rectangle getBoundingRect(){return boundingRect;}

    public abstract void renderPowerUP(Batch batch);
}
