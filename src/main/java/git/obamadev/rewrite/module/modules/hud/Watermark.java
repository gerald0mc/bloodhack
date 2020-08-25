package git.obamadev.rewrite.module.modules.hud;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;
import git.obamadev.rewrite.utils.ColourUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Watermark extends Module {
    public Watermark() {
        super("Watermark", Category.HUD);
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
        if (mc.player != null || mc.world != null) {
            if (this.isToggled()) {
                if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                    mc.fontRenderer.drawStringWithShadow(ObamaMod.name + " " + ObamaMod.version, 2, 2, GenRainbow());
                }
            }
        }
    }
}
