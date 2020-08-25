package git.obamadev.rewrite.module.modules.misc;

import git.obamadev.rewrite.managers.DiscordManager;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;

public class DiscordRPC extends Module {
    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }

    @Override
    public void onEnable() {
        DiscordManager.startup();
    }

    @Override
    public void onDisable() {
        DiscordManager.shutdown();
    }
}
