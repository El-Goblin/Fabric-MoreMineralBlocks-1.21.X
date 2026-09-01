package net.elgoblin.umamium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(MaceItem.class)
public class MaceItemMixin extends Item {

    @Unique
    private static final Map<Block, Block> CRACKED_BLOCKS = Map.of(
            Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS,
            Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
            Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES,
            Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS,
            Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS,
            Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS
    );

    public MaceItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Block clickedBlock = level.getBlockState(clickedPos).getBlock();

        if (CRACKED_BLOCKS.containsKey(clickedBlock)) {
            Player player = context.getPlayer();

            if (!level.isClientSide()) {
                level.setBlockAndUpdate(clickedPos, CRACKED_BLOCKS.get(clickedBlock).defaultBlockState());

                level.playSound(null,
                        clickedPos,
                        SoundEvents.POINTED_DRIPSTONE_LAND,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F);

                if (player != null && !player.isCreative()) {
                    ItemStack stackInHand = context.getItemInHand();
                    stackInHand.hurtAndBreak(1, player, context.getHand());
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
