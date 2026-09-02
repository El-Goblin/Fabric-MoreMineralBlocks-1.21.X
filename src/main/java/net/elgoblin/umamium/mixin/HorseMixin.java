package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.util.variants.FrogAccessor;
import net.elgoblin.umamium.util.variants.HorseAccessor;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Frog.class)
public class HorseMixin implements HorseAccessor {

    @Shadow
    private void setTypeVariant(final int variantType) {
    }

    @Override
    public void umamium$setVariantAccessor(final int variantType) {
        setTypeVariant(variantType);
    }
}
