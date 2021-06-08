package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.screen.Screen;

public class ScoreBooster extends PowerUp{
    public ScoreBooster(float width, float height) {
        super(width, height);
        texture = atlas.findRegion("x2");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        playSound();
        player.setScoringNumber(2);
    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {
        player.setScoringNumber(1);
    }

    @Override
    public void draw(Batch batch) {
        if (!hide) {
            Screen.font.draw(batch,"Score Booster",boundingRect.x-10, boundingRect.y - 10, Screen.WORLD_WIDTH/3,false);
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        }
    }
}
