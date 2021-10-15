package me.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import java.util.ArrayList;
import java.util.List;

import me.spaceshooter.game.GameObject;
import me.spaceshooter.game.ShipObject;
import me.spaceshooter.game.components.PhysicsComponent;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private Texture background;

   //world parameters
    private final int WORLD_WIDTH = 320*2;
    private final int WORLD_HEIGHT = 640*2;
    private float gravityConstant = 20.1f;



    private boolean isRunning = false;

    private BitmapFont font;

    private Ship ship = new Ship();


    private ShapeRenderer shapeRenderer;
    private int fontinverval = 5;
    private String fonttext, fonttext2;
    private boolean debug;



    private List<GameObject> gameObjectList = new ArrayList<>();
    private GameObject gameObject;

    GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        background = new Texture("background.png");
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelmix.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderGamma = 255;
        parameter.borderWidth = 1;
        parameter.size = 10;
        font = generator.generateFont(parameter);
        generator.dispose();
        fonttext2  = "";
        fonttext = "";
        debug = false;
        shapeRenderer = new ShapeRenderer();
        gameObject = new ShipObject("ship");
        addGameObjectToScreen(this.gameObject);
        start();
    }

    public void start(){
        if(!isRunning){
            for(GameObject gameObject: gameObjectList){
                gameObject.start();
            }
        }
    }

    @Override
    public void render(float deltaTime) {
        this.isRunning = true;
        batch.enableBlending();

        batch.begin();
        renderBackground(deltaTime);


        batch.end();
        if(debug) {
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(ship.hitbox.getHitbox().x, ship.hitbox.getHitbox().y, ship.hitbox.getHitbox().width, ship.hitbox.getHitbox().height);
            shapeRenderer.end();
        }
        detectInput(deltaTime);
        detectCollision(deltaTime);
        for(GameObject gameObject : this.gameObjectList){
            gameObject.update(deltaTime);
        }
    }

    private void detectInput(float deltaTime) {

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            ship.setPower(true);
        }
        else{
            ship.setPower(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            ship.setPowerLeft(true);
        }
        else{
            ship.setPowerLeft(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            ship.setPowerRight(true);
        }
        else{
            ship.setPowerRight(false);
        }
    }

    private void renderBackground(float deltaTime){
        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(ship.getShipTexture(), ship.getxPosition(), ship.getyPosition(),ship.getSizex(),ship.getSizey());

        if(debug) {
            font.draw(batch, fonttext, 10, 600);
            font.draw(batch, fonttext2, 10, 575);
            font.draw(batch, "Position x: " + String.valueOf((int) ship.getxPosition()), 10, 550);
            font.draw(batch, "Position y: " + String.valueOf((int) ship.getyPosition()), 10, 525);
            font.draw(batch, "AccelerationSideways: " + String.valueOf(ship.getAccelerationSideways()), 10, 500);
            font.draw(batch, "VelocitySideways: " + String.valueOf(ship.getVelocitySideways()), 10, 475);
            if (fontinverval == 5) {
                fonttext = "Acceleration: " + String.valueOf(ship.getAcceleration());
                fonttext2 = "Velocity: " + String.valueOf(ship.getVelocity());
                fontinverval = 0;
            }
            fontinverval++;
        }
    }

    public void detectCollision(float deltaTime){
        float yChange, xChange;

        if(ship.isPower() || ship.getyPosition()>0) {
            ship.setAcceleration((ship.getThrust() - gravityConstant) * deltaTime);
            ship.setVelocity(ship.getVelocity() + ship.getAcceleration());

        }
        if(ship.isPower() && (ship.isPowerLeft() || ship.isPowerRight()  ) ) {

            ship.setAccelerationSideways(ship.getThrustSideways() * deltaTime);
            ship.setVelocitySideways(ship.getVelocitySideways() + ship.getAccelerationSideways());
        }
        if (ship.getyPosition() <0){
            ship.setPositionY(0f);
            ship.setAcceleration(0.0f);
            ship.setVelocity(0.0f);
            ship.setVelocitySideways(0.0f);
            ship.setAccelerationSideways(0.0f);
        }
        if(ship.getyPosition()>WORLD_HEIGHT-ship.getSizey()){
            ship.setPositionY(WORLD_HEIGHT-ship.getSizey());
            ship.setVelocity(0.0f);
            ship.setAccelerationSideways(0.0f);
        }
        if(ship.getxPosition()<0){
            System.out.printf("hello");
            ship.setPositionX(0);
            ship.setVelocitySideways(0.0f);
            ship.setAccelerationSideways(0.0f);
        }
        if(ship.getxPosition()>WORLD_WIDTH-ship.getSizex()){
            ship.setPositionX(WORLD_WIDTH-ship.getSizex());
            ship.setVelocitySideways(0.0f);
            ship.setAccelerationSideways(0.0f);
        }
        yChange = ship.getVelocity()*deltaTime;
        xChange = ship.getVelocitySideways()*deltaTime;
        ship.translate(xChange, yChange);
    }

    public void addGameObjectToScreen(GameObject gameObject){
        if(!isRunning){
            gameObjectList.add(gameObject);
        }
        else{
            gameObjectList.add(gameObject);
            gameObject.start();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
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
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
    @Override
    public void show() {

    }
}
