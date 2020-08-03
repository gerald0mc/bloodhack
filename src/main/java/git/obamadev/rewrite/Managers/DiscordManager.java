package git.obamadev.rewrite.Managers;

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

        public static void start() {
            final DiscordEventHandlers handlers = new DiscordEventHandlers();
            handlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
            rpc.Discord_Initialize(ObamaMod.discordid, handlers, true, "");
            rp.startTimestamp = System.currentTimeMillis() / 1000L;
            rp.details = ObamaMod.name +  " " + ObamaMod.currentvers;
            rp.state = "https://discord.gg/WRFdxV3";
            rp.largeImageKey = "oabamhack";
            rp.largeImageText = ObamaMod.name +  " " + ObamaMod.currentvers;

            rpc.Discord_UpdatePresence(rp);
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        rpc.Discord_RunCallbacks();
                        details = ObamaMod.name +  " " + ObamaMod.currentvers;
                        state = "https://discord.gg/WRFdxV3";
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
        public static void Stop(){
            rpc.Discord_Shutdown();
        }
    }
