package nl.patrick.HOPE.Module.Modules;

import nl.patrick.HOPE.Module.Category;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.Managers.DiscordManager;

public class DiscordRPC extends Module {
    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }


    @Override
    public void selfSettings() {
        settings.addSetting("cheese", true);

    }

    public void onEnable() {
        DiscordManager.start();
    }

    @Override
    public void onDisable() {
        DiscordManager.Stop();
    }
}