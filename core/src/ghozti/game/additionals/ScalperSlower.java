package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

public class ScalperSlower extends PowerUp{

    public ScalperSlower(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("slowDownScalper");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        scalper.setSpeed(scalper.getSpeed()/1.8f);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
