package dev.lors.bloodhack.event.events;

import dev.lors.bloodhack.event.Event;

public class PlayerJoinEvent extends Event {
    private final String name;

    public PlayerJoinEvent(String n){
        super();
        name = n;
    }

    public String getName(){
        return name;
    }
}
