package dev.lors.bloodhack.module.BloodModules.movement;

import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;


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
