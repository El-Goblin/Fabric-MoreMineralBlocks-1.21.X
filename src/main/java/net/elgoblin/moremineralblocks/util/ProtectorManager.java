package net.elgoblin.moremineralblocks.util;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.persistence.StateSaverAndLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.ArrayList;
import java.util.List;

public class ProtectorManager extends PersistentState {

    public List<Interval3i> listOfIntervals = new ArrayList<>();

    public static ProtectorManager getProtectorManager(MinecraftServer server) {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();

        PersistentState.Type<ProtectorManager> type = new PersistentState.Type<>(
                ProtectorManager::createNew,
                ProtectorManager::createFromNbt,
                null
        );

        return manager.getOrCreate(type, MoreMineralBlocks.MOD_ID + "protector_manager");
    }

    public static ProtectorManager createNew() {
        return new ProtectorManager();
    }

    public static ProtectorManager createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        ProtectorManager protectorManager = new ProtectorManager();

        NbtList intervals = nbt.getList("protector_intervals", 10);
        for (int i = 0 ; i < intervals.size() ; i++) {
            NbtCompound compound = intervals.getCompound(i);
            ProtectorManager.Interval3i newInterval = new ProtectorManager.Interval3i(
                    compound.getInt("x1"),
                    compound.getInt("y1"),
                    compound.getInt("z1"),
                    compound.getInt("x2"),
                    compound.getInt("y2"),
                    compound.getInt("z2"));
            protectorManager.addInterval(newInterval);
        }

        return protectorManager;
    }


    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = new NbtList();
        for (Interval3i interval : listOfIntervals) {
            NbtCompound c = new NbtCompound();
            c.putInt("x1", interval.x1());
            c.putInt("y1", interval.y1());
            c.putInt("z1", interval.z1());
            c.putInt("x2", interval.x2());
            c.putInt("y2", interval.y2());
            c.putInt("z2", interval.z2());
            list.add(c);
        }
        nbt.put("protector_intervals", list);
        return nbt;
    }

    public void addInterval(Interval3i interval) {
        listOfIntervals.add(interval);
        markDirty();
    }

    public boolean isProtected(BlockPos point) {
        for (Interval3i interval : listOfIntervals) {
            if (interval.contains(point)) {
                return true;
            }
        }
        return false;
    }

    public void remove(Interval3i interval) {
        listOfIntervals.remove(interval);
        markDirty();
    }

    public record Interval3i (int x1, int y1, int z1, int x2, int y2, int z2) {

        public Interval3i(Vec3i center, int lengthX, int lengthY, int lengthZ) {
            this(center.getX() - lengthX, center.getY()-lengthY, center.getZ()-lengthZ,
                    center.getX()+lengthX, center.getY()+lengthY, center.getZ()+lengthZ);
        }

        public boolean contains(Vec3i point) {
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            int minZ = Math.min(z1, z2);
            int maxZ = Math.max(z1, z2);

            int x = point.getX();
            int y = point.getY();
            int z = point.getZ();

            return x>=minX && x<=maxX && y>=minY && y<=maxY && z>=minZ && z<=maxZ;
        }
    }
}
