package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp {

    TextureRegion texture;
    Rectangle boundingRect;
    boolean startedTimer;
    float time;

    public PowerUp(TextureRegion texture, float width, float height){
        this.texture = texture;
        boundingRect = new Rectangle(100,100,width,height);
    }

    public float getWidth(){return boundingRect.width;};
    public float getHeight(){return boundingRect.height;};
    public float getX(){return boundingRect.x;}
    public float getY() {return boundingRect.y;}
    public Rectangle getBoundingRect(){return boundingRect;}

    public boolean isTouched(Player player){
        return player.getBoundingRect().overlaps(boundingRect);
    }

    public void startTimer(){
        //TODO do timer stuff
    }

    public boolean isValid(){
        return time == 0;
    }

    public abstract void applyEffect(Scalper scalper, Player player);

    public abstract void renderPowerUP(Batch batch);
}
