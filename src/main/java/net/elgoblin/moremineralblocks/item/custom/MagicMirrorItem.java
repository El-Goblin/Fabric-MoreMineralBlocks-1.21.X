package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            MinecraftServer server = world.getServer();

            if (server != null) {

                ServerWorld overworld = server.getWorld(World.OVERWORLD);
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

                boolean hasSetSpawnPoint = serverPlayer.getSpawnPointPosition() != null;
                Vec3d coordinates = Vec3d.of(hasSetSpawnPoint ? serverPlayer.getSpawnPointPosition() : (overworld != null) ? overworld.getSpawnPos() : new Vec3i(0,64,0));
                RegistryKey<World> spawnDimension = serverPlayer.getSpawnPointDimension();
                ServerWorld targetWorld = server.getWorld(spawnDimension);

                TeleportTarget teleportTarget = new TeleportTarget(targetWorld,
                        coordinates,
                        new Vec3d(0, 0, 0),
                        user.getYaw(),
                        user.getPitch(),
                        TeleportTarget.NO_OP);
                user.teleportTo(teleportTarget);
            }
        }
        itemStack.decrement(1);
        return super.use(world, user, hand);
    }
}
