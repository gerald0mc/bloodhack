package dev.lors.bloodhack.module.BloodModules.render;

import dev.lors.bloodhack.event.events.RenderEvent;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.BloodHackTessellator;
import dev.lors.bloodhack.utils.CrystalUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HoleESP extends Module {
    private final BlockPos[] surroundOffset = {
            new BlockPos(0, -1, 0), // down
            new BlockPos(0, 0, -1), // north
            new BlockPos(1, 0, 0), // east
            new BlockPos(0, 0, 1), // south
            new BlockPos(-1, 0, 0) // west
    };
    Value<Integer> rangeS = new Value<Integer>("Range", 7, 0, 9);
    Value<Integer> r = new Value<Integer>("Red", 255, 0, 255);
    Value<Integer> g = new Value<Integer>("Green", 255, 0, 255);
    Value<Integer> b = new Value<Integer>("Blue", 255, 0, 255);
    Value<Integer> a = new Value<Integer>("Alpha", 255, 0, 255);
    private ConcurrentHashMap<BlockPos, Boolean> safeHoles;

    public HoleESP() {
        super("HoleESP-[NOT WORKING]", Category.RENDER);
    }

    @Override
    public void onUpdate() {
        if (mc.player == null && mc.world == null) return;
        try {
            if (safeHoles == null) {
                safeHoles = new ConcurrentHashMap<>();
            } else {
                safeHoles.clear();
            }

            int range = (int) Math.ceil(rangeS.value);

            List<BlockPos> blockPosList = CrystalUtil.getSphere(getPlayerPos(), range, range, false, true, 0);

            for (BlockPos pos : blockPosList) {

                // block gotta be air
                if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                    continue;
                }

                // block 1 above gotta be air
                if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }

                // block 2 above gotta be air
                if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }

                boolean isSafe = true;
                boolean isBedrock = true;

                for (BlockPos offset : surroundOffset) {
                    Block block = mc.world.getBlockState(pos.add(offset)).getBlock();
                    if (block != Blocks.BEDROCK) {
                        isBedrock = false;
                    }
                    if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                        isSafe = false;
                        break;
                    }
                }

                if (isSafe) {
                    safeHoles.put(pos, isBedrock);
                }
            }
        } catch (Exception bruh) {
        }
    }

    //@Override
    public void render(final RenderEvent event) {
        if (mc.player == null || safeHoles == null) {
            return;
        }
        try {
            if (safeHoles.isEmpty()) {
                return;
            }

            BloodHackTessellator.prepare("lines");

            safeHoles.forEach((blockPos, isBedrock) -> {
                BloodHackTessellator.draw_cube_line(blockPos, r.value, g.value, b.value, a.value, "all");
            });
        } catch (Exception ignored) {

        }
    }

    public BlockPos getPlayerPos() {
        return mc.player.getPosition();
    }
}