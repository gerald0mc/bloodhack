package git.obamadev.rewrite.module.modules.chat;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class PrefixChat extends Module {
    public PrefixChat() {
        super("PrefixChat", Category.CHAT);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (mc.currentScreen == null && Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) {
            mc.displayGuiScreen(new GuiChat(ObamaMod.prefix));
        }
    }
}