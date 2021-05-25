package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

public class SpeedBoost extends PowerUp{

    public SpeedBoost(float width, float height) {
        super(width, height);
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        player.setSpeed(player.getSpeed() + 50);
    }

    @Override
    public void renderPowerUP(Batch batch) {
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }
}
