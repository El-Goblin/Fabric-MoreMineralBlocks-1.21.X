package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MaceItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.BiConsumer;


@Mixin(Item.class)
public class ItemMixin {

    @Unique
    private static final Map<Block, Block> crackedBlocks = Map.of(
            Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS,
            Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
            Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES,
            Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS,
            Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS,
            Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS
    );

    @Inject(
            method = "useOnBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (context.getStack().getItem() instanceof MaceItem) {
            Block blockToCrack = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            Block cracked = crackedBlocks.get(blockToCrack);

            if (cracked != null) {
                context.getWorld().setBlockState(context.getBlockPos(), cracked.getDefaultState());
                context.getStack().damage(1, context.getPlayer());
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}

