package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.managers.Setting;
import dev.lors.bloodhack.BloodHack;
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

    Setting x;
    Setting y;

    @Override
    public void setup() {
        BloodHack.settingsManager.rSetting(x = new Setting("X Position", this, 2, 0.0, 1000, false, "WelcomerX"));
        BloodHack.settingsManager.rSetting(y= new Setting("Y Position", this, 350, 0.0, 1000, false, "WelcomerY"));
    }

    public int GenRainbow() {
        int drgb;
        int color;
        int argb;
        float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
        int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
        int red = rgb >> 16 & 255;
        int green = rgb >> 8 & 255;
        int blue = rgb & 255;
        color = argb = ColourUtils.toRGBA(red, green, blue, 255);
        return color;
    }

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
                mc.fontRenderer.drawStringWithShadow(WelcomeMessages() + mc.getSession().getUsername(), x.getValInt(), y.getValInt(), GenRainbow());
            }
        }
    }
}
