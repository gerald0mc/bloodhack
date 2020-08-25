package git.obamadev.rewrite.managers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class MessageManager {
    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;
    public static String prefix = TextFormatting.GRAY + "[" + TextFormatting.RED + "ObamaHack" + TextFormatting.GRAY + "]";

    public static void sendRawMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    public static void sendMessagePrefix(String message) {
        sendRawMessage(prefix + " " + message);
    }
}