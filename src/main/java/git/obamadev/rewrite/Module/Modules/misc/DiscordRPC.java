package git.obamadev.rewrite.Module.Modules.misc;

import git.obamadev.rewrite.Managers.DiscordManager;
import git.obamadev.rewrite.Module.Category;
import git.obamadev.rewrite.Module.Module;

public class DiscordRPC extends Module {
    public DiscordRPC(){
        super("DiscordRPC", Category.MISC);
    }

    @Override
    public void onEnable() { DiscordManager.start(); }

    @Override
    public void onDisable() {
        DiscordManager.Stop();
    }
}
