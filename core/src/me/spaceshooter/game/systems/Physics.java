package me.spaceshooter.game.systems;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.core.System;

public class Physics extends System {

    private float gravityAcceleration;
    private Vector2 acceleration;
    private Vector2 accelerationChange;
    private Vector2 velocity;

    private Vector2 position = new Vector2(0f,0f);
    private Vector2 offsetPosition = new Vector2(0f,0f);
    private int maxPositionX = 0;
    private int maxPositionY = 0;

    public void update(float dt) {


        acceleration.y = (accelerationChange.y - gravityAcceleration) * dt;
        velocity.y = velocity.y + acceleration.y;
        velocity.x = velocity.x + acceleration.x;
        velocity.add(velocity.add(acceleration));

        if (position.y <0){
            position.y = 0;
        }
        if(position.y>maxPositionY-offsetPosition.y){
            position.y = maxPositionY-offsetPosition.y;
        }
        if (position.x <0){
            position.x = 0;
        }
        if(position.x>maxPositionX-offsetPosition.x){
            position.x = maxPositionX-offsetPosition.x;
        }
    }
}
