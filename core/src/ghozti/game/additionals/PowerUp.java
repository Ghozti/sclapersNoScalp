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

    boolean spawnNew;
    boolean activated;
    boolean effectApplied;
    boolean drawReady;

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

    public void detectCollision(Scalper sclaper,Player player){
        if(player.getBoundingRect().overlaps(boundingRect)) {
            applyEffect(sclaper,player);
            effectApplied = true;
            activated  = true;
        }
    }

    protected void playSound(){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("bonus.wav"));
        sound.play(1.0f);
    }

    private float counter2;

    public void startSpawnerTimer(float delta){
        if(activated) {
            counter2 += delta;
            if (counter2 > 10.0) {
                spawnNew = true;
                counter2 = 0;
            }
        }
    }

    private float counter;

    public void startEffectTimer(float delta,Scalper scalper, Player player){
        if(activated) {
            counter += delta;
            if (counter > 5.0) {
                counter = 0;
                reverseEffect(scalper, player);
            }
        }
    }

    public abstract void applyEffect(Scalper scalper, Player player);

    public abstract void reverseEffect(Scalper scalper, Player player);

    public abstract void draw(Batch batch);
}
