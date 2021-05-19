package ghozti.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    //attributes
    float speed;
    //texture
    TextureRegion playerTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;

    //constructor
    public Player(TextureRegion playerTexture,float speed, float height, float width, float x, float y){
        this.playerTexture = playerTexture;
        this.speed = speed;
        boundingRect = new Rectangle(x,y,width,height);
    }

    //getters
    public float getSpeed(){return speed;}
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    public void move(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            //boundingRect.x += speed * delta;
            boundingRect.setPosition(boundingRect.x+speed * delta,boundingRect.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            //boundingRect.x -= speed * delta;
            boundingRect.setPosition(boundingRect.x-speed * delta,boundingRect.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            //boundingRect.y += speed * delta;
            boundingRect.setPosition(boundingRect.x,boundingRect.y+speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            //boundingRect.y -= speed * delta;
            boundingRect.setPosition(boundingRect.x,boundingRect.y-speed * delta);
        }
    }

    public void draw(Batch batch){
        batch.draw(playerTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
