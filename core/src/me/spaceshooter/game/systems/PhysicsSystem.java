package me.spaceshooter.game.systems;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Event;
import me.spaceshooter.event.core.EventTypes;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.event.events.DebugEvent;
import me.spaceshooter.event.subjects.PhysicsSubject;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import sun.security.util.Debug;


public class PhysicsSystem extends GameSystem {

    private float gravityAcceleration = 0.0f;
    private int maxPositionX = 0;
    private int maxPositionY = 0;

    private PhysicsSubject physicsSubject;
    private Event ev;


    public PhysicsSystem(float gravityAcceleration, int maxPositionX, int maxPositionY){
        this.gravityAcceleration = gravityAcceleration;
        this.maxPositionX = maxPositionX;
        this.maxPositionY = maxPositionY;
        physicsSubject = new PhysicsSubject();
        ev = new DebugEvent();
    }

    @Override
    public void addObserver(Observer ob){
        physicsSubject.addObserver(ob);
    }

    @Override
    public void update(List<Entity> entityList , float dt) {

        for(Entity entity: entityList) {
            if (entity.getComponent(PositionComponent.class) != null && entity.getComponent(VelocityComponent.class) != null && entity.getComponent(GraphicsCompoment.class) != null){
                Vector2 position = entity.getComponent(PositionComponent.class).getPosition();
                Vector2 velocity = entity.getComponent(VelocityComponent.class).getVelocity();
                Vector2 acceleration = entity.getComponent(VelocityComponent.class).getAcceleration();
                Vector2 accelerationChange = entity.getComponent(VelocityComponent.class).getAccelerationChange();
                Vector2 offsetPosition = new Vector2(entity.getComponent(GraphicsCompoment.class).getSizeX(),entity.getComponent(GraphicsCompoment.class).getSizeY());

                acceleration.y = (accelerationChange.y - gravityAcceleration) * dt;
                acceleration.x = (accelerationChange.x) * dt;
                velocity.y = velocity.y + acceleration.y;
                velocity.x = velocity.x + acceleration.x;

                if (position.y < 0) {
                    position.y = 0;
                    velocity.y = 0.0f;
                    acceleration.y = 0.0f;
                    velocity.x = 0.0f;
                    acceleration.x = 0.0f;
                }
                if (position.y > maxPositionY - offsetPosition.y) {
                    position.y = maxPositionY - offsetPosition.y;
                    velocity.y = 0.0f;
                }
                if (position.x < 0) {
                    position.x = 0;
                    velocity.x = 0.0f;
                    acceleration.x = 0.0f;
                }
                if (position.x > maxPositionX - offsetPosition.x) {
                    position.x = maxPositionX - offsetPosition.x;
                    velocity.x = 0.0f;
                    acceleration.x = 0.0f;
                }
                float yChange = velocity.y*dt;
                float xChange = velocity.x*dt;
                entity.getComponent(PositionComponent.class).translate(xChange, yChange);
                String message = entity.toString()  + " velocity :" +  velocity + "\n";

                if(Constants.DEBUG ) {
                    //ev.setMessage(message);
                    //physicsSubject.notify(entity, ev);
                }

            }
        }
    }
}
