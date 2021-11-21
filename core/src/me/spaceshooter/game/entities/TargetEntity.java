package me.spaceshooter.game.entities;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.GraphicsCompoment;

import me.spaceshooter.game.components.PositionComponent;

import me.spaceshooter.game.core.Entity;

public class TargetEntity extends Entity {
    private Vector2 spawnPosition, dimension;
    private int radius;


    public TargetEntity(String name) {
        super(name);
        spawnPosition = new Vector2(100,100);
        this.dimension = new Vector2(20,20);
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setSizeX((int) dimension.x);
        getComponent(GraphicsCompoment.class).setSizeY((int) dimension.y);

        getComponent(GraphicsCompoment.class).setColor(new Color(255,0,0,255));
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(spawnPosition);

        //Collision
        addComponent(new CollisionComponent());
        getComponent(CollisionComponent.class).setShapeCircle(new Circle(spawnPosition.x,spawnPosition.y,dimension.y));
    }


}
