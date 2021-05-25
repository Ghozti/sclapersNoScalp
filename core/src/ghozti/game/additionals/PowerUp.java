package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.screen.Screen;

public abstract class PowerUp {

    TextureAtlas atlas = new TextureAtlas("boosters.atlas");
    TextureRegion texture;
    Rectangle boundingRect;
    boolean startedTimer;
    float time;

    public PowerUp(float width, float height){
        texture = atlas.findRegion("speedBoost");
        float[] coordinates = determinePos(Screen.WORLD_WIDTH,Screen.WORLD_HEIGHT);
        boundingRect = new Rectangle(coordinates[0],coordinates[1],width,height);
    }

    public float getWidth(){return boundingRect.width;};
    public float getHeight(){return boundingRect.height;};
    public float getX(){return boundingRect.x;}
    public float getY() {return boundingRect.y;}
    public Rectangle getBoundingRect(){return boundingRect;}

    public float[] determinePos(float worldWidth,float worldHeight){
        return new float[]{(float) ((Math.random() * ((worldWidth - 60) - 60)) + 60), (float) ((Math.random() * ((worldHeight - 60) - 60)) + 60)};
    }

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
