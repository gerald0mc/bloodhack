package dev.lors.bloodhack.module.BloodModules.combat;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.event.events.EventEntityRemoved;
import dev.lors.bloodhack.event.events.PacketEvent;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;

import dev.lors.bloodhack.util.Pair;
import dev.lors.bloodhack.util.Timer;
import dev.lors.bloodhack.utils.BlockUtils;
import dev.lors.bloodhack.utils.BloodHackTessellator;
import dev.lors.bloodhack.utils.CrystalUtil;
import dev.lors.bloodhack.utils.EntityUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;

public class BetterAC extends Module {
    public BetterAC() {
        super("BetterAutoCrystal", Category.COMBAT);
    }

    Value<Boolean> placeCA = new Value("Place", true);
    Value<Boolean> breakCA = new Value("Break", true);
    Value<Boolean> autoSwitch = new Value("Auto-Switch", true);
    Value<Boolean> antiWeakness = new Value("Anti-Weakness");
    Value<Boolean> antiSui = new Value("Anti-Suicide", true);
    Value<Boolean> antiStuck = new Value("Anti-Stuck", true);
    Value<Boolean> newServ = new Value("1.13+ Mode", false);
    Value<Boolean> faceplace = new Value("FacePlace Mode", false);

    Value<Integer> placeRange = new Value("Place Range", 6, 1, 7);
    Value<Integer> placeDelay = new Value("Place Delay", 0, 0, 7);
    Value<Integer> breakRange = new Value("Break Range", 8, 1, 7);
    Value<Integer> breakDelay = new Value("Break Delay", 1, 0, 7);
    Value<Integer> wallRange = new Value("Wall Range", 6, 1, 6);
    Value<Integer> minDamagePlace = new Value("Min Damage Place", 8, 0, 7);
    Value<Integer> minDamageBreak = new Value("Min Damage Break", 6, 0, 7);
    Value<Integer> selfDamage = new Value("Max Self Damage", 6, 0, 20);
    Value<Integer> breakAttempts = new Value("Break Attempts", 2, 1, 6);
    Value<Integer> faceplaceHealth = new Value("Faceplace Min Health", 8, 0, 36);
    Value<Integer> r = new Value("Red", 255, 0, 255);
    Value<Integer> g = new Value("Green", 255, 0, 255);
    Value<Integer> b = new Value("Blue", 255, 0, 255);

    private final ConcurrentHashMap<EntityEnderCrystal, Integer> attackedCrystals = new ConcurrentHashMap<>();

    private EntityPlayer autoEzTarget = null;


    private BlockPos renderBlockInit;

    private double renderDamageValue;

    private float yaw;
    private float pitch;

    private boolean alreadyAttacking = false;
    private boolean placeTimeoutFlag = false;
    private boolean isRotating;
    private boolean didAnything;
    private boolean outline;
    private boolean solid;

    private int chainStep = 0;
    private int currentChainIndex = 0;
    private int placeTimeout;
    private int breakTimeout;
    private int breakDelayCounter;
    private int placeDelayCounter;

