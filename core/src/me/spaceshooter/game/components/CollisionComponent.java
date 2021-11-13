package me.spaceshooter.game.components;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import me.spaceshooter.game.core.Component;

public class CollisionComponent extends Component {

    public enum basicCollisionShapes {
        CIRCLE,
        RECT,
        ELLIPSE
    }

    private Map<basicCollisionShapes,Vector2> shapesList =  new HashMap<>();

    @Override
    public void update(float dt) {

    }

    public Map<basicCollisionShapes,Vector2> getCollisionShapes(){
        return shapesList;
    }

    public void addCollisionShape(basicCollisionShapes type, Vector2 dimension){
        shapesList.put(type,dimension);
    }

}
