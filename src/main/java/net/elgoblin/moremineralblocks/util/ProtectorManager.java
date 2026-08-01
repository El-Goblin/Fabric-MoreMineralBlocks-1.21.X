package net.elgoblin.moremineralblocks.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.storage.SavedDataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtectorManager extends SavedData {

    private record Protector(int centerX, int centerY, int centerZ, int radiusX, int radiusY, int radiusZ){
        public boolean isProtected(int x, int y, int z) {
            return  x >= centerX - radiusX && x <= centerX + radiusX &&
                    y >= centerY - radiusY && y <= centerY + radiusY &&
                    z >= centerZ - radiusZ && z <= centerZ + radiusZ;
        }

        public static final Codec<Protector> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("centerX").forGetter(Protector::centerX),
                        Codec.INT.fieldOf("centerY").forGetter(Protector::centerY),
                        Codec.INT.fieldOf("centerZ").forGetter(Protector::centerZ),
                        Codec.INT.fieldOf("radiusX").forGetter(Protector::radiusX),
                        Codec.INT.fieldOf("radiusY").forGetter(Protector::radiusY),
                        Codec.INT.fieldOf("radiusZ").forGetter(Protector::radiusZ)
                ).apply(instance, Protector::new)
        );
    }

    private final List<Protector> protectors = new ArrayList<>();
    private final Map<Long, List<Protector>> protectorMap = new HashMap<>();

    public static final Codec<ProtectorManager> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(Protector.CODEC.listOf()
                                    .fieldOf("protectors")
                                    .forGetter(manager -> manager.protectors)
                    ).apply(instance, protectors -> {
                        ProtectorManager manager = new ProtectorManager();
                        manager.protectors.addAll(protectors);

                        for (Protector protector : protectors) {
                            manager.addProtectorToMap(protector);
                        }

                        return manager;
                    })
            );

    public static final SavedDataType<ProtectorManager> TYPE =
            new SavedDataType<>(
                    Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "protector_manager"),
                    ProtectorManager::new,
                    CODEC,
                    DataFixTypes.LEVEL
            );

    public static ProtectorManager getProtectorManager(MinecraftServer server) {
        SavedDataStorage manager = server.overworld().getDataStorage();
        return manager.computeIfAbsent(TYPE);
    }

    public boolean isProtected(BlockPos point) {
        long chunkAsLong = ChunkPos.pack(point);
        for (Protector protector : protectorMap.getOrDefault(chunkAsLong, List.of())) {
            if (protector.isProtected(point.getX(), point.getY(), point.getZ())) {
                return true;
            }
        }
        return false;
    }

    private void addProtectorToMap(Protector protector) {
        int minChunkX = (protector.centerX() - protector.radiusX()) >> 4;
        int maxChunkX = (protector.centerX() + protector.radiusX()) >> 4;
        int minChunkZ = (protector.centerZ() - protector.radiusZ()) >> 4;
        int maxChunkZ = (protector.centerZ() + protector.radiusZ()) >> 4;

        for (int x = minChunkX; x <= maxChunkX; x++) {
            for (int z = minChunkZ; z <= maxChunkZ; z++) {
                long chunk = ChunkPos.pack(x, z);
                protectorMap.computeIfAbsent(chunk, k -> new ArrayList<>()).add(protector);
            }
        }
    }

    public void removeProtector(BlockPos pos) {
        List<Protector> protectorsForPos = protectorMap.get(ChunkPos.pack(pos));

        if (protectorsForPos == null) {
            return;
        }

        Protector protector = protectorsForPos.stream()
                .filter(p -> p.centerX() == pos.getX()
                        && p.centerY() == pos.getY()
                        && p.centerZ() == pos.getZ())
                .findFirst()
                .orElse(null);

        if (protector == null) {
            return;
        }
        protectors.remove(protector);

        int minChunkX = (protector.centerX() - protector.radiusX()) >> 4;
        int maxChunkX = (protector.centerX() + protector.radiusX()) >> 4;
        int minChunkZ = (protector.centerZ() - protector.radiusZ()) >> 4;
        int maxChunkZ = (protector.centerZ() + protector.radiusZ()) >> 4;

        for (int x = minChunkX; x <= maxChunkX; x++) {
            for (int z = minChunkZ; z <= maxChunkZ; z++) {
                long chunkKey = ChunkPos.pack(x, z);
                List<Protector> chunkProtectors = protectorMap.get(chunkKey);

                if (chunkProtectors != null) {
                    chunkProtectors.remove(protector);

                    if (chunkProtectors.isEmpty()) {
                        protectorMap.remove(chunkKey);
                    }
                }
            }
        }
        setDirty();
    }

    public void addProtector(BlockPos point, int radiusX, int radiusY, int radiusZ) {
        Protector protector = new Protector(point.getX(), point.getY(), point.getZ(), radiusX, radiusY, radiusZ);
        protectors.add(protector);
        addProtectorToMap(protector);
        setDirty();
    }
}
