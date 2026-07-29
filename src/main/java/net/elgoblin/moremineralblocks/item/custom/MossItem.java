package net.elgoblin.moremineralblocks.item.custom;

import com.google.common.collect.BiMap;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class MossItem extends Item {
    public MossItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if (!world.isClient()) {
            BlockPos position = context.getBlockPos();
            BlockState state = world.getBlockState(position);
            Block block = state.getBlock();
            Block mossy = mossyBlocks.get(block);

            if (mossy != null) {
                world.setBlockState(position, mossy.getStateWithProperties(state));

                world.playSound(
                        null,
                        position,
                        SoundEvents.ITEM_BONE_MEAL_USE,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F
                );

                world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, position);

                context.getStack().decrementUnlessCreative(1, context.getPlayer());

                return ActionResult.SUCCESS_SERVER;
            }
        }
        return ActionResult.PASS;
    }

    private static final Map<Block, Block> mossyBlocks = Map.of(
            Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE,
            Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB,
            Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS,
            Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL,
            Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS,
            Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB,
            Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS,
            Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL,
            Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_MOSSY_STONE_BRICKS
    );
}
