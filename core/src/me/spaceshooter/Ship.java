package me.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ship {

    private float movementSpeed, velocity, acceleration, accelerationSideways, thrust, thrustSideways, velocitySideways, velocityMax;
    private Vector2 position, dimension;
    private Texture shipTexture, shipPoweredTexture;
    public Hitbox hitbox;
    private boolean power, powerLeft, powerRight;

    public Ship(){
        movementSpeed = 100;
        shipTexture= new Texture("ship1.png");
        shipPoweredTexture = new Texture("ship1powered.png");
        position = new Vector2(320-20,10);
        dimension = new Vector2(40,40);
        hitbox = new Hitbox(position,dimension);
        velocity = 0;
        velocitySideways = 0;
        velocityMax = 80.0f;
        thrust = 40.0f;
        thrustSideways = 40.0f;
        acceleration = 0.0f;
        accelerationSideways = 0.0f;
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

    public void setPositionX(float positionX) {
        this.position.x = positionX;
        this.hitbox.updateHitboxPosition(position);
    }

    public float getThrust() {
        if (this.isPower()){
            return thrust;
        }
        return 0.0f;
    }

    public float getThrustSideways() {
        if (this.isPowerRight()){
            return thrustSideways;
        }else if (this.isPowerLeft()){
            return -thrustSideways;
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
        //if(this.velocity<this.velocityMax) {
        this.velocity = velocity;
        //}else
        //    this.velocity = velocityMax;
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

    public boolean isPowerLeft() {
        return powerLeft;
    }

    public void setPowerLeft(boolean powerLeft) {
        this.powerLeft = powerLeft;
    }

    public boolean isPowerRight() {
        return powerRight;
    }

    public void setPowerRight(boolean powerRight) {
        this.powerRight = powerRight;
    }

    public void setThrustSideways(float thrustSideways) {
        this.thrustSideways = thrustSideways;
    }

    public float getVelocitySideways() {
        return velocitySideways;
    }

    public void setVelocitySideways(float velocitySideways) {
        //if(this.velocitySideways<this.velocityMax)
        this.velocitySideways = velocitySideways;
        //else

        /*if(this.velocitySideways> -this.velocityMax)
            this.velocitySideways = velocitySideways;
        else
            this.velocitySideways = -this.velocityMax;*/
    }

    public float getAccelerationSideways() {
        return accelerationSideways;
    }

    public void setAccelerationSideways(float accelerationSideways) {
        this.accelerationSideways = accelerationSideways;
    }
}
