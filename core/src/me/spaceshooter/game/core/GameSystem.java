package me.spaceshooter.game.core;

import java.util.List;

public abstract class GameSystem {

    public abstract void update(List<Enitity> entities , float dt);
}
