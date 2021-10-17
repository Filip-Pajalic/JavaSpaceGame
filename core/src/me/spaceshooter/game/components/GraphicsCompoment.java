package me.spaceshooter.game.components;

import com.badlogic.gdx.graphics.Texture;

import me.spaceshooter.game.core.Component;

public class GraphicsCompoment extends Component {


    private Texture texture;

    private int sizeX = 0;
    private int sizeY = 0;

    @Override
    public void update(float dt) {

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
