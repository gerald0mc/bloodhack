package git.obamadev.rewrite.module.modules.movement;

import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    public void onUpdate() {
        try {
                if (mc.gameSettings.keyBindForward.isKeyDown() && !(mc.player.collidedHorizontally)) {
                    if(!mc.player.isSprinting()) {
                        mc.player.setSprinting(true);
                    }
                }
            } catch (Exception ignored) {
        }
    }
}
