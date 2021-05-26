package ghozti.game.additionals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

    private float counter;
    private float counter2;

    public PowerUp(float width, float height){
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
        if(player.getBoundingRect().overlaps(boundingRect)) {

        }
        return player.getBoundingRect().overlaps(boundingRect);
    }

    protected void playSound(){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("bonus.wav"));
        sound.play(1.0f);
    }

    public void startSpawnerTimer(float delta){
        counter2 += delta;
        if (counter2 > 10.0) {
            counter2 = 0;
        }
    }

    public void startTimer(float delta,Scalper scalper, Player player){
        counter += delta;
        if (counter > 5.0) {
            counter = 0;
            reverseEffect(scalper,player);
        }
    }

    public abstract void applyEffect(Scalper scalper, Player player);

    public abstract void reverseEffect(Scalper scalper, Player player);

    public abstract void draw(Batch batch);
}
