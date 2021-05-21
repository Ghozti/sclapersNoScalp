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
    float determiner = 15;
    //texture
    TextureAtlas atlas = new TextureAtlas("scalper");
    TextureRegion[] textures;
    TextureRegion currentTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;

    public Scalper(TextureRegion region,float speed,float width, float height,float x, float y){
        this.speed = speed;
        currentTexture = atlas.findRegion("neutral");
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

                if(determiner == 15){
                    currentTexture = atlas.findRegion("rightwalk1");
                }else if(determiner == 30){
                    currentTexture = atlas.findRegion("rightwalk2");
                }
            }else if(card.getX() < getX()){
                boundingRect.setPosition(getX()-speed*delta, getY());

                if(determiner == 15){
                    currentTexture = atlas.findRegion("leftwalk1");
                }else if(determiner == 30){
                    currentTexture = atlas.findRegion("leftwalk2");
                }
            }

            if(card.getY() > getY()){
                boundingRect.setPosition(getX(),getY()+speed*delta);

                if(determiner == 15){
                    currentTexture = atlas.findRegion("frontwalk1");
                }else if(determiner == 30){
                    currentTexture = atlas.findRegion("frontwalk2");
                }
            }else if(card.getY() < getY()){
                boundingRect.setPosition(getX(),getY()-speed*delta);

                if(determiner == 15){
                    currentTexture = atlas.findRegion("forwardwalk1");
                }else if(determiner == 30){
                    currentTexture = atlas.findRegion("forwardwalk2");
                }
            }

            determiner++;

            if(determiner > 30){
                determiner = 0;
            }
        }
        //TODO make this move the scalper to the nearest/last rendered card
    }

    public void draw(Batch batch,float delta,GraphicsCard card){
        move(delta,card);
        batch.draw(currentTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
