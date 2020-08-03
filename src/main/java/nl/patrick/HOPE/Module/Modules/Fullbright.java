package nl.patrick.HOPE.Module.Modules;

import nl.patrick.HOPE.Module.Category;
import nl.patrick.HOPE.Module.Module;

public class Fullbright extends Module {


    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    public void selfSettings() {
        settings.addSetting("hoi", "hoi2");
    }

    @Override
    public void onEnable() {
        if(mc.player != null) {
            mc.gameSettings.gammaSetting = +100;
        }
    }

    @Override
    public void onDisable() {
        if(mc.player != null) {
            mc.gameSettings.gammaSetting = -100;
        }
    }
}
