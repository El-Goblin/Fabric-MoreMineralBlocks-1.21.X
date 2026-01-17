//package net.elgoblin.moremineralblocks.persistence;
//
//import net.elgoblin.moremineralblocks.MoreMineralBlocks;
//import net.elgoblin.moremineralblocks.util.ProtectorManager;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.nbt.NbtList;
//import net.minecraft.registry.RegistryWrapper;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.world.PersistentState;
//import net.minecraft.world.PersistentStateManager;
//
//public class StateSaverAndLoader extends PersistentState {
//
//    private boolean skyBlockHappened = false;
//
//    private StateSaverAndLoader(boolean skyBlockHappened) {
//        this.skyBlockHappened = skyBlockHappened;
//    }
//
//    public static StateSaverAndLoader createNew() {
//        return new StateSaverAndLoader();
//    }
//
//    public StateSaverAndLoader() {
//        this(false);
//    }
//
//    public boolean hasSkyblockHappened() {
//        return skyBlockHappened;
//    }
//
//    public void setSkyBlockHappened(boolean value) {
//        skyBlockHappened = value;
//        markDirty();
//    }
//
//    public void setDirty() {markDirty();}
//
////    // CODEC — describes how to read/write this state
////    public static final Codec<StateSaverAndLoader> CODEC = RecordCodecBuilder.create(instance ->
////            instance.group(
////                    Codec.BOOL
////                            .fieldOf("skyblock_happened")
////                            .forGetter(state -> state.skyBlockHappened)
////            ).apply(instance, StateSaverAndLoader::new)
////    );
//
//    @Override
//    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
//        nbt.putBoolean("skyblock_happened", this.skyBlockHappened);
//        return nbt;
//    }
//
//    public static StateSaverAndLoader createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
//        return new StateSaverAndLoader(nbt.getBoolean("skyblock_happened"));
//    }
//
//    public static StateSaverAndLoader loadSave(MinecraftServer server) {
//        PersistentStateManager stateManager = server.getOverworld().getPersistentStateManager();
//
//        PersistentState.Type<StateSaverAndLoader> type = new PersistentState.Type<>(
//                StateSaverAndLoader::createNew,
//                StateSaverAndLoader::createFromNbt,
//                null
//        );
//
//        return stateManager.getOrCreate(type, MoreMineralBlocks.MOD_ID);
//    }
//}
