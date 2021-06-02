package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;

public class ScoreBooster extends PowerUp{
    public ScoreBooster(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("x2");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        playSound();

    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {

    }

    @Override
    public void draw(Batch batch) {

    }
}
