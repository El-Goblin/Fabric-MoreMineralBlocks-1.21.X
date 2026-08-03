package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.elgoblin.moremineralblocks.tags.ModTags;
import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow
    private @Nullable Item item;

    @Inject(
            at = @At("RETURN"),
            method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemInstance;)Ljava/util/List;",
            cancellable = true
    )
    private static void carryDropsToLinkedChest(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            BlockEntity blockEntity,
            Entity breaker,
            ItemInstance tool,
            CallbackInfoReturnable<List<ItemStack>> cir) {

        if (!(tool instanceof ItemStack itemStack) || !tool.is(ModTags.Items.LEGENDARY_TOOLS) || !(ModEnchantments.getLevel((ItemStack) tool, ModEnchantments.LINKER) > 0)) {
            return;
        }

        List<ItemStack> remainderDrops = LegendaryItemUtils.carryDropsToLinkedContainerAndGetRemainder(level, itemStack, cir.getReturnValue());
        cir.setReturnValue(remainderDrops);
    }
}