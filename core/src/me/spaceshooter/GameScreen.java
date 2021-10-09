package me.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import javax.xml.transform.Templates;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private Texture[] backgrounds;

    //timing
    private float[] backgroundOffsets = {0,0};
    private float backgroundMaxScrollingSpeed;

    //world parameters
    private final int WORLD_WIDTH = 320;
    private final int WORLD_HEIGHT = 640;

    private Ship ship;
    private float velocity;
    private BitmapFont font;
    private float gravityConstant = 20.1f;
    private float acceleration = 10.0f;
    private float totalAcceleration = 0.0f;
    float velocityY = 0.0f;
    private ShapeRenderer shapeRenderer;
    private int fontinverval = 5;
    private String fonttext, fonttext2;


    GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        backgrounds = new Texture[2];

        backgrounds[0] = new Texture("background.png");
        //backgrounds[1] = new Texture("stars.png");

        backgroundMaxScrollingSpeed = (float)WORLD_HEIGHT/4;

        batch = new SpriteBatch();
        ship = new Ship();
        velocity = 0.0f;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelmix.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderGamma = 255;
        parameter.borderWidth = 1;
        parameter.size = 10;
        font = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        fonttext2  = "";
        fonttext = "";
        shapeRenderer = new ShapeRenderer();


    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        //GL20 gl = Gdx.gl;
        //gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.enableBlending();
        batch.begin();




        renderBackground(deltaTime);

        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(ship.hitbox.getHitbox().x, ship.hitbox.getHitbox().y, ship.hitbox.getHitbox().width, ship.hitbox.getHitbox().height);
        shapeRenderer.end();
        detectInput(deltaTime);
        //detectCollision(deltaTime);



    }

    private void detectInput(float deltaTime) {

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            ship.setPower(true);
        }
        else{
            ship.setPower(false);
        }
        float yChange;

        yChange = ship.getVelocity()*deltaTime;
        if (ship.getyPosition() <0 && !ship.isPower()){
            ship.setPositionY(0f);
        }
        else if(ship.getyPosition()>WORLD_HEIGHT-ship.getSizey()){

            ship.setPositionY(WORLD_HEIGHT-ship.getSizey());
            ship.setVelocity(0.0f);

        }else{
            if(ship.isPower() || ship.getyPosition()>0) {
                ship.setAcceleration((ship.getThrust() - gravityConstant) * deltaTime);
                ship.setVelocity(ship.getVelocity() + ship.getAcceleration());
            }else
            {
                ship.setAcceleration(0);
                ship.setVelocity(0);
            }
            ship.translate(0, yChange);
        }


    }

    private void renderBackground(float deltaTime){
        batch.draw(backgrounds[0],0,0,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(ship.getShipTexture(), ship.getxPosition(), ship.getyPosition(),ship.getSizex(),ship.getSizey());
        font.draw(batch, fonttext, 10, 10);
        font.draw(batch,fonttext2 , 10, 25);
        font.draw(batch,"Position x: " + String.valueOf((int)ship.getxPosition()) , 10, 40);
        font.draw(batch,"Position y: " + String.valueOf((int)ship.getyPosition()) , 10, 55);
        if(fontinverval == 5) {
            fonttext = "Acceleration: " + String.valueOf(ship.getAcceleration());
            fonttext2 = "Velocity: " + String.valueOf(ship.getVelocity());
            fontinverval = 0;
        }
        fontinverval++;



    }

    public void detectCollision(float deltaTime){

        float dy = ship.getVelocity();
        
        if (ship.getyPosition() <0){
            dy = -dy;
            ship.translate(0f,0f);
        }
        else if(ship.getyPosition()>WORLD_HEIGHT){
            dy = -dy;
            //ship.translate(0f,WORLD_HEIGHT-ship.getSizey());
            ship.translate(0f,0f);

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
