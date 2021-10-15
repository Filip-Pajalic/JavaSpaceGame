package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import me.spaceshooter.game.Component;

public class PositionComponent extends Component {

    private Vector2 position = new Vector2(0f,0f);
    private Vector2 offsetPosition = new Vector2(0f,0f);
    private int maxPositionX = 0;
    private int maxPositionY = 0;

    @Override
    public void update(float dt) {

        if (position.y <0){
            position.y = 0;
        }
        if(position.y>maxPositionY-offsetPosition.y){
            position.y = maxPositionY-offsetPosition.y;
        }
        if (position.x <0){
            position.x = 0;
        }
        if(position.x>maxPositionX-offsetPosition.x){
            position.x = maxPositionX-offsetPosition.x;
        }

    }

    public void start(){

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getMaxPositionX() {
        return maxPositionX;
    }

    public void setMaxPositionX(int maxPositionX) {
        this.maxPositionX = maxPositionX;
    }

    public int getMaxPositionY() {
        return maxPositionY;
    }

    public void setMaxPositionY(int maxPositionY) {
        this.maxPositionY = maxPositionY;
    }

    public Vector2 getOffsetPosition() {
        return offsetPosition;
    }

    public void setOffsetPosition(Vector2 offsetPosition) {
        this.offsetPosition = offsetPosition;
    }


}
