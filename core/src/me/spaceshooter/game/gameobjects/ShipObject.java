package me.spaceshooter.game.gameobjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.InputComponent;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.components.ShipPhysicsComponent;
import me.spaceshooter.game.components.VelocityComponent;
import me.spaceshooter.game.core.GameObject;

public class ShipObject extends GameObject {


    public ShipObject(String name) {
        super(name);
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("ship1.png"));
        addComponent(new ShipPhysicsComponent());
        addComponent(new PositionComponent());
        addComponent(new VelocityComponent());
        addComponent(new InputComponent());
        getComponent(InputComponent.class).addInput(Input.Keys.W,new Vector2(0,1));
        getComponent(InputComponent.class).addInput(Input.Keys.S,new Vector2(0,-1));
        getComponent(InputComponent.class).addInput(Input.Keys.A,new Vector2(-1,0));
        getComponent(InputComponent.class).addInput(Input.Keys.D,new Vector2(1,0));
    }



}
