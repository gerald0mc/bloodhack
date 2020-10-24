package dev.lors.bloodhack.module.BloodModules.movement;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.event.EventCancellable;
import dev.lors.bloodhack.event.events.EventEntity;
import dev.lors.bloodhack.event.events.EventPacket;
import dev.lors.bloodhack.event.events.PacketEvent;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;


public class Velocity extends Module {
    public Velocity() {
        super(" Velocity", Category.MOVEMENT);
    }
}

    /*@EventHandler
    private Listener<EventPacket.ReceivePacket> damage = new Listener<>(event -> {
        if (event.get_era() == EventCancellable.Era.EVENT_PRE) {
            if (event.get_packet() instanceof SPacketEntityVelocity) {
                SPacketEntityVelocity knockback = (SPacketEntityVelocity) event.get_packet();

                if (knockback.getEntityID() == mc.player.getEntityId()) {
                    event.cancel();

                    knockback.motionX *= 0.0f;
                    knockback.motionY *= 0.0f;
                    knockback.motionZ *= 0.0f;
                }
            } else if (event.get_packet() instanceof SPacketExplosion) {
                event.cancel();

                SPacketExplosion knockback = (SPacketExplosion) event.get_packet();

                knockback.motionX *= 0.0f;
                knockback.motionY *= 0.0f;
                knockback.motionZ *= 0.0f;
            }
        }
    });

    @EventHandler
    private Listener<EventEntity.WurstplusEventColision> explosion = new Listener<>(event -> {
        if (event.get_entity() == mc.player) {
            event.cancel();

        }
    });
}
*/
