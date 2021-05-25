package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class StockCrash extends PowerUp{

    public StockCrash(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("marketCrash");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        scalper.setScore(scalper.getScore()/2);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
