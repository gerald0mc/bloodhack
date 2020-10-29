package dev.lors.bloodhack.module.BloodModules.render;

import dev.lors.bloodhack.event.events.RenderWorldEvent;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.RenderUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class ChunkOverlay extends Module {
    public ChunkOverlay() {
        super("ChunkOverlay", Category.RENDER);
    }

    @EventHandler
    Listener<RenderWorldEvent> event = new Listener<RenderWorldEvent>(e -> {
        RenderUtil.drawRect(10, 10, 10, 10, -1);
    });
}
