package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.core.Component;

public class PositionComponent extends Component {

    private Vector2 position = new Vector2(0f,0f);


    @Override
    public void update(float dt) {



    }

    public void start(){

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }



}
