package git.obamadev.rewrite.managers;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import git.obamadev.rewrite.ObamaMod;

public class DiscordManager {
        private static final Minecraft mc = Minecraft.getMinecraft();
        private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
        public static DiscordRichPresence rp = new DiscordRichPresence();
        private static String details;
        private static String state;

        public static void startup() {
            final DiscordEventHandlers handlers = new DiscordEventHandlers();
            handlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
            rpc.Discord_Initialize(ObamaMod.appid, handlers, true, "");
            rp.startTimestamp = System.currentTimeMillis() / 1000L;
            rp.details = ObamaMod.name +  " " + ObamaMod.version;
            rp.state = "discord.gg/XcMd2Us";
            rp.largeImageKey = "rewrite";
            rp.largeImageText = ObamaMod.name +  " " + ObamaMod.version;
            rp.smallImageKey = "default";
            rp.smallImageText = mc.getSession().getUsername();

            rpc.Discord_UpdatePresence(rp);
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        rpc.Discord_RunCallbacks();
                        details = ObamaMod.name +  " " + ObamaMod.version;
                        state = "discord.gg/XcMd2Us";
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            }, "Discord-RPC-Callback-Handler").start();
        }
        public static void shutdown(){
            rpc.Discord_Shutdown();
        }
}
