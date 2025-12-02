package net.elgoblin.moremineralblocks.terrain;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.*;

import java.util.*;

public class SkyBlockJob implements TerrainJob{
    private List<int[]> chunkPositions;
    private World world;
    private final ChunkPos center;
    private PlayerEntity player;
    private int closestChunk = 0;
    private int timer = 0;
    private List<ChunkPos> positionsTicketed;

    public SkyBlockJob(World world, ChunkPos center, PlayerEntity player) {
        this.world = world;
        this.center = center;
        this.player = player;
        chunkPositions = new ArrayList<>();
        positionsTicketed = new ArrayList<>();

        for (int z = 0 ; z < 34 ; z++) {
            for (int x = 0 ; x <= 34 ; x++) {
                if (x == 0 && z == 0) { continue;}

                if (x == 0) {
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {x, -z});
                    continue;
                }
                if (z == 0) {
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {-x, z});
                    continue;
                }
                else {
                    chunkPositions.add(new int[] {x, z});
                    chunkPositions.add(new int[] {-x, z});
                    chunkPositions.add(new int[] {x, -z});
                    chunkPositions.add(new int[] {-x, -z});
                }
            }
        }

        chunkPositions.sort(Comparator.comparingInt(p -> p[0] * p[0] + p[1] * p[1]));
    }

    private int loadChunks() {
        int chunksLoaded = positionsTicketed.size();
        timer++;

        for (int i = 0 ; i < 5 ; i++) {
            if (chunksLoaded != chunkPositions.size()) {
                int[] newPosition = chunkPositions.get(this.closestChunk);
                ChunkPos chunkPos = new ChunkPos(this.center.x + newPosition[0], this.center.z + newPosition[1]);
                ServerChunkManager chunkManager = (ServerChunkManager) world.getChunkManager();
                chunkManager.addTicket(ChunkTicketType.FORCED, chunkPos, 2, chunkPos);
                positionsTicketed.add(chunkPos);
                closestChunk++;
                chunksLoaded = positionsTicketed.size();
            }
            else {
                closestChunk = 0;
                return 0;
            }
        }
        return 5;
    }

    public boolean process(int maxBlockOperations) {

        if (loadChunks() != 0) {return false;}
        int chunksProcessed = 0;

        timer++;

        while (chunksProcessed < chunkPositions.size() && !this.chunkPositions.isEmpty() && this.timer % (60*20) == 0) {
            int[] newPosition = chunkPositions.get(this.closestChunk);

            ChunkPos chunkPos = new ChunkPos(this.center.x + newPosition[0], this.center.z + newPosition[1]);

            ServerChunkManager chunkManager = (ServerChunkManager) world.getChunkManager();
            WorldChunk chunk = world.getChunk(chunkPos.x, chunkPos.z);
            ServerWorld serverWorld = (ServerWorld) world;

            serverWorld.getPointOfInterestStorage().remove(chunkPos.getStartPos());
            ChunkSection[] sections = chunk.getSectionArray();
            Registry<Biome> registry = world.getRegistryManager().get(RegistryKeys.BIOME);
            ReadableContainer<RegistryEntry<Biome>> readableContainer = new PalettedContainer<>(registry.getIndexedEntries(), registry.entryOf(BiomeKeys.SNOWY_TAIGA), PalettedContainer.PaletteProvider.BIOME);
            Box chunkBox = new Box(chunkPos.getStartX(), 0, chunkPos.getStartZ(), chunkPos.getEndX() + 1, world.getHeight(), chunkPos.getEndZ() + 1);
            for (Entity entity : world.getOtherEntities(this.player, chunkBox)) {
                entity.remove(Entity.RemovalReason.DISCARDED);
            }

            chunk.clear();
            for (int i = 0 ; i < sections.length ; i++) {
                ChunkSection empty = new ChunkSection(new PalettedContainer<>(Block.STATE_IDS, Blocks.AIR.getDefaultState(), PalettedContainer.PaletteProvider.BLOCK_STATE), readableContainer);
                sections[i] = empty;
            }
            chunk.setLightOn(false);
            ServerLightingProvider lighting = chunkManager.getLightingProvider();
            lighting.enqueue(chunkPos.x, chunkPos.z);
            chunk.setNeedsSaving(true);

            ChunkDataS2CPacket packet = new ChunkDataS2CPacket(chunk, chunkManager.getLightingProvider(), null, null);
            chunkManager.sendToNearbyPlayers(this.player, packet);

            chunksProcessed++;
            this.closestChunk++;
        }
        if (chunksProcessed == chunkPositions.size()) {
            ServerChunkManager chunkManager = (ServerChunkManager) world.getChunkManager();
            for (ChunkPos chunkPos : positionsTicketed) {
                chunkManager.removeTicket(ChunkTicketType.FORCED, chunkPos, 2, chunkPos);
            }
            for (int i = 0 ; i < 200 ; i++) {
                chunkManager.tick(() -> false, true);
            }
            this.chunkPositions.clear();
            this.positionsTicketed.clear();
            this.world = null;
            this.player = null;
            return true;
        }
        return chunksProcessed == chunkPositions.size();
    }
}
