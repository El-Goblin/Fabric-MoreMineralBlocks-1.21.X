package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

//        ItemStack itemStack = user.getStackInHand(hand);
//
//        if (!world.isClient) {
//            MinecraftServer server = world.getServer();
//
//            if (server != null) {
//
//                ServerWorld overworld = server.getWorld(World.OVERWORLD);
//                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
//
//                boolean hasSetSpawnPoint = serverPlayer.getSpawnPointPosition() != null;
//                Vec3d coordinates = Vec3d.of(hasSetSpawnPoint ? serverPlayer.getSpawnPointPosition() : (overworld != null) ? overworld.getSpawnPos() : new Vec3i(0, 64, 0));
//
//                RegistryKey<World> spawnDimension = serverPlayer.getSpawnPointDimension();
//                ServerWorld targetWorld = server.getWorld(spawnDimension);
//
//                Pair<ServerWorld, Vec3d> target = new Pair<>(targetWorld, coordinates);
//
//                if (hasSetSpawnPoint && targetWorld != null) {
//                    target = getRespawnPosition(targetWorld, serverPlayer.getSpawnPointPosition(), serverPlayer.isSpawnForced());
//                }
//
//
//                TeleportTarget teleportTarget = new TeleportTarget(target.getLeft(),
//                        target.getRight(),
//                        new Vec3d(0, 0, 0),
//                        user.getYaw(),
//                        user.getPitch(),
//                        TeleportTarget.NO_OP);
//                user.teleportTo(teleportTarget);
//            }
//        }
//        itemStack.decrementUnlessCreative(1, user);
        return super.use(world, user, hand);
    }

    public Pair<ServerWorld, Vec3d> getRespawnPosition(ServerWorld world, BlockPos writtenSpawnPosition, boolean spawnForced) {
        BlockState blockState = world.getBlockState(writtenSpawnPosition);
        Block block = blockState.getBlock();

        if (block instanceof BedBlock && BedBlock.isBedWorking(world)) {
            return new Pair<>(world, new Vec3d(writtenSpawnPosition.getX(), writtenSpawnPosition.getY(), writtenSpawnPosition.getZ()));
        }
        else if (block instanceof RespawnAnchorBlock && (spawnForced || (Integer)blockState.get(RespawnAnchorBlock.CHARGES) > 0) && RespawnAnchorBlock.isNether(world)) {
            return new Pair<>(world, new Vec3d(writtenSpawnPosition.getX(), writtenSpawnPosition.getY(), writtenSpawnPosition.getZ()));
        }
        else {
            MinecraftServer server = world.getServer();
            ServerWorld overworld = server.getWorld(World.OVERWORLD);
            BlockPos worldSpawnPos = world.getSpawnPos();
            return new Pair<>(overworld, (overworld != null) ? new Vec3d(worldSpawnPos.getX(), worldSpawnPos.getY(), worldSpawnPos.getZ()) : new Vec3d(0,64,0));
        }
    }
}
