package me.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ship {

    private float movementSpeed, velocity, acceleration, thrust;
    private Vector2 position, dimension;
    private Texture shipTexture, shipPoweredTexture;
    public Hitbox hitbox;
    private boolean power;

    public Ship(){
        movementSpeed = 100;
        shipTexture= new Texture("ship1.png");
        shipPoweredTexture = new Texture("ship1powered.png");
        position = new Vector2(320/2-20/2,10);
        dimension = new Vector2(20,20);
        hitbox = new Hitbox(position,dimension);
        velocity = 0;
        thrust = 40.0f;
        acceleration = 0.0f;
        power = false;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPositionY(float positionY) {
        this.position.y = positionY;
        this.hitbox.updateHitboxPosition(position);
    }

    public float getThrust() {
        if (this.isPower()){
            return thrust;
        }
        return 0.0f;
    }

    public void setThrust(float thrust) {
        this.thrust = thrust;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }


    public float getxPosition() {
        return position.x;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getyPosition() {
        return position.y;
    }

    public Texture getShipTexture() {
        if (this.isPower()) {
            return shipPoweredTexture;
        }
        return shipTexture;
    }

    public void translate(float xChange, float yChange){
        this.position.x = this.position.x + xChange;
        this.position.y = this.position.y + yChange;
        this.hitbox.updateHitboxPosition(position);
    }

    public float getSizex() {
        return dimension.x;
    }

    public void setSizex(float sizex) {
        this.dimension.x = sizex;
    }



    public float getSizey() {
        return dimension.y;
    }

    public void setSizey(float sizey) {
        this.dimension.y = sizey;
    }
}
