package ghozti.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.font.Font;

public class CharacterSelector {

    String textureID;
    int characterSelected;
    Font font;

    //textures
    Texture anakin, anakinSelected,linus,linusSelected;
    Texture currentSelected;

    public CharacterSelector(){
        //default textureID
        textureID = "SpaceDude";
        //sets the font
        font = new Font(130);
        //sets textures
        anakin = new Texture("anakin.png");
        anakinSelected = new Texture("anakinSelected.png");
        linus = new Texture("linux.png");
        linusSelected = new Texture("linusSelected.png");
        currentSelected = anakinSelected;
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
        batch.draw(currentSelected);
    }
}
