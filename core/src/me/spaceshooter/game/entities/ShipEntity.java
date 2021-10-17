package me.spaceshooter.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.Hitbox;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;

public class ShipEntity extends Entity {

    private float movementSpeed, thrust, thrustSideways, velocityMax;
    private Vector2  dimension;
    private Texture shipPoweredTexture;
    public Hitbox hitbox;
    private boolean power;

    public ShipEntity(String name) {
        super(name);
        this.movementSpeed = 100;
        this.shipPoweredTexture = new Texture("ship1powered.png");
        this.dimension = new Vector2(40,40);
        this.hitbox = new Hitbox(dimension,dimension);
        this.velocityMax = 80.0f;
        this.thrust = 40.0f;
        this.thrustSideways = 40.0f;
        this.power = false;
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("ship1.png"));
        getComponent(GraphicsCompoment.class).setSizeX(40);
        getComponent(GraphicsCompoment.class).setSizeY(40);
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(new Vector2(100,20));
        addComponent(new VelocityComponent());
        addComponent(new InputComponent());
        getComponent(InputComponent.class).addInput(Input.Keys.W,new Vector2(0,1));
        getComponent(InputComponent.class).addInput(Input.Keys.S,new Vector2(0,-1));
        getComponent(InputComponent.class).addInput(Input.Keys.A,new Vector2(-1,0));
        getComponent(InputComponent.class).addInput(Input.Keys.D,new Vector2(1,0));
    }
}
