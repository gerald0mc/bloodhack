package dev.lors.bloodhack.event;

import com.google.common.hash.BloomFilter;
import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.event.events.RenderEvent;
import dev.lors.bloodhack.event.events.RenderWorldEvent;
import dev.lors.bloodhack.utils.BloodHackTessellator;
import dev.lors.bloodhack.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

public class Adapter {

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event)
    {
        if (event.isCanceled())
            return;
        RenderUtil.drawBoundingBox(mc.player.getEntityBoundingBox(), 1, 0xFF, 0xFF, 0xFF, 0xFF);
        BloodHack.EVENT_BUS.post(new RenderWorldEvent());
    }


    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event)
    {
        if (mc.player == null)
            return;

        BloodHack.EVENT_BUS.post(new dev.lors.bloodhack.event.events.TickEvent());
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.isCanceled())
            return;

        BloodHack.EVENT_BUS.post(event);
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(RenderPlayerEvent.Pre event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(RenderPlayerEvent.Post event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onChunkLoaded(ChunkEvent.Load event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onEventMouse(InputEvent.MouseInputEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onChunkUnLoaded(ChunkEvent.Unload event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onLivingEntityUseItemEventTick(LivingEntityUseItemEvent.Start entityUseItemEvent)
    {
        BloodHack.EVENT_BUS.post(entityUseItemEvent);
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent entityJoinWorldEvent)
    {
        BloodHack.EVENT_BUS.post(entityJoinWorldEvent);
    }

    @SubscribeEvent
    public void onPlayerPush(PlayerSPPushOutOfBlocksEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent entityEvent)
    {
        BloodHack.EVENT_BUS.post(entityEvent);
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent event)
    {
        BloodHack.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void OnWorldChange(WorldEvent p_Event)
    {
        BloodHack.EVENT_BUS.post(p_Event);
    }


}
