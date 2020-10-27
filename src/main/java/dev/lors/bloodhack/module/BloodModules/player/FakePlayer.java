package dev.lors.bloodhack.module.BloodModules.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FakePlayer extends Module {

    Value<String> name = new Value<String>("Name", "", "Popbob");

    public FakePlayer() {
        super("FakePlayer", Category.PLAYER);
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
            _fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(mc.player.getUniqueID(), name.value));
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