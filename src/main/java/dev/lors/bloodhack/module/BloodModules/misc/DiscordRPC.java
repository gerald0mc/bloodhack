package dev.lors.bloodhack.module.BloodModules.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;

public class DiscordRPC extends Module {
    DiscordRichPresence presence = new DiscordRichPresence();
    club.minnced.discord.rpc.DiscordRPC rpc = club.minnced.discord.rpc.DiscordRPC.INSTANCE;
    Value<Long> updateDelay = new Value<Long>("UpdateDelay", 1L, 0L, 10L);
    boolean connected;

    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }

    @Override
    public void onEnable() {
        start();
    }

    @Override
    public void onDisable() {
        end();
    }

    private void start() {
        if (connected) return;
        System.out.println("DiscordRPC started");
        connected = true;
        rpc.Discord_Initialize("767603853574144003", new DiscordEventHandlers(), true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;
        presence.largeImageKey = "blood_group_picture";
        presence.smallImageKey = "blood";

        /* update rpc while thread isn't interrupted  */
        new Thread(this::setRpcWithDelay, "Discord-RPC-Callback-Handler").start();

        System.out.println("Discord RPC initialised successfully");
    }

    void end() {
        if (!connected) return;
        System.out.println("DiscordRPC stopped");
        connected = false;
        rpc.Discord_Shutdown();
    }

    private void setRpcWithDelay() {
        while (!Thread.currentThread().isInterrupted() && connected) {
            try {
                String details = "In the menus";
                String state = "taking a shit";

                if (mc.isIntegratedServerRunning()) {
                    details = "Singleplayer - " + mc.getIntegratedServer().getWorldName();
                } else if (mc.currentScreen instanceof GuiMultiplayer) {
                    details = "Multiplayer Menu";
                } else if (mc.getCurrentServerData() != null) {
                    details = "On " + mc.getCurrentServerData().serverIP.toLowerCase();
                } else if (mc.currentScreen instanceof GuiWorldSelection) {
                    details = "Singleplayer Menu";
                }

                presence.details = details;
                presence.state = state;
                rpc.Discord_UpdatePresence(presence);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(updateDelay.value * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
