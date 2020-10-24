package dev.lors.bloodhack.event.events;

import dev.lors.bloodhack.event.Event;
import net.minecraft.entity.Entity;

public class EntityUseTotemEvent extends Event {
    private Entity entity;

    public EntityUseTotemEvent(Entity entity) {
        super();
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}