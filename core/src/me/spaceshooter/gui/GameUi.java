package me.spaceshooter.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import me.spaceshooter.Constants;
import me.spaceshooter.event.core.Event;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.game.core.Entity;

public class GameUi implements Observer {

    Skin skin;
    Stage stage;
    Label fpsLabel,accelerationLabel,velocityLabel,positionLabel, collisionLabel;
    Table debugTable;
    int height, width;
    Float timer = 0.0f;
    String position = "";
    String velocity = "";
    String acceleration = "";
    String collision = "";

    public GameUi(int width, int height) {
        this.stage = new Stage();
        this.skin = new Skin();
        this.width = width;
        this.height = height;
        createUi();
    }

    private void createUi(){
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);
        if(Constants.DEBUGUITEXT) {
            createDebugUi();
            stage.addActor(debugTable);
        }
        stage.setDebugAll(Constants.DEBUGUI);
    }
    public void createDebugUi(){
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        fpsLabel = new Label("fps:", this.skin);
        accelerationLabel = new Label("acceleration:", this.skin);
        velocityLabel = new Label("velocity:", this.skin);
        positionLabel = new Label("position:", this.skin);
        collisionLabel = new Label("", this.skin);
        collisionLabel.setPosition(100,100);
        debugTable = new Table();
        debugTable.add(fpsLabel).width(100).row();
        debugTable.add(accelerationLabel).width(100).row();
        debugTable.add(velocityLabel).width(100).row();
        debugTable.add(positionLabel).width(100).row();
        debugTable.add(collisionLabel).width(100).row();
        debugTable.setPosition(this.width/2.0f, height-100);
        debugTable.setBackground(textureRegionDrawableBg);
    }

    public void update(float dt){

        timer+=dt;
        if (timer >= 0.05) {
            timer -= 0.05f;
            updateDebug(dt);

        }
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 10f));
        this.stage.draw();

    }

    public void updateDebug(float dt){
        if(Constants.DEBUGUITEXT) {
            fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());
            positionLabel.setText(this.position);
            velocityLabel.setText(this.velocity);
            accelerationLabel.setText(this.acceleration);
            collisionLabel.setText(this.collision);
        }
    }

    public Stage getStage(){
        return this.stage;
    }

    @Override
    public void onNotify(Entity entity, Event event) {
        switch (event.getEventType())
        {
            case DEBUG_ACCELERATION:
                acceleration = event.message;
                break;
            case DEBUG_VELOCITY:
                velocity = event.message;
                break;
            case DEBUG_POSITION:
                position = event.message;
                break;
            case DEBUG_COLLISION:
                collision = event.message;
                break;
        }
    }
}
