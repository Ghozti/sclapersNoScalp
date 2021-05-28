package ghozti.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    //attributes
    float speed;
    //textures
    TextureAtlas atlas;
    TextureRegion[] textures = new TextureRegion[12];
    TextureRegion currentTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;
    //HUD
    float score;

    //constructor
    public Player(float speed, float width, float height, float x, float y){
        atlas = new TextureAtlas("spacedude.atlas");
        currentTexture = atlas.findRegion("neutral");
        this.speed = speed;
        boundingRect = new Rectangle(x,y,width,height);

        textures[0] = atlas.findRegion("frontwalk1");
        textures[1] = atlas.findRegion("frontwalk2");

        textures[2] = atlas.findRegion("rightwalk0");
        textures[3] = atlas.findRegion("rightwalk1");
        textures[4] = atlas.findRegion("rightwalk2");

        textures[5] = atlas.findRegion("leftwalk0");
        textures[6] = atlas.findRegion("leftwalk1");
        textures[7] = atlas.findRegion("leftwalk2");

        textures[8] = atlas.findRegion("neutral");

        textures[9] = atlas.findRegion("upwalk0");
        textures[10] = atlas.findRegion("upwalk2");
        textures[11] = atlas.findRegion("upwalk2");
    }

    //getters
    public Rectangle getBoundingRect(){return boundingRect;}
    public float getSpeed(){return speed;}
    public void setSpeed(float speed){this.speed = speed;}
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}
    public void setScore(float score){this.score = score;}
    public float getScore(){return score;}


    private void verifyPos(float x,float y){
        /*
        * will check the current x and y position.
        * based on the position it will check if the player's x and y is at a certain point if it is then the player will be moved to the opposite "border" coordinate making an effect like in
        * pacman where the player seems to move from one side of the map to the other.
         */
        if (x > 1878 || x < -3){//for x
            if(x > 1878) boundingRect.setPosition(-3,boundingRect.y);
            else if(x < -3) boundingRect.setPosition(1878,boundingRect.y);
        }
        if(y > 1041 || y < 4){//for y
            if(y > 1041) boundingRect.setPosition(boundingRect.x,4);
            if(y < 4) boundingRect.setPosition(boundingRect.x,1041);
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
            boundingRect.setPosition(xchange,ychange);
            verifyPos(xchange,ychange);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            ychange = boundingRect.y-speed * delta;
            xchange = boundingRect.x;
            boundingRect.setPosition(xchange,ychange);
            verifyPos(xchange,ychange);
        }
    }

    public void draw(Batch batch, float delta){
        batch.draw(currentTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
        //System.out.println("x" + "[" + boundingRect.x + "]");
        //System.out.println("y" + "[" + boundingRect.y + "]");
    }
}
