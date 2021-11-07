package me.spaceshooter.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.spaceshooter.game.core.Component;

public class GraphicsCompoment extends Component {

    public enum Shapes{
        CIRCLE,
        RECANGLE,
        OTHER
    }

    private Texture texture = null;
    private Shapes shape = null;
    private Color color = null;

    private int sizeX = 0;
    private int sizeY = 0;
    private int radius = 0;

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

    public void setShape(Shapes shape){
        this.shape = shape;
    }

    public Shapes getShape(){
        return shape;

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
