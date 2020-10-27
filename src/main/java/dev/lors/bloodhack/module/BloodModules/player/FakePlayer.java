package dev.lors.bloodhack.module.BloodModules.player;

import com.mojang.authlib.GameProfile;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Module {

    Value<String> name = new Value<String>("Name", "", "Popbob");
    private EntityOtherPlayerMP _fakePlayer;

    public FakePlayer() {
        super("FakePlayer", Category.PLAYER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        _fakePlayer = null;

        if (mc.world == null) {
            this.toggle();
            return;
        }
        _fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(mc.player.getUniqueID(), name.value));
        mc.world.addEntityToWorld(_fakePlayer.getEntityId(), _fakePlayer);
        _fakePlayer.attemptTeleport(mc.player.posX, mc.player.posY, mc.player.posZ); // moves fake player to your current position
    }

    @Override
    public void onDisable() {
        if (!(mc.world == null)) {
            mc.world.removeEntity(_fakePlayer);
        }
    }

}