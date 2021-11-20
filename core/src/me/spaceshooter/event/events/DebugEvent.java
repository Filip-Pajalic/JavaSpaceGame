package me.spaceshooter.event.events;

import me.spaceshooter.event.core.EventTypes;
import me.spaceshooter.event.core.Event;

public class DebugEvent extends Event {



    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public DebugEvent(){
        eventType = EventTypes.DEBUG_TEXT;
    }

    public DebugEvent(EventTypes eventTypes){
        eventType = eventTypes;
    }

    @Override
    public EventTypes getEventType() {
        return eventType;
    }

}
