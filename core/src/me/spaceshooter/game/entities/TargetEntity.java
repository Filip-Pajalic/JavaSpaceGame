package me.spaceshooter.game.entities;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.Vector2;


import me.spaceshooter.Hitbox;
import me.spaceshooter.game.components.GraphicsCompoment;

import me.spaceshooter.game.components.PositionComponent;

import me.spaceshooter.game.core.Entity;

public class TargetEntity extends Entity {
    private Vector2 spawnPosition, dimensions;
    private int radius;
    public Hitbox hitbox;

    public TargetEntity(String name) {
        super(name);
        spawnPosition = new Vector2(100,100);
        radius = 20;
        dimensions = new Vector2(10,10);
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setRadius(radius);
        getComponent(GraphicsCompoment.class).setSizeX((int) dimensions.x);
        getComponent(GraphicsCompoment.class).setSizeY((int) dimensions.y);

        getComponent(GraphicsCompoment.class).setColor(new Color(255,0,0,255));
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(spawnPosition);
    }


}
