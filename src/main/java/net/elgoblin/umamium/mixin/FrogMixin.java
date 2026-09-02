package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.util.variants.FrogAccessor;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Frog.class)
public class FrogMixin implements FrogAccessor {

    @Shadow
    private void setVariant(Holder<FrogVariant> variant) {
    }

    @Override
    public void umamium$setVariantAccessor(Holder<FrogVariant> variant) {
        setVariant(variant);
    }
}
