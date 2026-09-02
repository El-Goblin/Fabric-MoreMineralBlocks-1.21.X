package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.item.ModItems;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Zombie.class)
public class ZombieMixin {

    @ModifyArg(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;)V"
            )
    )
    private ItemLike redirectItemStack(ItemLike item) {
        if (item.asItem() == Items.IRON_SWORD && ((Zombie)(Object)this).getRandom().nextBoolean()) {
            return ModItems.FLAMEBERGE_LONGSWORD;
        }
        return item;
    }
}