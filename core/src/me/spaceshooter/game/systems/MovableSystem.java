package me.spaceshooter.game.systems;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.event.core.Observer;
import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;

public class MovableSystem extends GameSystem {


    @Override
    public void update(List<Entity> entityList, float dt) {
        for(Entity entity: entityList) {
            if (entity.getComponent(InputComponent.class) != null && entity.getComponent(VelocityComponent.class) != null){
                Vector2 direction = entity.getComponent(InputComponent.class).getDirection();
                Vector2 acceleration = new Vector2(entity.getComponent(VelocityComponent.class).getActiveAcceleration());
                entity.getComponent(VelocityComponent.class).setAccelerationChange(acceleration.scl(direction));
            }

        }
    }

    @Override
    public void addObserver(Observer ob) {

    }

    public MovableSystem() {
    }

}
