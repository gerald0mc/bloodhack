package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.ColourUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Watermark extends Module {
    public Watermark() {
        super("Watermark", Category.HUD);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (mc.player != null && mc.world != null) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                mc.fontRenderer.drawStringWithShadow(BloodHack.name + " " + BloodHack.version, 2, 2, ColourUtils.genRainbow());
            }
        }
    }
}
