package me.spaceshooter.game.systems;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Enitity;
import me.spaceshooter.game.core.GameSystem;


public class Physics extends GameSystem {

    private float gravityAcceleration;



    private Vector2 offsetPosition = new Vector2(0f,0f);
    private int maxPositionX = 0;
    private int maxPositionY = 0;


    public Physics(float gravityAcceleration, int maxPositionX, int maxPositionY){
        this.gravityAcceleration = gravityAcceleration;
        this.maxPositionX = maxPositionX;
        this.maxPositionY = maxPositionY;

    }

    @Override
    public void update(List<Enitity> entityList , float dt) {

        for(Enitity entity: entityList) {

            if (entity.getComponent(InputComponent.class) != null && entity.getComponent(VelocityComponent.class) != null){
                Vector2 position = entity.getComponent(PositionComponent.class).getPosition();
                Vector2 velocity = entity.getComponent(VelocityComponent.class).getVelocity();
                Vector2 acceleration = entity.getComponent(VelocityComponent.class).getAcceleration();
                Vector2 accelerationChange = entity.getComponent(VelocityComponent.class).getAccelerationChange();
                acceleration.y = (accelerationChange.y - gravityAcceleration) * dt;
                acceleration.x = (accelerationChange.x) * dt;
                velocity.y = velocity.y + acceleration.y;
                velocity.x = velocity.x + acceleration.x;
                velocity.add(velocity.add(acceleration));

                if (position.y < 0) {
                    position.y = 0;
                }
                if (position.y > maxPositionY - offsetPosition.y) {
                    position.y = maxPositionY - offsetPosition.y;
                }
                if (position.x < 0) {
                    position.x = 0;
                }
                if (position.x > maxPositionX - offsetPosition.x) {
                    position.x = maxPositionX - offsetPosition.x;
                }
                float yChange = velocity.y*dt;
                float xChange = velocity.x*dt;
                entity.getComponent(PositionComponent.class).translate(xChange, yChange);
            }
        }
    }
}