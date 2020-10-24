package dev.lors.bloodhack.module.BloodModules.movement;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

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