    private final Timer timer = new Timer();
    private final Timer removeVisualTimer = new Timer();

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        placeTimeout = placeDelay.value;
        breakTimeout = breakDelay.value;
        placeTimeoutFlag = false;
        isRotating = false;
        autoEzTarget = null;
        chainStep = 0;
        currentChainIndex = 0;
        timer.reset();
        removeVisualTimer.reset();
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        renderBlockInit = null;
        autoEzTarget = null;
    }

    @Override
    public void onUpdate() {
        do_ca();
    }

    @EventHandler
    private Listener<EventEntityRemoved> on_entity_removed = new Listener<>(event -> {
        if (event.get_entity() instanceof EntityEnderCrystal) {
            attackedCrystals.remove(event.get_entity());
        }
    });

    @EventHandler
    private final Listener<PacketEvent.Receive> receive_listener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();

            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (Entity e : mc.world.loadedEntityList) {
                    if (e instanceof EntityEnderCrystal) {
                        if (e.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0f) {
                            e.setDead();
                        }
                    }
                }
            }
        }

    });

    public void do_ca() {
        didAnything = false;

        if (mc.player == null || mc.world == null) return;

        if (removeVisualTimer.passed(1000)) {
            removeVisualTimer.reset();
            attackedCrystals.clear();
        }

        if (checkPause()) {
            return;
        }

        if (placeCA.value && placeDelayCounter > placeTimeout) {
            place_crystal();
        }

        if (breakCA.value && breakDelayCounter > breakTimeout) {
            break_crystal();
        }

        if (!didAnything) {

            autoEzTarget = null;
            isRotating = false;
        }


        if (timer.passed(1000)) {
            timer.reset();
            chainStep = 0;
        }

        breakDelayCounter++;
        placeDelayCounter++;
    }

    public EntityEnderCrystal get_best_crystal() {

        double best_damage = 0;

        double minimum_damage;
        double maximum_damage_self = this.selfDamage.value;

        double best_distance = 0;

        EntityEnderCrystal best_crystal = null;
        try {
            for (Entity c : mc.world.loadedEntityList) {

                if (!(c instanceof EntityEnderCrystal)) continue;

                EntityEnderCrystal crystal = (EntityEnderCrystal) c;
                if (mc.player.getDistance(crystal) > (!mc.player.canEntityBeSeen(crystal) ? this.wallRange.value : this.breakRange.value)) {
                    continue;
                }

                if (crystal.isDead) continue;

                if (attackedCrystals.containsKey(crystal) && attackedCrystals.get(crystal) > 5 && antiStuck.value) continue;

                for (Entity player : mc.world.playerEntities) {

                    if (player == mc.player || !(player instanceof EntityPlayer)) continue;

                    //if (Friends.isFriend(player.getName())) continue;

                    if (player.getDistance(mc.player) >= 11) continue;

                    final EntityPlayer target = (EntityPlayer) player;

                    if (target.isDead || target.getHealth() <= 0) continue;

                    boolean no_place = false;
                    if ((target.getHealth() < faceplaceHealth.value && faceplace.value && !no_place) || !no_place) {
                        minimum_damage = 2;
                    } else {
                        minimum_damage = this.minDamageBreak.value;
                    }

                    final double target_damage = CrystalUtil.calculateDamage(crystal, target);

                    if (target_damage < minimum_damage) continue;

                    final double self_damage = CrystalUtil.calculateDamage(crystal, mc.player);

                    if (self_damage > maximum_damage_self || (antiSui.value && (mc.player.getHealth() + mc.player.getAbsorptionAmount()) - self_damage <= 0.5)) continue;

                    if (target_damage > best_damage) {
                        autoEzTarget = target;
                        best_damage = target_damage;
                        best_crystal = crystal;
                    }

                }

                if (mc.player.getDistanceSq(crystal) > best_distance) {
                    best_distance = mc.player.getDistanceSq(crystal);
                    best_crystal = crystal;
                }

            }
        }catch(ConcurrentModificationException cme) {
        }
        return best_crystal;

    }


    public BlockPos get_best_block() {

        if (get_best_crystal() != null) {
            placeTimeoutFlag = true;
            return null;
        }

        if (placeTimeoutFlag) {
            placeTimeoutFlag = false;
            return null;
        }

        List<Pair<Double, BlockPos>> damage_blocks = new ArrayList<>();
        double best_damage = 0;
        double minimum_damage;
        double maximum_damage_self = this.selfDamage.value;

        BlockPos best_block = null;

        List<BlockPos> blocks = CrystalUtil.possiblePlacePositions((float) placeRange.value, newServ.value, true);

        for (Entity player : mc.world.playerEntities) {

            //if (Friends.isFriend(player.getName())) continue;

            for (BlockPos block : blocks) {

                if (player == mc.player || !(player instanceof EntityPlayer)) continue;

                if (player.getDistance(mc.player) >= 11) continue;

                if (!BlockUtils.rayTracePlaceCheck(block, false)) {
                    continue;
                }

                if (!BlockUtils.canSeeBlock(block) && mc.player.getDistance(block.getX(), block.getY(), block.getZ()) > wallRange.value) {
                    continue;
                }

                final EntityPlayer target = (EntityPlayer) player;

                if (target.isDead || target.getHealth() <= 0) continue;

                boolean no_place = faceplace.value && mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                if ((target.getHealth() < faceplaceHealth.value && faceplace.value && !no_place) || !no_place) {
                    minimum_damage = 2;
                } else {
                    minimum_damage = this.minDamagePlace.value;
                }

                final double target_damage = CrystalUtil.calculateDamage((double) block.getX() + 0.5, (double) block.getY() + 1, (double) block.getZ() + 0.5, target);

                if (target_damage < minimum_damage) continue;

                final double self_damage = CrystalUtil.calculateDamage((double) block.getX() + 0.5, (double) block.getY() + 1, (double) block.getZ() + 0.5, mc.player);

                if (self_damage > maximum_damage_self || (antiSui.value && (mc.player.getHealth() + mc.player.getAbsorptionAmount()) - self_damage <= 0.5)) continue;

                /** if (attempt_chain.get_value(true) && chain_step > 0) {
                 damage_blocks.add(new BadlionPair<>(best_damage, best_block));
                 autoez_target = target;
                 } else**/ if (target_damage > best_damage) {
                    best_damage = target_damage;
                    best_block = block;
                    autoEzTarget = target;
                }

            }

        }

        blocks.clear();

        if (chainStep == 1) {
            currentChainIndex = 3;
        } else if (chainStep > 1) {
            currentChainIndex--;
        }

        renderDamageValue = best_damage;
        renderBlockInit = best_block;

        damage_blocks = sortBestBlocks(damage_blocks);

        //if (!attempt_chain.get_value(true)) {
        return best_block;
//        } else {
//            if (damage_blocks.size() == 0) {
//                return null;
//            }
//            if (damage_blocks.size() < current_chain_index) {
//                return damage_blocks.get(0).getValue();
//            }
//            return damage_blocks.get(current_chain_index).getValue();
//        }

    }

    public List<Pair<Double, BlockPos>> sortBestBlocks(List<Pair<Double, BlockPos>> list) {
        List<Pair<Double, BlockPos>> new_list = new ArrayList<>();
        double damage_cap = 1000;
        for (int i = 0; i < list.size(); i++) {
            double biggest_dam = 0;
            Pair<Double, BlockPos> best_pair = null;
            for (Pair<Double, BlockPos> pair : list) {
                if (pair.getKey() > biggest_dam && pair.getKey() < damage_cap) {
                    best_pair = pair;
                }
            }
            if (best_pair == null) continue;
            damage_cap = best_pair.getKey();
            new_list.add(best_pair);
        }
        return new_list;
    }

    public void place_crystal() {

        BlockPos target_block = get_best_block();

        if (target_block == null) {
            return;
        }

        placeDelayCounter = 0;

        alreadyAttacking = false;

        boolean offhand_check = false;
        if (mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            if (mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && autoSwitch.value) {
                if (find_crystals_hotbar() == -1) return;
                mc.player.inventory.currentItem = find_crystals_hotbar();
                return;
            }
        } else {
            offhand_check = true;
        }


        chainStep++;
        didAnything = true;
        timer.reset();
        BlockUtils.placeCrystalOnBlock(target_block, offhand_check ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);

    }

    private int find_crystals_hotbar() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }

    public void break_crystal() {

        EntityEnderCrystal crystal = get_best_crystal();
        if (crystal == null) {
            return;
        }

        if (antiWeakness.value && mc.player.isPotionActive(MobEffects.WEAKNESS)) {

            boolean should_weakness = true;

            if (mc.player.isPotionActive(MobEffects.STRENGTH)) {

                if (Objects.requireNonNull(mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                    should_weakness = false;
                }

            }

            if (should_weakness) {

                if (!alreadyAttacking) {
                    alreadyAttacking = true;
                }

                int new_slot = -1;

                for (int i = 0; i < 9; i++) {

                    ItemStack stack = mc.player.inventory.getStackInSlot(i);

                    if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemTool) {
                        new_slot = i;
                        mc.playerController.updateController();
                        break;
                    }

                }

                if (new_slot != -1) {
                    mc.player.inventory.currentItem = new_slot;
                }

            }

        }

        didAnything = true;

        for (int i = 0; i < breakAttempts.value; i++) {
            EntityUtil.attackEntity(crystal, false);
        }
        addAttackedCrystal(crystal);

        breakDelayCounter = 0;

    }

    private void addAttackedCrystal(EntityEnderCrystal crystal) {

        if (attackedCrystals.containsKey(crystal)) {
            int value = attackedCrystals.get(crystal);
            attackedCrystals.put(crystal, value + 1);
        } else {
            attackedCrystals.put(crystal, 1);
        }


    }

    public boolean checkPause() {

        if (find_crystals_hotbar() == -1 && mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            return true;
        }

        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            renderBlockInit = null;
            return true;
        }

        return false;
    }

    //@Override
    //public void render(RenderEvent event) {
    //if(render.value) {
    //if (renderBlockInit == null) return;

    //outline = false;
    //solid = true;

    //render_block(renderBlockInit);
    //}
    //}

    //temporarily commented out since it was apparently causing extreme fps drops and crashes
    //hopefully we are good now

    public void render_block(BlockPos pos) {
        BlockPos render_block = (pos);

        float h = 1.0F;

        if (solid) {
            BloodHackTessellator.prepare("quads");
            BloodHackTessellator.draw_cube(BloodHackTessellator.get_buffer_build(),
                    render_block.getX(), render_block.getY(), render_block.getZ(),
                    1, h, 1,
                    r.value, g.value, b.value, 255,
                    "all"
            );
            BloodHackTessellator.release();
        }

        if (outline) {
            BloodHackTessellator.prepare("lines");
            BloodHackTessellator.draw_cube_line(BloodHackTessellator.get_buffer_build(),
                    render_block.getX(), render_block.getY(), render_block.getZ(),
                    1, h, 1,
                    r.value, g.value, b.value, 255,
                    "all"
            );
            BloodHackTessellator.release();
        }
    }



}