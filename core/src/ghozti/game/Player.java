package ghozti.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    //attributes
    float speed,height,width,x,y;
    //texture
    TextureRegion playerTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;

    //constructor
    public Player(TextureRegion playerTexture,float speed, float height, float width, float x, float y){
        this.playerTexture = playerTexture;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        boundingRect = new Rectangle(x,y,width,height);
    }

    //getters
    public float getSpeed(){return speed;}
    public float getHeight(){return height;}
    public float getWidth() {return width;}
    public float getX() {return x;}
    public float getY(){return y;}

    public void move(float delta){

        float leftLimit = -boundingRect.x
                ,rightLimit = Screen.WORLD_WIDTH-boundingRect.x-boundingRect.width
                ,upLimit = Screen.WORLD_HEIGHT/2 - boundingRect.y - boundingRect.height
                ,downLimit = -boundingRect.y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0){
            float xChange = speed*delta;
            xChange = Math.min(xChange,rightLimit);
            boundingRect.setPosition(xChange,0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit < 0){
            float xChange = -speed*delta;
            xChange = Math.max(xChange,leftLimit);
            boundingRect.setPosition(xChange,0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && upLimit > 0){
            float yChange = speed*delta;
            yChange = Math.min(yChange,upLimit);
            boundingRect.setPosition(0f,yChange);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && downLimit < 0){
            float yChange = -speed*delta;
            yChange = Math.max(yChange,downLimit);
            boundingRect.setPosition(0f,yChange);
        }
    }

    public void draw(Batch batch){

    }
}
