package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

public class StockCrash extends PowerUp{

    public StockCrash(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("marketCrash");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        if (!effectApplied) {
            playSound();
            scalper.setScore(scalper.getScore() / 2);
        }
    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {

    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
