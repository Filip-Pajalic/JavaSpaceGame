package me.spaceshooter.game.systems;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;
import java.util.Map;


import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.game.components.CollisionComponent;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.game.entities.BackgroundEntity;
import me.spaceshooter.gui.GameUi;

public class RenderSystem extends GameSystem {
    private final SpriteBatch batch;
    private GameUi gameUi;

    private final ShapeRenderer shapeRenderer;
    private Camera camera;
    GraphicsCompoment backgroundGraphics;
    PositionComponent backgroundPosition;


    private Viewport viewport;

    public RenderSystem(int width, int height){
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(width,height, this.camera);
        BackgroundEntity background = new BackgroundEntity("background", width, height);
        this.backgroundGraphics = background.getComponent(GraphicsCompoment.class);
        this.backgroundPosition = background.getComponent(PositionComponent.class);
    }

    public void setGameUi(GameUi gameUi){
        this.gameUi = gameUi;
        this.gameUi.getStage().setViewport(this.viewport);
    }

    @Override
    public void update(List<Entity> entityList , float dt){
        this.batch.begin();
        this.batch.draw(backgroundGraphics.getTexture(), backgroundPosition.getPosition().x, backgroundPosition.getPosition().y, backgroundGraphics.getSizeX(), backgroundGraphics.getSizeY());
        this.batch.end();
        renderShape(entityList);
        renderTextures(entityList);
        this.gameUi.update(dt);
    }

    public void renderTextures(List<Entity> entityList){
        this.batch.begin();
        for(Entity entity : entityList){
            GraphicsCompoment entityGraphics = entity.getComponent(GraphicsCompoment.class);
            PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
            if(entityGraphics != null && entityPosition!=null){
                if(entityGraphics.getTexture() != null) {
                    this.batch.draw(entityGraphics.getTexture(), entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX(), entityGraphics.getSizeY());
                }
            }
        }
        this.batch.end();
    }

    public void renderShape(List<Entity> entityList){
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(Entity entity : entityList){
            GraphicsCompoment entityGraphics = entity.getComponent(GraphicsCompoment.class);
            PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
            CollisionComponent entityCollision = entity.getComponent(CollisionComponent.class);
            if(entityGraphics != null && entityPosition!=null){
                if(entityGraphics.getShape() != null){
                    switch ( entityGraphics.getShape()){
                        case CIRCLE:
                            this.shapeRenderer.circle(entityPosition.getPosition().x,entityPosition.getPosition().y,entityGraphics.getSizeX());
                            break;
                        case RECANGLE:
                            this.shapeRenderer.rect(entityPosition.getPosition().x,entityPosition.getPosition().y,entityGraphics.getSizeX(),entityGraphics.getSizeY());
                            break;
                    }
                    this.shapeRenderer.setColor(entityGraphics.getColor());
                }
            }
            //draw hitboxes
            if(Constants.DEBUG && entityCollision != null && entityPosition!=null){
                for(Map.Entry<CollisionComponent.basicCollisionShapes, Vector2> collisionShape: entityCollision.getCollisionShapes().entrySet()) {
                    switch (collisionShape.getKey()) {
                        case CIRCLE:
                            this.shapeRenderer.circle(entityPosition.getPosition().x,entityPosition.getPosition().y,collisionShape.getValue().x);
                            break;
                        case ELLIPSE:
                            break;
                        case RECT:
                            this.shapeRenderer.rect(entityPosition.getPosition().x,entityPosition.getPosition().y,collisionShape.getValue().x,collisionShape.getValue().y);
                            break;
                    }
                    this.shapeRenderer.setColor(Color.RED);
                }
            }
        }
        this.shapeRenderer.end();
    }

    @Override
    public void addObserver(Observer ob) {

    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return this.shapeRenderer;
    }

    public Stage getStage() {
        return this.gameUi.getStage();
    }

    public void dispose(){
        this.batch.dispose();
        this.shapeRenderer.dispose();
        this.gameUi.getStage().dispose();
    }
}
