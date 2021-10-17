package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.core.Component;

public class VelocityComponent extends Component {

    private Vector2 velocity = new Vector2(0f,0f);
    private Vector2 acceleration = new Vector2(0f,0f);
    private Vector2 accelerationChange = new Vector2(0f,0f);

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

    public void setAcceleration(Vector2 velocity) {
        this.acceleration = velocity;
    }

    public Vector2 getAccelerationChange() {
        return accelerationChange;
    }

    public void setAccelerationChange(Vector2 accelerationChange) {
        this.accelerationChange = accelerationChange;
    }

}
