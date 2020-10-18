package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.clickgui.ClickGui;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", Category.HUD);
    }

    @Override
    public void onEnable() {
        if (mc.player != null && mc.world != null) {
            mc.displayGuiScreen(new ClickGui());
            toggle();
        }
    }
}
