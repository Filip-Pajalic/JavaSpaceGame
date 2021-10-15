package me.spaceshooter.game.components;

import com.badlogic.gdx.graphics.Texture;

import me.spaceshooter.game.Component;

public class GraphicsCompoment extends Component {


    private Texture texture;

    @Override
    public void update(float dt) {

    }

    public void start(){

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
