package dev.lors.bloodhack.util;

 import dev.lors.bloodhack.util.font.CFontRenderer;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.entity.EntityPlayerSP;
 import net.minecraft.world.World;
 import org.lwjgl.input.Keyboard;

 public class Wrapper {
     final static Minecraft mc = Minecraft.getMinecraft();

     public static Minecraft GetMC() {
         return mc;
     }

     public static EntityPlayerSP GetPlayer() {
         return mc.player;
     }

     private static CFontRenderer fontRenderer;


     public static int getKey(String keyname) {
         return Keyboard.getKeyIndex(keyname.toUpperCase());
     }

     public static CFontRenderer getFontRenderer() {
         return fontRenderer;
     }

 }
