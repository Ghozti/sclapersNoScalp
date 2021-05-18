package ghozti.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    //attributes
    float speed,height,length,x,y;
    //texture
    TextureRegion playerTexture;

    public Player(TextureRegion playerTexture,float speed, float height, float x, float y){
        this.playerTexture = playerTexture;
        this.speed = speed;
        this.height = height;
        this.x = x;
        this.y = y;
    }
}
