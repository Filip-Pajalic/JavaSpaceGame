package me.spaceshooter.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;


import java.util.HashMap;
import java.util.Map;


import me.spaceshooter.game.Component;

public class InputComponent extends Component {

    protected Map<Integer, Vector2> inputMap = new HashMap<>();
    public Vector2 directon = new Vector2(0f,0f);

    public Vector2 getDirecton() {
        return directon;
    }

    public void setDirecton(Vector2 directon) {
        this.directon = directon;
    }

    @Override
    public void update(float dt) {

        for(Map.Entry<Integer, Vector2> input : inputMap.entrySet()){
            int key = input.getKey();
            Vector2 value = input.getValue();
            if(Gdx.input.isKeyPressed(key)){
                System.out.println(value.toString());
                directon = value;
                break;
            }
        }
    }

    public void start(){

    }

    public void addInput(int key, Vector2 value){
        inputMap.put(key,value);
    }
}
