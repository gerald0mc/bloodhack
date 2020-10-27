package dev.lors.bloodhack.module.BloodModules.hud;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

public class ClickGUI extends Module {
    public Value<Boolean> rainbow = new Value<Boolean>("Rainbow", true);
    public Value<Integer> r = new Value<Integer>("Red", 0xcc, 0, 255);
    public Value<Integer> g = new Value<Integer>("Green", 0, 0, 255);
    public Value<Integer> b = new Value<Integer>("Blue", 0, 0, 255);
    public Value<Integer> a = new Value<Integer>("Alpha", 0xFF, 0, 255);
    public Value<Integer> br = new Value<Integer>("Background Red", 0xcc, 0, 255);
    public Value<Integer> bg = new Value<Integer>("Background Green", 0, 0, 255);
    public Value<Integer> bb = new Value<Integer>("Background Blue", 0, 0, 255);
    public Value<Integer> ba = new Value<Integer>("Background Alpha", 0xFF, 0, 255);
    public ClickGUI() {
        super("ClickGUI", Category.HUD);
    }

    @Override
    public void onEnable() {
        if (mc.player != null && mc.world != null) {
            mc.displayGuiScreen(BloodHack.gui);
            toggle();
        }
    }
}
