package git.obamadev.rewrite.module.modules.movement;

import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class HoleTP extends Module {
    public HoleTP() {
        super("HoleTP", Category.MOVEMENT);
    }

    public void onUpdate() {
        if (mc.player.onGround) {
            --mc.player.motionY;
        }
    }
}
