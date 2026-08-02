package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public class ReflectiveMirrorItem extends Item {

    public ReflectiveMirrorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {

        ItemStack itemStack = user.getItemInHand(hand);

        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();

            if (server != null) {

                Identifier dimension = itemStack.get(ModDataComponentTypes.SERVERWORLD);
                if (dimension == null) {
                    return InteractionResult.FAIL;
                }
                ResourceKey<Level> worldKey = ResourceKey.create(Registries.DIMENSION, dimension);

                ServerLevel targetWorld = level.getServer().getLevel(worldKey);
                if (targetWorld == null) {
                    return InteractionResult.FAIL;
                }

                Vec3 coordinates = itemStack.get(ModDataComponentTypes.COORDINATES);

                if (coordinates != null) {
                    TeleportTransition teleportTarget = new TeleportTransition(targetWorld,
                            coordinates,
                            new Vec3(0, 0, 0),
                            user.getYRot(),
                            user.getXRot(),
                            TeleportTransition.DO_NOTHING);
                    user.teleport(teleportTarget);
                }
            }
            itemStack.consume(1, user);
            return InteractionResult.SUCCESS;
        }
        return super.use(level, user, hand);
    }
}
