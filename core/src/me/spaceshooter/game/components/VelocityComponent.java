package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.core.Component;

public class VelocityComponent extends Component {

    private Vector2 velocity = new Vector2(0f,0f);
    private Vector2 acceleration = new Vector2(0f,0f);
    private Vector2 accelerationChange = new Vector2(0f,0f);
    private Vector2 activeAccelearation = new Vector2(0f,0f);

    @Override
    public void update(float dt) {

    }

    public void start(){

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAccelerationChangeX(float velocityX) {
        this.acceleration.x = velocityX;
    }
    public void setAccelerationChangeY(float velocityY) {
        this.acceleration.y = velocityY;
    }

    public Vector2 getAccelerationChange() {
        return accelerationChange;
    }

    public void setAccelerationChange(Vector2 accelerationChange) {
        this.accelerationChange = accelerationChange;
    }

    public void setActiveAccelearation(Vector2 activeAccelearation) {
        this.activeAccelearation = activeAccelearation;
    }

    public Vector2 getActiveAccelearation() {
        return activeAccelearation;
    }

}
