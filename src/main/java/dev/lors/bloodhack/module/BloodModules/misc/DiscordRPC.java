package dev.lors.bloodhack.module.BloodModules.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

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
        rpc.Discord_Initialize("", new DiscordEventHandlers(), true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;

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
                presence.details = "this is a test";
                presence.state = "Cool test";
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
