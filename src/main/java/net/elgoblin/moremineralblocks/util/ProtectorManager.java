package net.elgoblin.moremineralblocks.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;

import java.util.ArrayList;
import java.util.List;

public class ProtectorManager extends PersistentState {

    public List<Interval3i> listOfIntervals = new ArrayList<>();

    public static final Codec<ProtectorManager> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Interval3i.CODEC.listOf().fieldOf("intervals").forGetter(manager -> manager.listOfIntervals)
            ).apply(instance, intervals -> {
                ProtectorManager manager = new ProtectorManager();
                manager.listOfIntervals = new ArrayList<>(intervals);
                return manager;
            })
    );

    public static final PersistentStateType<ProtectorManager> TYPE = new PersistentStateType<>(
            "protector_manager",
            ProtectorManager::new,
            CODEC,
            null
    );

    public void addInterval(Interval3i interval) {
        listOfIntervals.add(interval);
        markDirty();
    }

    public static ProtectorManager getProtectorManager(MinecraftServer server) {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
        return manager.getOrCreate(TYPE);
    }

//    public static ProtectorManager createNew() {
//        return new ProtectorManager();
//    }

//    public static ProtectorManager createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
//        ProtectorManager protectorManager = new ProtectorManager();
//
//        NbtList intervals = nbt.getList("protector_intervals").get();
//        for (int i = 0 ; i < intervals.size() ; i++) {
//            NbtCompound compound = intervals.getCompound(i).get();
//            ProtectorManager.Interval3i newInterval = new ProtectorManager.Interval3i(
//                    compound.getInt("x1").get(),
//                    compound.getInt("y1").get(),
//                    compound.getInt("z1").get(),
//                    compound.getInt("x2").get(),
//                    compound.getInt("y2").get(),
//                    compound.getInt("z2").get());
//            protectorManager.addInterval(newInterval);
//        }
//
//        return protectorManager;
//    }

//    public NbtCompound writeData(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
//        NbtList list = new NbtList();
//        for (Interval3i interval : listOfIntervals) {
//            NbtCompound c = new NbtCompound();
//            c.putInt("x1", interval.x1());
//            c.putInt("y1", interval.y1());
//            c.putInt("z1", interval.z1());
//            c.putInt("x2", interval.x2());
//            c.putInt("y2", interval.y2());
//            c.putInt("z2", interval.z2());
//            list.add(c);
//        }
//        nbt.put("protector_intervals", list);
//        return nbt;
//    }

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

        public static final Codec<Interval3i> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("x1").forGetter(Interval3i::x1),
                        Codec.INT.fieldOf("y1").forGetter(Interval3i::y1),
                        Codec.INT.fieldOf("z1").forGetter(Interval3i::z1),
                        Codec.INT.fieldOf("x2").forGetter(Interval3i::x2),
                        Codec.INT.fieldOf("y2").forGetter(Interval3i::y2),
                        Codec.INT.fieldOf("z2").forGetter(Interval3i::z2)
                ).apply(instance, Interval3i::new)
        );

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
