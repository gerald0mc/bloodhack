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
    public EntityAlert(){
        super("EntityAlert", Category.MISC);
    }

    public void onUpdate() {
        if (this.isToggled()) {
            if (mc.player == null || mc.world == null) return;

            for (Entity entity : mc.world.getLoadedEntityList()) {
                if (entity instanceof EntityDonkey) {
                    MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "donkey " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ", " + Math.round(entity.lastTickPosY) + ", " + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                }
            }

            for (Entity entity : mc.world.getLoadedEntityList()) {
                if (entity instanceof EntityLlama) {
                    MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "llama " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ", " + Math.round(entity.lastTickPosY) + ", " + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                }
            }

            for (Entity entity : mc.world.getLoadedEntityList()) {
                if (entity instanceof EntityMule) {
                    MessageManager.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "mule " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.lastTickPosX) + ", " + Math.round(entity.lastTickPosY) + ", " + Math.round(entity.lastTickPosZ) + ChatFormatting.GRAY + "]");
                }
            }
        }
    }
}
