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

    //TODO imprive the collision detection

    private void verifyPos(float x,float y){
        if (x > 1878 || x < -3){
            boundingRect.setPosition(1045,boundingRect.y);
            System.out.println('*');
        }
        if(y > 1041 || y < 4){
            boundingRect.setPosition(boundingRect.x,10);
        }
    }

    public void move(float delta) {
        float xchange,ychange;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xchange = boundingRect.x+speed * delta;
            ychange = boundingRect.y;
            boundingRect.setPosition(xchange,ychange);
            verifyPos(xchange,ychange);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xchange = boundingRect.x-speed * delta;
            ychange = boundingRect.y;
            boundingRect.setPosition(xchange,ychange);
            verifyPos(xchange,ychange);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            ychange = boundingRect.y+speed * delta;
            xchange = boundingRect.x;
            verifyPos(xchange,ychange);
            boundingRect.setPosition(xchange,ychange);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            ychange = boundingRect.y-speed * delta;
            xchange = boundingRect.x;
            boundingRect.setPosition(xchange,ychange);
            verifyPos(xchange,ychange);
        }
    }

    public void draw(Batch batch){
        batch.draw(playerTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
        //System.out.println("x" + "[" + boundingRect.x + "]");
        //System.out.println("y" + "[" + boundingRect.y + "]");
    }
}
