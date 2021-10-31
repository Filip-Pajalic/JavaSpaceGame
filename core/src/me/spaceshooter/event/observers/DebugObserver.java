package me.spaceshooter.event.observers;
import me.spaceshooter.event.core.Event;
import me.spaceshooter.event.core.Observer;
import me.spaceshooter.game.core.Entity;

public class DebugObserver implements Observer {

    @Override
    public void onNotify(Entity entity, Event event) {
        switch (event.getEventType())
        {
            case DEBUG_TEXT:
                debugMessage(event);
                break;
        }
    }
    private void debugMessage(Event event)
    {
        System.out.printf("event: %s", event.getMessage());
    }
}
