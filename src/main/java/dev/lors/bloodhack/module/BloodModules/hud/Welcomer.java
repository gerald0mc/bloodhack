package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.ColourUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Calendar;

public class Welcomer extends Module {
    public Welcomer() {
        super("Welcomer", Category.HUD);
    }

    Value<Float> x = new Value<Float>("X Position", 2.0f, 0.0f, 1000.0f);
    Value<Float> y = new Value<Float>("Y Position", 350.0f, 0.0f, 1000.0f);

    private String WelcomeMessages() {
        final int timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (timeOfDay < 12) {
            return "Good Morning, ";
        } else if (timeOfDay < 16) {
            return "Good Afternoon, ";
        } else if (timeOfDay < 21) {
            return "Good Evening, ";
        } else {
            return "Good Night, ";
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (mc.player != null && mc.world != null) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                mc.fontRenderer.drawStringWithShadow(
                        WelcomeMessages()
                                + mc.getSession().getUsername()
                        , x.value.floatValue()
                        , y.value.floatValue()
                        , ColourUtils.genRainbow());
            }
        }
    }
}
