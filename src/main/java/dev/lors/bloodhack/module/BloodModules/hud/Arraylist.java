package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.utils.ColourUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Arraylist extends Module {
    public Arraylist() {
        super("Arraylist", Category.HUD);
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

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (mc.player != null && mc.world != null) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                float currY = mc.fontRenderer.FONT_HEIGHT + 2;
                for (Module m : BloodHack.moduleManager.getModules()) {
                    if (m.isToggled()) {
                        mc.fontRenderer.drawStringWithShadow("\u300a" + m.getName() + "\u300b", 2, currY, GenRainbow());
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
        }
    }
}
