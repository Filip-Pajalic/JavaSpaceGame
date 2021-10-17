package me.spaceshooter.game.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import me.spaceshooter.game.components.GraphicsCompoment;
import me.spaceshooter.game.components.PositionComponent;
import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;

public class RenderSystem extends GameSystem {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;

    private Viewport viewport;

    public RenderSystem(int width, int height){
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(width,height, this.camera);
        this.shapeRenderer = new ShapeRenderer();
    }
    @Override
    public void update(List<Entity> entityList , float dt) {
        this.batch.begin();
        for(Entity entity : entityList){
            GraphicsCompoment entityGraphics = entity.getComponent(GraphicsCompoment.class);
            PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
            if(entityGraphics != null && entityPosition!=null){
                this.batch.draw(entityGraphics.getTexture(),entityPosition.getPosition().x,entityPosition.getPosition().y,entityGraphics.getSizeX(),entityGraphics.getSizeY());
            }
        }
        this.batch.end();
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
}
