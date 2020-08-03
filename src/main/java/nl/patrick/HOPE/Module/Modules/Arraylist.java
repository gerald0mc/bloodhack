package nl.patrick.HOPE.Module.Modules;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Module.Category;
import nl.patrick.HOPE.Module.Module;

public class Arraylist extends Module {
    public Arraylist() {
        super("arraylist", Category.RENDER);
    }


    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if(this.isToggled()) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                float currY = mc.fontRenderer.FONT_HEIGHT + 5;
                for (Module m : Hope.moduleManager.getModules()) {
                    if (m.isToggled()){
                        mc.fontRenderer.drawStringWithShadow(m.getName(), 5, currY + 1, -1);
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
        }
    }
}
