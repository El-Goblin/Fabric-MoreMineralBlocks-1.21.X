package net.elgoblin.moremineralblocks.structure;

public class MoreMineralBlocksStructure {

    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It's great for mod/datapacks compatibility.
     */
    public static void registerStructureTypes() {
        //SKY_STRUCTURES = Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(StructureTutorialMain.MODID, "sky_structures"), () -> SkyStructures.CODEC);
        //OCEAN_STRUCTURES = Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(StructureTutorialMain.MODID, "ocean_structures"), () -> OceanStructures.CODEC);
        //END_ISLAND_STRUCTURES  = Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(StructureTutorialMain.MODID, "end_island_structures"), () -> EndIslandStructures.CODEC);
    }

}
