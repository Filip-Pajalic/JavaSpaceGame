package me.spaceshooter.game.core;

import java.util.List;

import me.spaceshooter.event.core.Observer;

public abstract class GameSystem {

    public abstract void update(List<Entity> entities , float dt);

    public abstract void addObserver(Observer ob);
}
