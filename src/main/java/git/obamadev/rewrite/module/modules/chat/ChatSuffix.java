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
        String suffix = "  (ObamaHack)";
        if (event.getMessage().startsWith("/")) return;
        if (event.getMessage().startsWith(".")) return;
        event.setMessage(event.getMessage() + suffix);
    }
}
