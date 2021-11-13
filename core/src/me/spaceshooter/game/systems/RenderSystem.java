package me.spaceshooter.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

public class RenderSystem extends GameSystem {
    private SpriteBatch batch;
    private Stage stage;
    private Table table;
    Skin skin;
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private BackgroundEntity background;
    GraphicsCompoment backgroundGraphics;
    PositionComponent backgroundPosition;


    private Viewport viewport;

    public RenderSystem(int width, int height){

        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(width,height, this.camera);
        this.stage = new Stage(this.viewport);

        this.background = new BackgroundEntity("background", width, height);
        this.backgroundGraphics = this.background.getComponent(GraphicsCompoment.class);
        this.backgroundPosition = this.background.getComponent(PositionComponent.class);
        createGui();
    }

    public void createGui(){
        Gdx.input.setInputProcessor(stage);

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libGDX font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button = new TextButton("Click me!", skin);
        table.add(button);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button.isChecked());
                button.setText("Good job!");
            }
        });

        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
        table.setDebug(Constants.DEBUG);
    }


    @Override
    public void update(List<Entity> entityList , float dt){
        this.batch.begin();
        this.batch.draw(backgroundGraphics.getTexture(), backgroundPosition.getPosition().x, backgroundPosition.getPosition().y, backgroundGraphics.getSizeX(), backgroundGraphics.getSizeY());
        this.batch.end();
        renderShape(entityList);
        renderTextures(entityList);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

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
        return this.stage;
    }

    public void dispose(){
        this.batch.dispose();
        this.shapeRenderer.dispose();
        this.stage.dispose();

    }


}
