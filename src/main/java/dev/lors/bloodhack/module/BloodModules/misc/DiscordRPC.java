package dev.lors.bloodhack.module.BloodModules.misc;

import dev.lors.bloodhack.managers.DiscordManager;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;

public class DiscordRPC extends Module {
    DiscordManager manager = new DiscordManager();
    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }

    @Override
    public void onEnable() { DiscordManager.startup(); }

    @Override
    public void onDisable() {
        DiscordManager.shutdown();
    }
}
