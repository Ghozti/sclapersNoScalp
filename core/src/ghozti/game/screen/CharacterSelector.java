package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.font.Font;
import jdk.tools.jmod.Main;

public class CharacterSelector {

    String textureID;
    int characterSelected;
    Font font;
    Font subFont;
    String characterName;

    //textures
    Texture anakin, anakinSelected,currentAnakin,linus,linusSelected,currentLinus;

    public CharacterSelector(){
        //default textureID
        textureID = "SpaceDude";
        //sets the font
        font = new Font(160);
        subFont = new Font(100);
        //sets textures
        anakin = new Texture("anakin.png");
        anakinSelected = new Texture("anakinSelected.png");
        linus = new Texture("linus.png");
        linusSelected = new Texture("linusSelected.png");

        currentAnakin = anakinSelected;
        currentLinus = linus;

        characterName = "chosen one";
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
        handleInput();
    }

    private void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            currentLinus = linusSelected;
            currentAnakin = anakin;
            characterName = "linus drop tips";
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            currentAnakin = anakinSelected;
            currentLinus = linus;
            characterName = "chosen one";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            MainMenu.changeCharacter = false;
        }
    }

    public String getTextureId(){
        return textureID;
    }

    public void draw(Batch batch){
        update();
        batch.begin();
        font.draw(batch,"Character Selector",780, 1000,MainMenu.WORLD_WIDTH/3,false);
        subFont.draw(batch,"Use arrow keys to cycle through characters and ENTER to select",520,900,MainMenu.WORLD_WIDTH/3,false);
        subFont.draw(batch,"Current Character: ".concat(characterName),520,700,MainMenu.WORLD_WIDTH/3,false);
        batch.draw(currentAnakin,100,100,500,500);
        batch.draw(currentLinus,700,100,500,500);
        batch.end();
    }

    //TODO scale the fonts and textures, make this actually work, scale and work on Main menu
}
