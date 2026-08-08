package net.elgoblin.moremineralblocks.mixin;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.TintedGlassBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TintedGlassBlock.class)
public class TintedGlassBlockMixin implements BeaconBeamBlock {
    @Override
    public DyeColor getColor() {
        return DyeColor.BLACK;
    }
}
