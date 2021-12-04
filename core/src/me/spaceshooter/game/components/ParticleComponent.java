package me.spaceshooter.game.components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import me.spaceshooter.game.core.Component;

public class ParticleComponent extends Component {

    ParticleEffect effect = new ParticleEffect();
    @Override
    public void update(float dt) {

    }

    public ParticleEffect getEffect() {
        return this.effect;
    }

    public void setEffect(FileHandle particle, FileHandle particleTexture) {
        this.effect.load(particle, particleTexture);
    }
}
