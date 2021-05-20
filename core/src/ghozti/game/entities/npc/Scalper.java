package ghozti.game.entities.npc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.graphicsCard.GraphicsCard;

public class Scalper {

    //attributes

    //speed
    float speed;
    //texture
    TextureRegion texture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;

    public Scalper(TextureRegion region,float speed,float width, float height,float x, float y){
        this.speed = speed;
        texture = region;
        boundingRect = new Rectangle(x,y,width,height);
    }

    public float getSpeed(){return speed;}
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}

    public void move(float delta, GraphicsCard card){
        if(card != null){
            if(card.getX() > getX()){
                boundingRect.setPosition(getX()+speed*delta, getY());
            }else if(card.getX() < getX()){
                boundingRect.setPosition(getX()-speed*delta, getY());
            }

            if(card.getY() > getY()){
                boundingRect.setPosition(getX(),getY()+speed*delta);
            }else if(card.getY() < getY()){
                boundingRect.setPosition(getX(),getY()-speed*delta);
            }
        }
        //TODO make this move the scalper to the nearest/last rendered card
    }

    public void draw(Batch batch){
        batch.draw(texture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
