package ghozti.game.additionals;

import com.badlogic.gdx.graphics.g2d.Batch;
import ghozti.game.entities.npc.Scalper;
import ghozti.game.entities.player.Player;
import ghozti.game.screen.Screen;

public class ScalperSlower extends PowerUp{

    public ScalperSlower(float width, float height) {
        super(width, height);
        //sets the texture
        texture = atlas.findRegion("slowDownScalper");
    }

    @Override
    public void applyEffect(Scalper scalper, Player player) {
        //if (!effectApplied) {
        //will play the sound and apply this effects effect
            playSound();
            scalper.setSpeed(scalper.getSpeed() / 1.8f);
        //}
    }

    @Override
    public void reverseEffect(Scalper scalper, Player player) {
        scalper.setSpeed(375);
    }

    @Override
    public void draw(Batch batch) {
        if (!hide){
            Screen.font.draw(batch,"Scalper Slower",boundingRect.x-10, boundingRect.y - 10, Screen.WORLD_WIDTH/3,false);
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        }
    }
}
