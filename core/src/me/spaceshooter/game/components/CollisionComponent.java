package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;


import java.util.ArrayList;
import java.util.List;

import me.spaceshooter.game.core.Component;

public class CollisionComponent extends Component {

    private Rectangle rect = null;
    private Circle circle = null;
    private boolean collided = false;

    private List<String> collisionList = new ArrayList<>();

    @Override
    public void update(float dt) {

    }


    public void setShapeCircle(Circle circle){
        this.circle = circle;
        this.rect = null;
    }

    public void setShapeRect(Rectangle rect){
        this.rect  = rect;
        this.circle = null;
    }

    public Rectangle getRect(){
        return this.rect;
    }

    public Circle getCircle(){
        return this.circle;
    }
    public void setCollided(boolean collided){
        this.collided = collided;
    }
    public boolean getCollided(){
        return this.collided;
    }

    public void addToCollisionList(String name){
        collisionList.add(name);
    }

    public boolean isInCollisionList(String name){
        for (String collisionName: collisionList) {
            if(collisionName.equals(name))
                return true;
        }
        return false;
    }

    public void clearCollisionList(){
        collisionList = new ArrayList<>();
    }

}
