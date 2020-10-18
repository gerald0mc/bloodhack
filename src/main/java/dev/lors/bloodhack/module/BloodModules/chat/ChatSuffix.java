package dev.lors.bloodhack.module.BloodModules.chat;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", Category.CHAT);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String suffix = "  | \u0299\u029f\u1d0f\u1d0f\u1d05\u029c\u1d00\u1d04\u1d0b";
        if (event.getMessage().startsWith("/")) return; //commands
        if (event.getMessage().startsWith(BloodHack.prefix)) return; //client suffix
        event.setMessage(event.getMessage() + suffix);
    }
}
