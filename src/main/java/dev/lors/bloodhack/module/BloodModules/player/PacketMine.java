package dev.lors.bloodhack.module.BloodModules.player;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.event.events.EventPlayerDamageBlock;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PacketMine extends Module {
    public PacketMine() {
        super("PacketMine", Category.PLAYER);
    }

    @Override
    public void onEnable() {
        //BloodHack.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onDisable() {
        //BloodHack.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<EventPlayerDamageBlock> OnDamageBlock = new Listener<>(p_Event ->
    {
        if(canBreak(p_Event.getPos())) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.player.connection.sendPacket(new CPacketPlayerDigging(
                    CPacketPlayerDigging.Action.START_DESTROY_BLOCK, p_Event.getPos(), p_Event.getDirection()));
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK,
                    p_Event.getPos(), p_Event.getDirection()));
            p_Event.cancel();
        }
    });

    private boolean canBreak(BlockPos pos)
    {
        final IBlockState blockState = mc.world.getBlockState(pos);
        final Block block = blockState.getBlock();

        return block.getBlockHardness(blockState, Minecraft.getMinecraft().world, pos) != -1;
    }

}