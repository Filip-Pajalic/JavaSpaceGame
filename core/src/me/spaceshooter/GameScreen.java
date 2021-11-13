package me.spaceshooter;


import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.ArrayList;
import java.util.List;

import me.spaceshooter.event.core.Observer;
import me.spaceshooter.event.observers.DebugObserver;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.game.entities.BackgroundEntity;
import me.spaceshooter.game.entities.ShipEntity;

import me.spaceshooter.game.entities.TargetEntity;
import me.spaceshooter.game.systems.MovableSystem;
import me.spaceshooter.game.systems.PhysicsSystem;
import me.spaceshooter.game.systems.RenderSystem;


public class GameScreen implements Screen {

   //world parameters

    private final int WORLD_WIDTH = 640;
    private final int WORLD_HEIGHT = 1280;
    private float gravityConstant = 60.1f;
    private boolean isRunning = false;
    private BitmapFont font;
    private boolean debug;
    private List<Entity> entityList = new ArrayList<>();
    private Entity entity;
    private GameSystem physicsSystem;
    private RenderSystem renderSystem;
    private MovableSystem movableSystem;
    private Observer debugObserver;

    GameScreen(){
        debug = false;
        entity = new ShipEntity("ship");
        addGameObjectToScreen(this.entity);
        entity = new TargetEntity("Circle");
        entity.getComponent(GraphicsCompoment.class).setShape(GraphicsCompoment.Shapes.CIRCLE);
        addGameObjectToScreen(this.entity);
        physicsSystem = new PhysicsSystem(gravityConstant,WORLD_WIDTH,WORLD_HEIGHT);
        renderSystem = new RenderSystem(WORLD_WIDTH,WORLD_HEIGHT);
        movableSystem = new MovableSystem();
        debugObserver = new DebugObserver();
        physicsSystem.addObserver(debugObserver);
        start();
    }

    public void start(){
        if(!isRunning){
            for(Entity entity : entityList){
                entity.start();
            }
        }
    }

    @Override
    public void render(float deltaTime) {
        this.isRunning = true;
        for(Entity entity : this.entityList){
            entity.update(deltaTime);
        }
        renderSystem.update(entityList,deltaTime);
        physicsSystem.update(entityList,deltaTime);
        movableSystem.update(entityList,deltaTime);

    }

    public void addGameObjectToScreen(Entity entity){
        if(!isRunning){
            entityList.add(entity);
        }
        else{
            entityList.add(entity);
            entity.start();
        }
    }

    @Override
    public void resize(int width, int height) {
        //GameSystem gs = GameSystem.cast(c)
        /* kanske kan göra en cast här som i component istället*/
        renderSystem.getViewport().update(width,height,true);
        renderSystem.getBatch().setProjectionMatrix(renderSystem.getCamera().combined);
        renderSystem.getShapeRenderer().setProjectionMatrix(renderSystem.getCamera().combined);

        renderSystem.getStage().getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //font.dispose();
        renderSystem.dispose();
    }
    @Override
    public void show() {

    }
}
