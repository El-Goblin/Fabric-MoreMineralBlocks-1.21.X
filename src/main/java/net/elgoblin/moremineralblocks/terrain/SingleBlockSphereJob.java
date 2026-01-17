package net.elgoblin.moremineralblocks.terrain;

//import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SingleBlockSphereJob implements TerrainJob{
    private Queue<int[]> advanceX;
    private Queue<int[]> advanceZ;
    private Queue<int[]> advanceEquals;
    private final World world;
    private final PlayerEntity player;
    private final BlockPos center;
    private final int radius;
    private final int radiusSquared;
    private Block toPlace;
    int blocksAdvanced;
//    ProtectorManager protectorManager;

    public SingleBlockSphereJob(World world, PlayerEntity player, BlockPos center, int radius, Block toPlace) {
        this.world = world;
        this.center = center;
        this.radius = radius;
        this.radiusSquared = radius * radius;
        this.toPlace = toPlace;
        this.blocksAdvanced = 0;
        this.player = player;

//        if (world.getServer() != null) {
//            protectorManager = ProtectorManager.getProtectorManager(world.getServer());
//        }

        this.advanceX = new ArrayDeque<>();
        this.advanceZ = new ArrayDeque<>();
        this.advanceEquals = new ArrayDeque<>();
    }

    private int breakX4(BlockPos.Mutable positionToModify, int[] advanceDelta) {
        int blocksBroken = 0;
        // Romper X positivo y Z positivo
        positionToModify.set(center.getX() + advanceDelta[0], center.getY() + advanceDelta[1], center.getZ() + advanceDelta[2]);
        if (!world.getBlockState(positionToModify).isAir()) {
            world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
            blocksBroken++;
        }
        // Romper X negativo y Z positivo
        positionToModify.set(center.getX() - advanceDelta[0], center.getY() + advanceDelta[1], center.getZ() + advanceDelta[2]);
        if (!world.getBlockState(positionToModify).isAir()) {
            world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
            blocksBroken++;
        }
        // Romper X positivo y Z negativo
        positionToModify.set(center.getX() + advanceDelta[0], center.getY() + advanceDelta[1], center.getZ() - advanceDelta[2]);
        if (!world.getBlockState(positionToModify).isAir()) {
            world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
            blocksBroken++;
        }
        // Romper X negativo y Z negativo
        positionToModify.set(center.getX() - advanceDelta[0], center.getY() + advanceDelta[1], center.getZ() - advanceDelta[2]);
        if (!world.getBlockState(positionToModify).isAir()) {
            world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
            blocksBroken++;
        }
                return blocksBroken;
    }

    public boolean process(int maxBlockOperations) {
        int blocksProcessed = 0;

        BlockPos.Mutable positionToModify = new BlockPos.Mutable();

        while (blocksAdvanced <= radius || (blocksProcessed < maxBlockOperations && !(advanceX.isEmpty() && advanceZ.isEmpty()))) {
            blocksProcessed += processY(positionToModify);
            blocksProcessed += processX(positionToModify);
            for (int i = 0; i <advanceZ.size()/2+5 ; i++) {
                blocksProcessed += processZ(positionToModify);
            }
            blocksProcessed += processEquals(positionToModify);
        }
        if (advanceX.isEmpty() && advanceZ.isEmpty()) {
            player.sendMessage(Text.of(String.format("%d", radius)), true);
            return true;
        }
        return false;
    }

    private int processY(BlockPos.Mutable positionToModify) {
        if (blocksAdvanced <= radius) {
            positionToModify.set(center.getX(), center.getY() + blocksAdvanced, center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify.set(center.getX(), center.getY() + blocksAdvanced, center.getZ()), toPlace.getDefaultState(), 50);
            }
            positionToModify.set(center.getX(), center.getY() - blocksAdvanced, center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify.set(center.getX(), center.getY() - blocksAdvanced, center.getZ()), toPlace.getDefaultState(), 50);
            }
            // En todas las iteraciones subo en Y 1 vez. Si pude subir en Y, sumo en X
            advanceX.add(new int[]{1, blocksAdvanced, 0});
            blocksAdvanced++;
            return 2;
        }
        return 0;
    }

    private int processX(BlockPos.Mutable positionToModify) {
        if (!advanceX.isEmpty()) {
            int blocksBroken = 0;
            int[] advanceDelta = advanceX.poll();

            // Z siempre es 0 aca
            if ((advanceDelta[0] + 1) * (advanceDelta[0] + 1) + advanceDelta[1] * advanceDelta[1] <= radiusSquared) {
                advanceX.add(new int[] {advanceDelta[0]+1, advanceDelta[1], 0});
            }
            // Z siempre es 1 aca
            if (advanceDelta[0] * advanceDelta[0] + advanceDelta[1] * advanceDelta[1] + 1 <= radiusSquared) {
                if (advanceDelta[0] == 1) {
                    advanceEquals.add(new int[] {1, advanceDelta[1], 1});
                }
                else {
                    advanceZ.add(new int[] {advanceDelta[0], advanceDelta[1], 1});
                }
            }

            // Romper X positivo
            positionToModify.set(center.getX() + advanceDelta[0], center.getY() + advanceDelta[1], center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper X negativo
            positionToModify.set(center.getX() - advanceDelta[0], center.getY() + advanceDelta[1], center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper Z positivo
            positionToModify.set(center.getX(), center.getY() + advanceDelta[1], center.getZ() + advanceDelta[0]);
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper Z negativo
            positionToModify.set(center.getX(), center.getY() + advanceDelta[1], center.getZ() - advanceDelta[0]);
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }

            advanceDelta[1] = -advanceDelta[1];

            // Romper X positivo
            positionToModify.set(center.getX() + advanceDelta[0], center.getY() + advanceDelta[1], center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper X negativo
            positionToModify.set(center.getX() - advanceDelta[0], center.getY() + advanceDelta[1], center.getZ());
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper Z positivo
            positionToModify.set(center.getX(), center.getY() + advanceDelta[1], center.getZ() + advanceDelta[0]);
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }
            // Romper Z negativo
            positionToModify.set(center.getX(), center.getY() + advanceDelta[1], center.getZ() - advanceDelta[0]);
            if (!world.getBlockState(positionToModify).isAir()) {
                world.setBlockState(positionToModify, toPlace.getDefaultState(), 50);
                blocksBroken++;
            }

            return blocksBroken;
        }
        return 0;
    }

    private int processZ(BlockPos.Mutable positionToModify) {
        if (!advanceZ.isEmpty()) {
            // advanceDelta = {DeltaX, DeltaY, DeltaZ}
            int[] advanceDelta = advanceZ.poll();
            int blocksBroken = 0;

            // Agrego bloques en caso de poder seguir avanzando
            if (advanceDelta[0] * advanceDelta[0] + advanceDelta[1] * advanceDelta[1] + (advanceDelta[2] + 1) * (advanceDelta[2] + 1) <= radiusSquared) {
                int[] newDelta = new int[] {advanceDelta[0], advanceDelta[1], advanceDelta[2] + 1};
                if (advanceDelta[0] == advanceDelta[2] + 1) {
                    advanceEquals.add(newDelta);
                }
                else {
                    advanceZ.add(newDelta);
                }
            }

            blocksBroken += breakX4(positionToModify, advanceDelta);
            advanceDelta[1] = -advanceDelta[1];
            blocksBroken += breakX4(positionToModify, advanceDelta);
            advanceDelta = new int[] {advanceDelta[2], advanceDelta[1], advanceDelta[0]};
            blocksBroken += breakX4(positionToModify, advanceDelta);
            advanceDelta[1] = -advanceDelta[1];
            blocksBroken += breakX4(positionToModify, advanceDelta);

            return blocksBroken;
        }
        return 0;
    }

    private int processEquals(BlockPos.Mutable positionToModify) {
        if (!advanceEquals.isEmpty()) {
            // advanceDelta = {DeltaX, DeltaY, DeltaZ}
            int[] advanceDelta = advanceEquals.poll();
            int blocksBroken = 0;

            // No se agregan bloques nuevos porque van a ser procesados por la segunda iteracion del for de los Z

            blocksBroken += breakX4(positionToModify, advanceDelta);
            advanceDelta[1] = -advanceDelta[1];
            blocksBroken += breakX4(positionToModify, advanceDelta);

            return  blocksBroken;
        }
        return 0;
    }
}
