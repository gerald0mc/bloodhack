package git.obamadev.rewrite.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import git.obamadev.rewrite.managers.MessageManager;
import git.obamadev.rewrite.module.Category;
import git.obamadev.rewrite.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityMule;

public class EntityAlert extends Module {
    public EntityAlert() {
        super("EntityAlert", Category.MISC);
    }

    private int donkeyDelay;
    private int llamaDelay;
    private int muleDelay;

    public void onUpdate() {
        if (this.isToggled()) {
            if (mc.world != null) {

                ++donkeyDelay;
                ++llamaDelay;
                ++muleDelay;

                for (Entity entity : mc.world.getLoadedEntityList()) {
                    if (entity instanceof EntityDonkey && this.donkeyDelay >= 100) {
                        MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "donkey " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                        this.donkeyDelay = -600;
                    } else if (entity instanceof EntityLlama && this.llamaDelay >= 100) {
                        MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "llama " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                        this.llamaDelay = -600;
                    } else if (entity instanceof EntityMule && this.muleDelay >= 100) {
                        MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "mule " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                        this.muleDelay = -600;
                    }
                }
            }
        }
    }
}