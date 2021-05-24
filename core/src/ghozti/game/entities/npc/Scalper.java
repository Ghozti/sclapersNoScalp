package ghozti.game.entities.npc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ghozti.game.entities.graphicsCard.GraphicsCard;

public class Scalper {

    //attributes

    //speed
    float speed;
    //texture
    TextureAtlas atlas = new TextureAtlas("scalper");
    TextureRegion[] textures;
    TextureRegion currentTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;
    //hud
    float score;

    public Scalper(float speed,float width, float height,float x, float y){
        this.speed = speed;
        currentTexture = atlas.findRegion("neutral");
        boundingRect = new Rectangle(x,y,width,height);
    }

    public Rectangle getBoundingRect(){return boundingRect;}
    public float getSpeed(){return speed;}
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}
    public void setScore(float score){this.score = score;}
    public float getScore() {return score; }

    public void move(float delta, GraphicsCard card){

        if(card != null){
            if(card.getX() > getX()+25){//<-- this number is used as a "stabilizer" which basically allows the scalper to move smoothly without "jumping" when moving
                boundingRect.setPosition(getX()+speed*delta, getY());
            }else if(card.getX() < getX()-25){
                boundingRect.setPosition(getX()-speed*delta, getY());
            }

            if(card.getY() > getY()+25){
                boundingRect.setPosition(getX(),getY()+speed*delta);
            }else if(card.getY() < getY()-25){
                boundingRect.setPosition(getX(),getY()-speed*delta);
            }
        }
        //TODO make this move the scalper to the nearest/last rendered card
    }

    public void draw(Batch batch,float delta,GraphicsCard card){
        move(delta,card);
        batch.draw(currentTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
