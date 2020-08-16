package git.obamadev.rewrite.module.modules.hud;

import git.obamadev.rewrite.clickgui.ClickGui;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", Category.HUD);
    }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.displayGuiScreen(new ClickGui());
            toggle();
        }
    }
}
