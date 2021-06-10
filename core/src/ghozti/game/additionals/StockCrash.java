package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.screen.Screen;

public class StockCrash extends PowerUp{

    public StockCrash(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("marketCrash");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        //if (!effectApplied) {
            playSound();
            scalper.setScore(scalper.getScore() - ((scalper.getScore() * 10)/100));
        //}
    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {

    }

    @Override
    public void draw(Batch batch) {
        if (!hide){
            Screen.font.draw(batch,"Stock Crasher",boundingRect.x-10, boundingRect.y - 10, Screen.WORLD_WIDTH/3,false);
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        }
    }
}
