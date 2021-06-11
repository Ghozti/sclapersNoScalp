package ghozti.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.font.Font;

public class CharacterSelector {

    private static String atlasName;
    private Font font;
    private Font subFont;
    private String characterName;

    //textures
    Texture anakin, anakinSelected,currentAnakin,linus,linusSelected,currentLinus;

    public CharacterSelector(){
        //default textureID
        atlasName = "spacedude.atlas";
        //sets the font
        font = new Font(160);
        subFont = new Font(100);
        //sets textures
        anakin = new Texture("anakin.png");
        anakinSelected = new Texture("anakinSelected.png");
        linus = new Texture("linusT.png");
        linusSelected = new Texture("linusSelected.png");

        currentAnakin = anakinSelected;
        currentLinus = linus;

        characterName = "chosen one";
    }

    public static String getAtlas(){
        return atlasName;
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
        }else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            MainMenu.changeCharacter = false;
        }
    }

    public void draw(Batch batch){
        update();
        batch.begin();
        font.draw(batch,"Character Selector",Screen.WORLD_WIDTH/(32/13f), Screen.WORLD_HEIGHT/(27/25f),MainMenu.WORLD_WIDTH/3,false);
        subFont.draw(batch,"Use arrow keys to cycle through characters, press ENTER",Screen.WORLD_WIDTH/(48/13f),Screen.WORLD_HEIGHT/(6/5f),MainMenu.WORLD_WIDTH/3,false);
        subFont.draw(batch,"Current Character: ".concat(characterName),Screen.WORLD_WIDTH/(48/13f),Screen.WORLD_HEIGHT/(54/35f),MainMenu.WORLD_WIDTH/3,false);
        batch.draw(currentAnakin,Screen.WORLD_WIDTH/(95/5f),Screen.WORLD_HEIGHT/(54/5f),Screen.WORLD_WIDTH/(96/25f),Screen.WORLD_HEIGHT/(54/25f));
        batch.draw(currentLinus,Screen.WORLD_WIDTH/(96/35f),Screen.WORLD_HEIGHT/(54/5f),Screen.WORLD_WIDTH/(96/25f),Screen.WORLD_HEIGHT/(54/25f));
        batch.end();
    }

    //TODO scale the fonts and textures, make this actually work, scale and work on Main menu
}
