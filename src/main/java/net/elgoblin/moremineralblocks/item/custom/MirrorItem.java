package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class MirrorItem extends Item {

    public MirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            MinecraftServer server = world.getServer();

            if (server != null) {

                Identifier world_ID = itemStack.get(ModDataComponentTypes.SERVERWORLD);
                RegistryKey<World> worldKey = RegistryKey.of(RegistryKeys.WORLD, world_ID);

                ServerWorld targetWorld = world.getServer().getWorld(worldKey);

                Vec3d coordinates = itemStack.get(ModDataComponentTypes.COORDINATES);

                if (coordinates != null) {
                    TeleportTarget teleportTarget = new TeleportTarget(targetWorld,
                            coordinates,
                            new Vec3d(0, 0, 0),
                            user.getYaw(),
                            user.getPitch(),
                            TeleportTarget.NO_OP);
                    user.teleportTo(teleportTarget);
                }
            }
        }
        itemStack.decrementUnlessCreative(1, user);
        return super.use(world, user, hand);
    }
}
