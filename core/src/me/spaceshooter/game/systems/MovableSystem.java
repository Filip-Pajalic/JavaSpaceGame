package me.spaceshooter.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;

public class MovableSystem extends GameSystem {


    @Override
    public void update(List<Entity> entityList, float dt) {
        for(Entity entity: entityList) {
            if (entity.getComponent(InputComponent.class) != null && entity.getComponent(VelocityComponent.class) != null){
                float directionX = entity.getComponent(InputComponent.class).getDirectionX();
                float directionY = entity.getComponent(InputComponent.class).getDirectionY();
                Vector2 acceleration = entity.getComponent(VelocityComponent.class).getActiveAccelearation();
                Vector2 acceleration2 = entity.getComponent(VelocityComponent.class).getAcceleration();

                System.out.println(acceleration2);

                if(directionY == 1.0f){
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeY(acceleration.y);
                }
                else{
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeY(0.0f);
                }
                if(directionX == 1.0f){
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeX(acceleration.x);
                }
                else{
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeX(0.0f);
                }
                if(directionX == -1.0f){
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeX(-acceleration.x);
                }
                else{
                    entity.getComponent(VelocityComponent.class).setAccelerationChangeX(0.0f);
                }

            }

        }
    }

    public MovableSystem() {
    }

}
