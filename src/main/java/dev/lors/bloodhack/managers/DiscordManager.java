package dev.lors.bloodhack.managers;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import dev.lors.bloodhack.BloodHack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;

public class DiscordManager {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
    public static DiscordRichPresence rp = new DiscordRichPresence();
    private static String details;
    private static String state;

    public static void startup() {
        System.out.println("[BloodHack] Discord RPC Starting!");
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize(BloodHack.appid, handlers, true, "");
        rp.startTimestamp = System.currentTimeMillis() / 1000L;
        rp.largeImageKey = "https://imgur.com/tM2zA3a";
        rp.largeImageText = BloodHack.name +  " " + BloodHack.version;
        rp.smallImageKey = "data:image/jpeg;base64,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";
        rp.smallImageText = mc.getSession().getUsername();
        rpc.Discord_UpdatePresence(rp);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                        details = "In the menus";
                        state = "taking a shit";

                        if (mc.isIntegratedServerRunning()) {
                            details = "Singleplayer - " + mc.getIntegratedServer().getWorldName();
                        } else if (mc.currentScreen instanceof GuiMultiplayer) {
                            details = "Multiplayer Menu";
                        } else if (mc.getCurrentServerData() != null) {
                            details = "On " + mc.getCurrentServerData().serverIP.toLowerCase();
                        } else if (mc.currentScreen instanceof GuiWorldSelection) {
                            details = "Singleplayer Menu";
                        }

                    rp.details = details;
                    rp.state = state;
                    rpc.Discord_UpdatePresence(rp);
                } catch (Exception e1) {
                    e1.printStackTrace();
                } try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }, "RPC-Callback-Handler").start();
    }

    public static void shutdown() {
        System.out.println("[BloodHack] Discord RPC Shutting Down!");
        rpc.Discord_Shutdown();
    }
}
