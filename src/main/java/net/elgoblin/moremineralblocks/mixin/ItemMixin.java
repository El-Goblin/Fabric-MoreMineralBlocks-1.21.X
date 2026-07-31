package net.elgoblin.moremineralblocks.mixin;

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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Item.class)
public class ItemMixin {

    @Unique
    private static final Map<Block, Block> CRACKED_BLOCKS = Map.of(
            Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS,
            Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
            Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES,
            Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS,
            Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS,
            Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS
    );

    @Inject(at = @At("HEAD"), method = "useOn", cancellable = true)
    private void init(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stackInHand = context.getItemInHand();
        if (stackInHand.getItem() instanceof MaceItem) {
            Level level = context.getLevel();

            BlockPos clickedPos = context.getClickedPos();
            Block clickedBlock = level.getBlockState(clickedPos).getBlock();
            Player player = context.getPlayer();

            if (CRACKED_BLOCKS.containsKey(clickedBlock) && player != null) {
                if (!level.isClientSide()) {
                    level.setBlockAndUpdate(clickedPos, CRACKED_BLOCKS.get(clickedBlock).defaultBlockState());
                    if (!player.getAbilities().instabuild) {
                        stackInHand.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stackInHand));
                    }

                    level.playSound(null,
                            clickedPos,
                            SoundEvents.POINTED_DRIPSTONE_LAND,
                            SoundSource.BLOCKS,
                            1.0F,
                            1.0F);
                }

                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}