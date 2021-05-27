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
    TextureRegion[] textures = new TextureRegion[9];
    TextureRegion currentTexture;
    //boundingRect
    com.badlogic.gdx.math.Rectangle boundingRect;
    //hud
    float score;

    public Scalper(float speed,float width, float height,float x, float y){
        this.speed = speed;
        currentTexture = atlas.findRegion("neutral");
        boundingRect = new Rectangle(x,y,width,height);

        textures[0] = atlas.findRegion("forwardwalk1");
        textures[1] = atlas.findRegion("forwardwalk2");

        textures[2] = atlas.findRegion("frontwalk1");
        textures[3] = atlas.findRegion("frontwalk2");

        textures[4] = atlas.findRegion("leftwalk1");
        textures[5] = atlas.findRegion("leftwalk2");

        textures[6] = atlas.findRegion("neutral");

        textures[7] = atlas.findRegion("rightwalk1");
        textures[8] = atlas.findRegion("rightwalk2");
    }

    public Rectangle getBoundingRect(){return boundingRect;}
    public float getSpeed(){return speed;}
    public void setSpeed(float speed){this.speed = speed;}
    public float getHeight(){return boundingRect.height;}
    public float getWidth() {return boundingRect.width;}
    public float getX() {return boundingRect.x;}
    public float getY(){return boundingRect.y;}
    public void setScore(float score){this.score = score;}
    public float getScore() {return score; }

    int currentTextureRight = 0, currentTextureLeft = 0;
    int currentTextureUp = 0, currentTextureDown = 0;

    public void move(float delta, GraphicsCard card){

        if(card != null){
            if(card.getX() > getX()+25){//<-- this number is used as a "stabilizer" which basically allows the scalper to move smoothly without "jumping" when moving
                boundingRect.setPosition(getX()+speed*delta, getY());

                if (currentTextureRight >= 60){
                    currentTextureRight = 0;
                }

                if(currentTextureRight <= 30){
                    currentTexture = textures[7];
                }else if(currentTextureRight >= 31){
                    currentTexture = textures[8];
                }
                currentTextureRight++;

            }else if(card.getX() < getX()-25){
                boundingRect.setPosition(getX()-speed*delta, getY());

                if (currentTextureLeft >= 60){
                    currentTextureLeft = 0;
                }

                if(currentTextureLeft <= 30){
                    currentTexture = textures[4];
                }else if(currentTextureLeft >= 31){
                    currentTexture = textures[5];
                }
                currentTextureLeft++;
            }

            if(card.getY() > getY()+25){
                boundingRect.setPosition(getX(),getY()+speed*delta);

                if (currentTextureUp >= 60){
                    currentTextureUp = 0;
                }

                if(currentTextureUp <= 30){
                    currentTexture = textures[2];
                }else if(currentTextureUp >= 31){
                    currentTexture = textures[3];
                }
                currentTextureUp++;

            }else if(card.getY() < getY()-25){
                boundingRect.setPosition(getX(),getY()-speed*delta);

                if (currentTextureDown >= 60){
                    currentTextureDown = 0;
                }

                if(currentTextureDown <= 30){
                    currentTexture = textures[0];
                }else if(currentTextureDown >= 31){
                    currentTexture = textures[1];
                }
                currentTextureDown++;

            }
        }
    }

    public void draw(Batch batch,float delta,GraphicsCard card){
        move(delta,card);
        batch.draw(currentTexture,boundingRect.x,boundingRect.y,boundingRect.width,boundingRect.height);
    }
}
