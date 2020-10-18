package dev.lors.bloodhack.module.BloodModules.chat;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

public class AutoSuicide extends Module {

    public AutoSuicide() {
        super("AutoSuicide", Category.CHAT);
    }

    public void onEnable(){mc.player.sendChatMessage("/kill"); this.toggle();}
    //1 line code :)
}




