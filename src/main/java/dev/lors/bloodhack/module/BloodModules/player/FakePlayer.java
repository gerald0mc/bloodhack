package dev.lors.bloodhack.module.BloodModules.player;

import com.mojang.authlib.GameProfile;
import dev.lors.bloodhack.managers.Setting;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.ArrayList;

public class FakePlayer extends Module {
    public Setting name;

    public FakePlayer() {
        super("FakePlayer", Category.PLAYER);

        ArrayList<String> list = new ArrayList<>();
        list.add("popbob");
        list.add("Fit");

        rSetting(name = new Setting("Name", this, "popbob", list, "name"));
    }

    private EntityOtherPlayerMP _fakePlayer;

    @Override
    public void onEnable()
    {
        super.onEnable();
        _fakePlayer = null;

        if (mc.world == null)
        {
            this.toggle();
            return;
        }

        String s = "";

        switch (name.getValString()) {
            case "popbob":
                s = "popbob";
                break;
            case "Fit":
                s = "Fit";
                break;
        }

        _fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(mc.player.getUniqueID(), s));

        mc.world.addEntityToWorld(_fakePlayer.getEntityId(), _fakePlayer);
        _fakePlayer.attemptTeleport(mc.player.posX, mc.player.posY, mc.player.posZ); // moves fake player to your current position
    }

    @Override
    public void onDisable()
    {
        if(!(mc.world == null)) {
            mc.world.removeEntity(_fakePlayer);
        }
    }
}