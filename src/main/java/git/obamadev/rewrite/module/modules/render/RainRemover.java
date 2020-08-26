package git.obamadev.rewrite.module.modules.render;

import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class RainRemover extends Module {
    public RainRemover() {
        super("RainRemover", Category.RENDER);
    }

    public void onUpdate() {
        if (mc.world == null) return;
        if (mc.world.isRaining()) {
            mc.world.setRainStrength(0);
        }
    }
}
