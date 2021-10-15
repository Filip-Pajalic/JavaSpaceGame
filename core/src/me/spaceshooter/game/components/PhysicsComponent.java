package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.Component;

public class PhysicsComponent extends Component {


    private float gravityAcceleration;
    private Vector2 acceleration;
    private Vector2 accelerationChange;
    private Vector2 velocity;

    @Override
    public void update(float dt) {


        acceleration.y = (accelerationChange.y - gravityAcceleration) * dt;
        velocity.y = velocity.y + acceleration.y;
        velocity.x = velocity.x + acceleration.x;
        velocity.add(velocity.add(acceleration));
    }

    @Override
    public void start(){
        System.out.println("starting");

    }

    public float getGravityAcceleration() {
        return gravityAcceleration;
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
