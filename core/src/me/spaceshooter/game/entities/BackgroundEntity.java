package me.spaceshooter.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.core.Entity;

public class BackgroundEntity extends Entity {


    public BackgroundEntity(String name, int sizeX, int sizeY) {
        super(name);
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("background.png"));
        getComponent(GraphicsCompoment.class).setSizeX(sizeX);
        getComponent(GraphicsCompoment.class).setSizeY(sizeY);
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(new Vector2(0,0));
    }
}
