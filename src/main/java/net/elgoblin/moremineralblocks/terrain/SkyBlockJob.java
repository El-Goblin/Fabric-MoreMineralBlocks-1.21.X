package net.elgoblin.moremineralblocks.terrain;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.mixin.ChunkMixin;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.chunk.*;

import java.util.*;

public class SkyBlockJob implements TerrainJob{
    private final List<int[]> chunkPositions;
    private final ServerWorld world;
    private final int tpX;
    private final int tpZ;
    private final int radius;
    private int timer;

    private final List<WorldChunk> modifiedChunks; // Track wiped chunks for batched updates

    public SkyBlockJob(ServerWorld world, int tpX, int tpZ, int radius) {
        this.world = world;
        this.tpX = tpX;
        this.tpZ = tpZ;
        this.radius = radius;
        this.chunkPositions = new ArrayList<>();
        this.modifiedChunks = new ArrayList<>();

        chunkPositions.add(new int[] {0,0});
        getChunksToProcess();
        timer = chunkPositions.size();

        chunkPositions.sort(Comparator.comparingInt(p -> p[0] * p[0] + p[1] * p[1]));
    }

    public boolean process(int maxOperations) {
        maxOperations = 1;
        for (int i = 0 ; i < maxOperations ; i++) {
            System.out.println("timer = " + timer);
            timer--;

            if (timer == -2) {

                MinecraftServer server = world.getServer();
                if (server == null) {
                    return true;
                }

                StructureTemplateManager structureManager = world.getServer().getStructureTemplateManager();
                StructureTemplate template = structureManager.getTemplateOrBlank(Identifier.of(MoreMineralBlocks.MOD_ID, "skyblock_start"));

                StructurePlacementData settings = new StructurePlacementData()
                        .setRotation(BlockRotation.NONE)
                        .setMirror(BlockMirror.NONE)
                        .setIgnoreEntities(false);

                WorldChunk chunk = world.getChunk(tpX >> 4, tpZ >> 4);

                template.place(world, chunk.getPos().getBlockPos(4, 63, 4),chunk.getPos().getBlockPos(4,63,4), settings, world.getRandom(), 50);

                return false;
            }
            if (timer < 0 && timer % (2*chunkPositions.size()) == 0) {
                applyBatchedLightingAndSync();
            }
            if (timer < -chunkPositions.size() * 15) {
                MinecraftServer server = world.getServer();
                if (server == null) {
                    return true;
                }

                List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
                StatusEffectInstance slowFall = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 600, 0);

                TeleportTarget teleportTarget = new TeleportTarget(world,
                        new Vec3d(tpX,
                                150,
                                tpZ),
                        new Vec3d(0, 0, 0),
                        0,
                        0,
                        TeleportTarget.NO_OP);

                for (ServerPlayerEntity player : players) {
                    if (player == null) {
                        continue;
                    }

                    player.addStatusEffect(slowFall);
                    player.teleportTo(teleportTarget);

                    BlockPos newSpawnPoint = new BlockPos((int) player.getX() - (int) player.getX() % 16 + 11, 66, (int) player.getZ() - (int) player.getZ() % 16 + 11);
                    player.setSpawnPoint(new ServerPlayerEntity.Respawn(WorldProperties.SpawnPoint.create(world.getRegistryKey(), newSpawnPoint, 0, 0), false), false);
                }

                return true;
            }
            if (timer >= 0) {
                int[] position = chunkPositions.get(timer);
                WorldChunk chunk = world.getChunk(position[0] + (tpX >> 4), position[1] + (tpZ >> 4));
                wipeChunk(chunk);
            }
        }
        return false;
    }

    private void getChunksToProcess() {
        for (int z = 0 ; z <= radius+1 ; z++) {
            for (int x = 0 ; x <= radius+1 ; x++) {

                if (x == 0 && z == 0) {
                    continue;
                }

                if (x == 0) {
                    if (z == radius+1) {
                        modifiedChunks.add(world.getChunk(x + (tpX >> 4), z + (tpZ >> 4)));
                        modifiedChunks.add(world.getChunk(x + (tpX >> 4), -z + (tpZ >> 4)));
                        continue;
                    }
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {x, -z});
                    continue;
                }
                if (z == 0) {
                    if (x == radius+1) {
                        modifiedChunks.add(world.getChunk(x + (tpX >> 4), z + (tpZ >> 4)));
                        modifiedChunks.add(world.getChunk(-x + (tpX >> 4), z + (tpZ >> 4)));
                        continue;
                    }
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {-x, z});
                    continue;
                }
                else {
                    if (z == radius+1 || x == radius+1) {
                        modifiedChunks.add(world.getChunk(x + (tpX >> 4), z + (tpZ >> 4)));
                        modifiedChunks.add(world.getChunk(-x + (tpX >> 4), z + (tpZ >> 4)));
                        modifiedChunks.add(world.getChunk(x + (tpX >> 4), -z + (tpZ >> 4)));
                        modifiedChunks.add(world.getChunk(-x + (tpX >> 4), -z + (tpZ >> 4)));
                        continue;
                    }
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {-x, z});
                    chunkPositions.add(new int[] {x, -z});
                    chunkPositions.add(new int[] {-x, -z});
                }
            }
        }
        System.out.println("modChunks size = " +modifiedChunks.size());
    }

    private void wipeChunk(WorldChunk chunk) {

        chunk.getBlockEntities().clear();
        ((ChunkMixin) chunk).getBlockEntityNbts().clear();

        ChunkSection[] sections = chunk.getSectionArray();
        for (int i = 0 ; i < sections.length ; i++){
            ChunkSection oldSection = sections[i];
            if (oldSection == null) {continue;}

            ChunkSection newSection = new ChunkSection(
                    new PalettedContainer<>(
                            Blocks.AIR.getDefaultState(),
                            PaletteProvider.forBlockStates(Block.STATE_IDS)
                    ),
                    oldSection.getBiomeContainer()
            );
            newSection.calculateCounts();

            sections[i] = newSection;
        }
//        Heightmap.populateHeightmaps(chunk, ChunkStatus.INITIALIZE_LIGHT.getHeightmapTypes());
        chunk.markNeedsSaving();
        modifiedChunks.add(chunk);
    }

    private void applyBatchedLightingAndSync() {
        ServerLightingProvider lightingProvider = world.getChunkManager().getLightingProvider();
        int bottomSection = world.getBottomSectionCoord();
        int topSection = world.getTopSectionCoord();

        for (WorldChunk chunk : modifiedChunks) {
            for (int sectionY = topSection; sectionY >= bottomSection; sectionY--) {
                ChunkSectionPos sectionPos = ChunkSectionPos.from(chunk.getPos(), sectionY);
                lightingProvider.setSectionStatus(sectionPos, true);
            }
            lightingProvider.propagateLight(chunk.getPos());
            world.getChunkManager().markForUpdate(chunk.getPos().getStartPos());
        }
    }
}