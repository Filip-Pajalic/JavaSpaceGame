package me.spaceshooter.game.components;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import me.spaceshooter.game.core.Component;

public class PowerComponent extends Component {

    private final Map<String,Boolean> powerList = new HashMap<>();

    private Map<String,Texture> textureList = new HashMap<>();

    @Override
    public void update(float dt) {

    }

    public boolean isPower(String powerName) {
        return this.powerList.get(powerName);
    }

    public void setPower(String powerName, boolean power) {
        this.powerList.put(powerName,power);
    }

    public Texture getTexture(String powerName) {
        return this.textureList.get(powerName);
    }

    public void setTexture(String powerName, Texture texture) {
        this.textureList.put(powerName,texture);
    }
}
