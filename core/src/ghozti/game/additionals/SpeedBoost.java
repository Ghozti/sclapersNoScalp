package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

public class SpeedBoost extends PowerUp {

    public SpeedBoost(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("speedBoost");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        //if (!effectApplied) {
            playSound();
            player.setSpeed(500);
        //}
    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {
        player.setSpeed(355);
    }

    @Override
    public void draw(Batch batch) {
        if (!hide) batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
