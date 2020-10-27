package dev.lors.bloodhack.module.BloodModules.movement;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    public void onUpdate() {
        try {
            if (mc.gameSettings.keyBindForward.isKeyDown() && !(mc.player.collidedHorizontally)) {
                if (!mc.player.isSprinting()) {
                    mc.player.setSprinting(true);
                }
            }
        } catch (Exception ignored) {
        }
    }
}
