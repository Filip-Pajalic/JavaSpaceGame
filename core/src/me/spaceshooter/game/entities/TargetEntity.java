package me.spaceshooter.game.entities;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.UUID;

import me.spaceshooter.Constants;
import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.GraphicsCompoment;

import me.spaceshooter.game.components.HealthComponent;
import me.spaceshooter.game.components.PositionComponent;

import me.spaceshooter.game.core.Entity;

public class TargetEntity extends Entity {
    private Vector2 spawnPosition;

    private int health;
    private int radiusHitbox, radius;


    public TargetEntity(String name) {
        super(name);
        addComponent(new PositionComponent());
        addComponent(new CollisionComponent());
        addComponent(new HealthComponent());
        randomizeProperties();
        addComponent(new GraphicsCompoment());

        getComponent(GraphicsCompoment.class).setFilled(new Color(1,0,0,0.1f));
        getComponent(GraphicsCompoment.class).setColor(new Color(1,0,0,1));
    }

    public void randomizeProperties(){
        this.health = MathUtils.random(20,50);
        setRadius();
        this.spawnPosition = new Vector2(MathUtils.random(10+this.radius, Constants.WORLD_WIDTH-10-this.radius),
                MathUtils.random(30+this.radius, Constants.WORLD_HEIGHT-50-this.radius));
        getComponent(PositionComponent.class).setPosition(spawnPosition);
        getComponent(CollisionComponent.class).setShapeCircle(new Circle(spawnPosition.x,spawnPosition.y,radiusHitbox));
        getComponent(HealthComponent.class).setHealth(this.health);
    }

    public void setRadius(){
        //ensure radius is only updated here and not other place in code.
        this.radius = this.health+10;
        this.radiusHitbox = this.health+15;
    }


}
