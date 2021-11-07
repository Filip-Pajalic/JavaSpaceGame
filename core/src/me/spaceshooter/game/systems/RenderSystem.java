package me.spaceshooter.game.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.game.entities.BackgroundEntity;

public class RenderSystem extends GameSystem {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private BackgroundEntity background;
    GraphicsCompoment backgroundGraphics;
    PositionComponent backgroundPosition;

    private Viewport viewport;

    public RenderSystem(int width, int height){
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(width,height, this.camera);
        this.shapeRenderer = new ShapeRenderer();
        this.background = new BackgroundEntity("background", width, height);
        this.backgroundGraphics = this.background.getComponent(GraphicsCompoment.class);
        this.backgroundPosition = this.background.getComponent(PositionComponent.class);
    }
    @Override
    public void update(List<Entity> entityList , float dt){
        this.batch.begin();
        this.batch.draw(backgroundGraphics.getTexture(), backgroundPosition.getPosition().x, backgroundPosition.getPosition().y, backgroundGraphics.getSizeX(), backgroundGraphics.getSizeY());
        this.batch.end();
        renderShape(entityList);
        renderTextures(entityList);


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

    public void dispose(){
        this.batch.dispose();
        this.shapeRenderer.dispose();
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
                if(Constants.DEBUG.equals(true)){

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
            if(entityGraphics != null && entityPosition!=null){
                if(entityGraphics.getShape() != null){
                    switch ( entityGraphics.getShape()){
                        case CIRCLE:
                            this.shapeRenderer.circle(entityPosition.getPosition().x,entityPosition.getPosition().y,entityGraphics.getRadius());
                            break;
                        case RECANGLE:
                            this.shapeRenderer.rect(entityPosition.getPosition().x,entityPosition.getPosition().y,entityGraphics.getSizeX(),entityGraphics.getSizeY());
                            break;
                    }
                    this.shapeRenderer.setColor(entityGraphics.getColor());
                }
                if(Constants.DEBUG.equals(true)){
                }
            }
        }
        this.shapeRenderer.end();
    }


}
