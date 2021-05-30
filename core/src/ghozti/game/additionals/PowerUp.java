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

    //for when the powerup has reached it's end this boolean will reset it
    public boolean spawnNew;
    //if the powerup has detected collision
    boolean activated;
    //boolean effectApplied;
    //if the power up has detected collision it will not draw
    boolean hide;

    public PowerUp(float width, float height){
        //sets the position based on a randomizer.
        float[] coordinates = determinePos(Screen.WORLD_WIDTH,Screen.WORLD_HEIGHT);
        //sets the boundingrect for collision and positioning
        boundingRect = new Rectangle(coordinates[0],coordinates[1],width,height);
    }

    //getters and setters
    public float getWidth(){return boundingRect.width;};
    public float getHeight(){return boundingRect.height;};
    public float getX(){return boundingRect.x;}
    public float getY() {return boundingRect.y;}
    public Rectangle getBoundingRect(){return boundingRect;}

    //resets all booleans and coordinates for when the power up has reached it's end cycle.
    public void reset(){
        //creates new coordinates
        float[] coordinates = determinePos(Screen.WORLD_WIDTH,Screen.WORLD_HEIGHT);
        //sets the coordinates to the rect
        boundingRect.setPosition(coordinates[0],coordinates[1]);
        //resets booleans
        spawnNew = false;
        activated = false;
        //effectApplied = false;
        hide = false;
        //resets counter
        counter = 0;
        counter2 = 0;
    }

    public float[] determinePos(float worldWidth,float worldHeight){
        //creates 2 floats based on a random number generator
        return new float[]{(float) ((Math.random() * ((worldWidth - 60) - 60)) + 60), (float) ((Math.random() * ((worldHeight - 60) - 60)) + 60)};
    }

    public void detectCollision(Scalper sclaper,Player player){
        //System.out.println(hide);
        //System.out.println(player.getSpeed());
        if(player.getBoundingRect().overlaps(boundingRect) && !activated) {
            applyEffect(sclaper,player);
            //effectApplied = true;
            activated  = true;
            hide = true;
        }
    }

    protected void playSound(){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("bonus.wav"));
        sound.play(1.0f);
    }

    private float counter2;

    public void startSpawnerTimer(float delta){
        //a timer for 10 seconds (the time it takes to spawn a new powerup after the last one was touched)
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
        //a timer for 5 seconds (the time the powerup effect lasts)
        if(activated) {
            counter += delta;
            if (counter > 5.0) {
                reverseEffect(scalper, player);
                counter = 0;
            }
        }
    }

    public abstract void applyEffect(Scalper scalper, Player player);

    public abstract void reverseEffect(Scalper scalper, Player player);

    public abstract void draw(Batch batch);
}
