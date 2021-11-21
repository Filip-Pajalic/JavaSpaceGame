package me.spaceshooter.game.systems;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Event;

import me.spaceshooter.event.core.EventTypes;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.event.core.Subject;
import me.spaceshooter.event.events.DebugEvent;
import me.spaceshooter.event.subjects.PhysicsSubject;
import me.spaceshooter.game.components.GraphicsCompoment;

import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.gui.GameUi;



public class PhysicsSystem extends GameSystem {

    private float gravityAcceleration = 0.0f;
    private int maxPositionX = 0;
    private int maxPositionY = 0;
    private Subject subject;
    private Event event_velocity, event_position, event_acceleration;

    public PhysicsSystem(float gravityAcceleration, int maxPositionX, int maxPositionY){
        this.subject = new PhysicsSubject();
        this.gravityAcceleration = gravityAcceleration;
        this.maxPositionX = maxPositionX;
        this.maxPositionY = maxPositionY;
        event_velocity = new DebugEvent(EventTypes.DEBUG_VELOCITY);
        event_position = new DebugEvent(EventTypes.DEBUG_POSITION);
        event_acceleration = new DebugEvent(EventTypes.DEBUG_ACCELERATION);
    }
    @Override
    public void addObserver(Observer ob){
        this.subject.addObserver(ob);
    }

    @SuppressWarnings("DefaultLocale")
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

                if(Constants.DEBUGUITEXT ) {
                    event_acceleration.setMessage("Acceleration x: " + String.format("%.2f", acceleration.y) +"    Acceleration y: " + String.format("%.2f", acceleration.x));
                    event_velocity.setMessage("Velocity x: " + String.format("%.1f", velocity.y) +"    Velocity y: " + String.format("%.1f", velocity.x));
                    event_position.setMessage("Position x: " + String.format("%.0f", position.y) +"    Position y: " + String.format("%.0f", position.x));
                    this.subject.notify(entity, event_acceleration);
                    this.subject.notify(entity, event_velocity);
                    this.subject.notify(entity, event_position);
                }

            }
        }
    }

}
