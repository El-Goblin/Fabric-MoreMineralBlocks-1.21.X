package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class MossItem extends Item {

    private static final Map<Block, Block> MOSSY_BLOCKS = Map.of(
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

    public MossItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();


        BlockPos clickedPos = context.getClickedPos();
        Block clickedBlock = level.getBlockState(clickedPos).getBlock();
        ItemStack mossStack = context.getItemInHand();
        Player player = context.getPlayer();

        if (MOSSY_BLOCKS.containsKey(clickedBlock) && player != null) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(clickedPos, MOSSY_BLOCKS.get(clickedBlock).defaultBlockState());
                if (!player.getAbilities().instabuild) {
                    mossStack.consume(1, player);
                }

                level.playSound(null,
                        clickedPos,
                        SoundEvents.BONE_MEAL_USE,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
