package dev.lors.bloodhack.module.BloodModules.render;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    public void onEnable() {
        mc.gameSettings.gammaSetting = 100;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
