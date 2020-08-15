package git.obamadev.rewrite.module.modules.hud;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.managers.Setting;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Stats extends Module {
    public Stats(){
        super("Stats", Category.HUD);
    }
    Setting xacc;
    Setting yacc;
    @Override
    public void setup() {
        rSetting(xacc = new Setting("X", this, 100, 0.0, 1000, false, "statsyeeeeeex"));
        rSetting(yacc= new Setting("Y", this, 10, 0.0, 1000, false, "statsyeeeeeey"));


    }
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (mc.player != null || mc.world != null) {
            if (this.isToggled()) {
                if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                    mc.fontRenderer.drawStringWithShadow("Test YEEEEEEEEEEEE", xacc.getValInt(), yacc.getValInt(), -1);
                }
            }
        }
    }
}
