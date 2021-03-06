package me.spaceshooter;


import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.spaceshooter.event.core.Observer;
import me.spaceshooter.event.observers.DebugObserver;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.game.entities.ShipEntity;

import me.spaceshooter.game.entities.TargetEntity;
import me.spaceshooter.game.systems.CollisionSystem;
import me.spaceshooter.game.systems.MovableSystem;
import me.spaceshooter.game.systems.PhysicsSystem;
import me.spaceshooter.game.systems.RenderSystem;
import me.spaceshooter.game.systems.ScoringSystem;
import me.spaceshooter.gui.GameUi;


public class GameScreen implements Screen {

    //world parameters

    private float gravityConstant = 60.1f;
    private boolean isRunning = false;
    private boolean debug;
    private List<Entity> entityList = new ArrayList<>();
    private Entity entity;
    private PhysicsSystem physicsSystem;
    private RenderSystem renderSystem;
    private MovableSystem movableSystem;
    private CollisionSystem collisionSystem;
    private ScoringSystem scoringSystem;
    private Observer debugObserver;
    private GameUi gameUi;

    GameScreen(){
        this.gameUi = new GameUi(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        entity = new ShipEntity("ship");
        addGameObjectToScreen(this.entity);
        entity = new TargetEntity("Circle" + UUID.randomUUID());
        entity.getComponent(GraphicsCompoment.class).setShape(GraphicsCompoment.Shapes.CIRCLE);
        addGameObjectToScreen(this.entity);
        collisionSystem = new CollisionSystem();
        physicsSystem = new PhysicsSystem(gravityConstant,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderSystem = new RenderSystem(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        movableSystem = new MovableSystem();
        debugObserver = new DebugObserver();
        scoringSystem = new ScoringSystem();
        renderSystem.setGameUi(this.gameUi);
        physicsSystem.addObserver(this.gameUi);
        collisionSystem.addObserver(this.gameUi);
        scoringSystem.addObserver(this.gameUi);
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
        collisionSystem.update(entityList,deltaTime);
        scoringSystem.update(entityList,deltaTime);

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
        renderSystem.getViewport().update(width,height,true);
        renderSystem.getBatch().setProjectionMatrix(renderSystem.getCamera().combined);
        renderSystem.getShapeRenderer().setProjectionMatrix(renderSystem.getCamera().combined);
        this.gameUi.setViewPort(renderSystem.getViewport());
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
