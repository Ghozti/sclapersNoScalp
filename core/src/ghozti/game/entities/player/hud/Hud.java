package ghozti.game.entities.player.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import ghozti.game.screen.Screen;

public class Hud {

    float score,time;
    BitmapFont font;
    float verticalMargin,leftx,rightx,centerx,row1Y,row2Y,sectionWidth;

    public Hud(){
        //creates a bitmapFont from our file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("NugoSansLight-9YzoK.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //sets font stuff
        fontParameter.size = 172;
        fontParameter.borderWidth = 3.6f;
        fontParameter.color = new Color(1,1,1,1f);
        fontParameter.borderColor = new Color(1,1,1,1f);

        font = fontGenerator.generateFont(fontParameter);

        //sets scale of font
        font.getData().setScale(.3f);

        //calculates hud margins,etc
        verticalMargin = font.getCapHeight()/2;
        leftx = verticalMargin;
        rightx = Screen.WORLD_WIDTH * 2 / 3 - leftx;
        centerx = Screen.WORLD_WIDTH/3;
        row1Y = Screen.WORLD_HEIGHT - verticalMargin;
        row2Y = row1Y - verticalMargin - font.getCapHeight();
        sectionWidth = Screen.WORLD_WIDTH/3;
    }

    public void render(Batch batch){
        font.draw(batch,"score",leftx,row1Y,sectionWidth, Align.left,false);
        //font.draw(batch,"score",leftx,row1Y,sectionWidth, Align.left,false);
    }
}
