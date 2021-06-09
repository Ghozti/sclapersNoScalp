package ghozti.game.screen;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.font.Font;

public class CharacterSelector {

    String textureID;
    int characterSelected;
    Font font = new Font(130);

    public CharacterSelector(){
        //default textureID
        textureID = "SpaceDude";
        //sets the font
        font = new Font(130);
    }

    private void setTextureID(){
        switch (characterSelected){
            case 1:
                textureID = "linusDropTips";
                break;
            case 2:
                textureID = "bitwit";
                break;
            case 3:
                textureID = "Steve";
                break;
            case 4:
                textureID = "SpaceDude";
                break;
        }
    }

    public void update(){

    }

    private void handleInput(){

    }

    public String getTextureId(){
        return textureID;
    }

    public void draw(Batch batch){
        font.draw(batch,"Character Selector",MainMenu.WORLD_WIDTH/2,40,MainMenu.WORLD_WIDTH/3,false);
    }
}
