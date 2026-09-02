package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.util.variants.PigAccessor;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.pig.PigVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Pig.class)
public class PigMixin implements PigAccessor {

    @Shadow
    private void setVariant(Holder<PigVariant> variant) {
    }

    @Override
    public void umamium$setVariantAccessor(Holder<PigVariant> variant) {
        setVariant(variant);
    }
}
