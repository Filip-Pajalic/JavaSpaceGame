package me.spaceshooter;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hitbox {

    private final Rectangle hitbox;

    public Hitbox(Vector2 position, Vector2 dimension){
        hitbox = new Rectangle();
        hitbox.setPosition(position);
        hitbox.setSize(dimension.x,dimension.y);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void updateHitboxPosition(Vector2 position){
        hitbox.setPosition(position.x, position.y);
    }
}
