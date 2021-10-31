package me.spaceshooter.event.core;

import java.util.List;

import me.spaceshooter.game.core.Entity;

public interface Observer {

    public void onNotify(Entity entity, Event event);
}
