package net.elgoblin.moremineralblocks.mixin;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.entity.BeaconBeamOwner;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin{


    @Shadow
    private List<BeaconBeamOwner.Section> checkingBeamSections;
    @Shadow
    private int lastCheckY;

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/DyeColor;getTextureDiffuseColor()I"
            )
    )
    private static int handleTintedGlassColor(DyeColor instance, Operation<Integer> original, Level level, BlockPos pos, BlockState selfState, BeaconBlockEntity entity) {
        BeaconBlockEntityMixin accessor = (BeaconBlockEntityMixin) (Object) entity;



        BlockPos checkPos;
        if (accessor == null) { return original.call(instance); }

        if (accessor.lastCheckY < pos.getY()) {
            checkPos = pos;
            accessor.checkingBeamSections = Lists.newArrayList();
            accessor.lastCheckY = checkPos.getY() - 1;
        } else {
            checkPos = new BlockPos(pos.getX(), accessor.lastCheckY + 1, pos.getZ());
        }
        BlockState state = level.getBlockState(checkPos);

        if (state.getBlock() instanceof TintedGlassBlock) {
            return 0x00000000;
        }
        return original.call(instance);
    }
}