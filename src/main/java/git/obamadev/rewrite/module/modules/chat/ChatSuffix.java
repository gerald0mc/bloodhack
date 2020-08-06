package git.obamadev.rewrite.module.modules.chat;

import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix(){
        super("ChatSuffix", Category.CHAT);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String suffix = "  \u1d0f\u0299\u1d00\u1d0d\u1d00\u029c\u1d00\u1d04\u1d0b \u0299\u1d07\u1d1b\u1d00";
        if (event.getMessage().startsWith("/")) return;
        if (event.getMessage().startsWith(".")) return;
        event.setMessage(event.getMessage() + suffix);
    }
}
