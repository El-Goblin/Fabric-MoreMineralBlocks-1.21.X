package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin extends HostileEntity {

    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyArg(method = "initEquipment",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V"),
    index = 1)
    private ItemStack replaceSwordForFlameberge(ItemStack originalEquipment) {
        if (originalEquipment.getItem() == Items.IRON_SWORD && random.nextInt(2) == 0) {
            return new ItemStack(ModItems.FLAMEBERGE_LONGSWORD);
        }
        else {
            return originalEquipment;
        }
    }
}
