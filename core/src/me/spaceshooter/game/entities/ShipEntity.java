package me.spaceshooter.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;


import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.ScoreComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.Entity;

public class ShipEntity extends Entity {

    private float thrust, velocityMax;
    private Vector2  dimension, spawnPosition;

    public ShipEntity(String name) {
        super(name);

        this.dimension = new Vector2(20,20);
        this.spawnPosition = new Vector2(100,10);
        this.velocityMax = 80.0f;
        this.thrust = 120.0f;

        //Graphics
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("ship1.png"));
        getComponent(GraphicsCompoment.class).setSizeX((int)dimension.x);
        getComponent(GraphicsCompoment.class).setSizeY((int)dimension.y);

        //Position
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(spawnPosition);
        //Velocity
        addComponent(new VelocityComponent());

        //Input
        addComponent(new InputComponent());
        getComponent(InputComponent.class).addInput(Input.Keys.W,new Vector2(0,1));
        getComponent(InputComponent.class).addInput(Input.Keys.S,new Vector2(0,-1));
        getComponent(InputComponent.class).addInput(Input.Keys.A,new Vector2(-1,0));
        getComponent(InputComponent.class).addInput(Input.Keys.D,new Vector2(1,0));
        getComponent(VelocityComponent.class).setActiveAcceleration(thrust);

        //Collision
        addComponent(new CollisionComponent());
        getComponent(CollisionComponent.class).setShapeRect(new Rectangle(spawnPosition.x,spawnPosition.y,dimension.x,dimension.y));

        //Score
        addComponent(new ScoreComponent());
        getComponent(ScoreComponent.class).setScore(0);
    }
}
