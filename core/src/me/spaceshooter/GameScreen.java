package me.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.ArrayList;
import java.util.List;

import me.spaceshooter.game.core.Entity;
import me.spaceshooter.game.core.GameSystem;
import me.spaceshooter.game.entities.BackgroundEntity;
import me.spaceshooter.game.entities.ShipEntity;

import me.spaceshooter.game.systems.PhysicsSystem;
import me.spaceshooter.game.systems.RenderSystem;


public class GameScreen implements Screen {

   //world parameters
    private final int WORLD_WIDTH = 320*2;
    private final int WORLD_HEIGHT = 640*2;
    private float gravityConstant = 0.1f;

    enum LEVELS {
        LEVEL1
    }

    private boolean isRunning = false;

    private BitmapFont font;

    private Ship ship = new Ship();
    private boolean debug;
    private List<Entity> entityList = new ArrayList<>();
    private Entity entity;
    private GameSystem physicsSystem;
    private RenderSystem renderSystem;

    GameScreen(){


        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelmix.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderGamma = 255;
        parameter.borderWidth = 1;
        parameter.size = 10;
        font = generator.generateFont(parameter);
        generator.dispose();
        fonttext2  = "";
        fonttext = "";*/
        debug = false;

        entity = new BackgroundEntity("background", WORLD_WIDTH, WORLD_HEIGHT);
        addGameObjectToScreen(this.entity);
        entity = new ShipEntity("ship");
        addGameObjectToScreen(this.entity);
        physicsSystem = new PhysicsSystem(gravityConstant,WORLD_WIDTH,WORLD_HEIGHT);
        renderSystem = new RenderSystem(WORLD_WIDTH,WORLD_HEIGHT);
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
        renderSystem.update(entityList,deltaTime);
        physicsSystem.update(entityList,deltaTime);

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
     /*

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
        }*/
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
        renderSystem.dispose();
    }
    @Override
    public void show() {

    }
}
