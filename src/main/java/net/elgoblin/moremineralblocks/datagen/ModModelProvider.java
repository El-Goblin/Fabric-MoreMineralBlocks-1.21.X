package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //BlockStateModelGenerator.BlockTexturePool pinkGarnetPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK);
        //pinkGarnetPool.stairs(ModBlocks.PINK_GARNET_STAIRS);
        //pinkGarnetPool.slab(ModBlocks.PINK_GARNET_SLAB);
        //pinkGarnetPool.button(ModBlocks.PINK_GARNET_BUTTON);
        //pinkGarnetPool.pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
        //pinkGarnetPool.fence(ModBlocks.PINK_GARNET_FENCE);
        //pinkGarnetPool.fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE);
        //pinkGarnetPool.wall(ModBlocks.PINK_GARNET_WALL);


        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        BlockStateModelGenerator.BlockTexturePool goldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GOLD_BLOCK);
        goldPool.stairs(ModBlocks.GOLD_STAIRS);
        goldPool.slab(ModBlocks.GOLD_SLAB);
        goldPool.fence(ModBlocks.GOLD_FENCE);
        goldPool.fenceGate(ModBlocks.GOLD_FENCE_GATE);
        goldPool.wall(ModBlocks.GOLD_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.GOLD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.GOLD_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_GOLD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_GOLD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_BLOCK);

        BlockStateModelGenerator.BlockTexturePool diamondPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIAMOND_BLOCK);
        diamondPool.stairs(ModBlocks.DIAMOND_STAIRS);
        diamondPool.slab(ModBlocks.DIAMOND_SLAB);
        diamondPool.fence(ModBlocks.DIAMOND_FENCE);
        diamondPool.fenceGate(ModBlocks.DIAMOND_FENCE_GATE);
        diamondPool.wall(ModBlocks.DIAMOND_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.DIAMOND_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.DIAMOND_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_DIAMOND_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DIAMOND_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DIAMOND_BLOCK);

        BlockStateModelGenerator.BlockTexturePool ironPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.IRON_BLOCK);
        ironPool.stairs(ModBlocks.IRON_STAIRS);
        ironPool.slab(ModBlocks.IRON_SLAB);
        ironPool.fence(ModBlocks.IRON_FENCE);
        ironPool.fenceGate(ModBlocks.IRON_FENCE_GATE);
        ironPool.wall(ModBlocks.IRON_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_BLOCK);

        BlockStateModelGenerator.BlockTexturePool emeraldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.EMERALD_BLOCK);
        emeraldPool.stairs(ModBlocks.EMERALD_STAIRS);
        emeraldPool.slab(ModBlocks.EMERALD_SLAB);
        emeraldPool.fence(ModBlocks.EMERALD_FENCE);
        emeraldPool.fenceGate(ModBlocks.EMERALD_FENCE_GATE);
        emeraldPool.wall(ModBlocks.EMERALD_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.EMERALD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.EMERALD_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_EMERALD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_EMERALD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_EMERALD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_BLOCK);

        BlockStateModelGenerator.BlockTexturePool amethystPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.AMETHYST_BLOCK);
        amethystPool.stairs(ModBlocks.AMETHYST_STAIRS);
        amethystPool.slab(ModBlocks.AMETHYST_SLAB);
        amethystPool.fence(ModBlocks.AMETHYST_FENCE);
        amethystPool.fenceGate(ModBlocks.AMETHYST_FENCE_GATE);
        amethystPool.wall(ModBlocks.AMETHYST_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.AMETHYST_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.AMETHYST_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_AMETHYST_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMETHYST_BRICKS);

        BlockStateModelGenerator.BlockTexturePool coalPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.COAL_BLOCK);
        coalPool.stairs(ModBlocks.COAL_STAIRS);
        coalPool.slab(ModBlocks.COAL_SLAB);
        coalPool.fence(ModBlocks.COAL_FENCE);
        coalPool.fenceGate(ModBlocks.COAL_FENCE_GATE);
        coalPool.wall(ModBlocks.COAL_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.COAL_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.COAL_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_COAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_COAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COAL_BRICKS);

        BlockStateModelGenerator.BlockTexturePool netheritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.NETHERITE_BLOCK);
        netheritePool.stairs(ModBlocks.NETHERITE_STAIRS);
        netheritePool.slab(ModBlocks.NETHERITE_SLAB);
        netheritePool.fence(ModBlocks.NETHERITE_FENCE);
        netheritePool.fenceGate(ModBlocks.NETHERITE_FENCE_GATE);
        netheritePool.wall(ModBlocks.NETHERITE_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.NETHERITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.NETHERITE_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_NETHERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHERITE_BRICKS);

        BlockStateModelGenerator.BlockTexturePool calcitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CALCITE);
        calcitePool.stairs(ModBlocks.CALCITE_STAIRS);
        calcitePool.slab(ModBlocks.CALCITE_SLAB);
        calcitePool.fence(ModBlocks.CALCITE_FENCE);
        calcitePool.fenceGate(ModBlocks.CALCITE_FENCE_GATE);
        calcitePool.wall(ModBlocks.CALCITE_WALL);

        BlockStateModelGenerator.BlockTexturePool lapisPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LAPIS_BLOCK);
        lapisPool.stairs(ModBlocks.LAPIS_STAIRS);
        lapisPool.slab(ModBlocks.LAPIS_SLAB);
        lapisPool.fence(ModBlocks.LAPIS_FENCE);
        lapisPool.fenceGate(ModBlocks.LAPIS_FENCE_GATE);
        lapisPool.wall(ModBlocks.LAPIS_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.LAPIS_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.LAPIS_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_LAPIS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAPIS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_LAPIS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAPIS_BRICKS);

        BlockStateModelGenerator.BlockTexturePool redstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.REDSTONE_BLOCK);
        redstonePool.stairs(ModBlocks.REDSTONE_STAIRS);
        redstonePool.slab(ModBlocks.REDSTONE_SLAB);
        redstonePool.fence(ModBlocks.REDSTONE_FENCE);
        redstonePool.fenceGate(ModBlocks.REDSTONE_FENCE_GATE);
        redstonePool.wall(ModBlocks.REDSTONE_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.REDSTONE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.REDSTONE_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_REDSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.REDSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.REDSTONE_BRICKS);

        BlockStateModelGenerator.BlockTexturePool obsidianPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.OBSIDIAN);
        obsidianPool.stairs(ModBlocks.OBSIDIAN_STAIRS);
        obsidianPool.slab(ModBlocks.OBSIDIAN_SLAB);
        obsidianPool.fence(ModBlocks.OBSIDIAN_FENCE);
        obsidianPool.fenceGate(ModBlocks.OBSIDIAN_FENCE_GATE);
        obsidianPool.wall(ModBlocks.OBSIDIAN_WALL);

        BlockStateModelGenerator.BlockTexturePool bedrockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEDROCK);
        bedrockPool.stairs(ModBlocks.BEDROCK_STAIRS);
        bedrockPool.slab(ModBlocks.BEDROCK_SLAB);
        bedrockPool.fence(ModBlocks.BEDROCK_FENCE);
        bedrockPool.fenceGate(ModBlocks.BEDROCK_FENCE_GATE);
        bedrockPool.wall(ModBlocks.BEDROCK_WALL);

        BlockStateModelGenerator.BlockTexturePool sculkPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SCULK);
        sculkPool.stairs(ModBlocks.SCULK_STAIRS);
        sculkPool.slab(ModBlocks.SCULK_SLAB);
        sculkPool.fence(ModBlocks.SCULK_FENCE);
        sculkPool.fenceGate(ModBlocks.SCULK_FENCE_GATE);
        sculkPool.wall(ModBlocks.SCULK_WALL);

        BlockStateModelGenerator.BlockTexturePool packedIcePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PACKED_ICE);
        packedIcePool.stairs(ModBlocks.PACKED_ICE_STAIRS);
        packedIcePool.slab(ModBlocks.PACKED_ICE_SLAB);
        packedIcePool.fence(ModBlocks.PACKED_ICE_FENCE);
        packedIcePool.fenceGate(ModBlocks.PACKED_ICE_FENCE_GATE);
        packedIcePool.wall(ModBlocks.PACKED_ICE_WALL);

        BlockStateModelGenerator.BlockTexturePool blueIcePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BLUE_ICE);
        blueIcePool.stairs(ModBlocks.BLUE_ICE_STAIRS);
        blueIcePool.slab(ModBlocks.BLUE_ICE_SLAB);
        blueIcePool.fence(ModBlocks.BLUE_ICE_FENCE);
        blueIcePool.fenceGate(ModBlocks.BLUE_ICE_FENCE_GATE);
        blueIcePool.wall(ModBlocks.BLUE_ICE_WALL);

        BlockStateModelGenerator.BlockTexturePool icePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.ICE);
        icePool.stairs(ModBlocks.ICE_STAIRS);
        icePool.slab(ModBlocks.ICE_SLAB);
        icePool.fence(ModBlocks.ICE_FENCE);
        icePool.fenceGate(ModBlocks.ICE_FENCE_GATE);
        icePool.wall(ModBlocks.ICE_WALL);

        BlockStateModelGenerator.BlockTexturePool deepslateEmeraldOrePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DEEPSLATE_EMERALD_ORE);
        deepslateEmeraldOrePool.stairs(ModBlocks.DEEPSLATE_EMERALD_ORE_STAIRS);
        deepslateEmeraldOrePool.slab(ModBlocks.DEEPSLATE_EMERALD_ORE_SLAB);
        deepslateEmeraldOrePool.fence(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE);
        deepslateEmeraldOrePool.fenceGate(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE_GATE);
        deepslateEmeraldOrePool.wall(ModBlocks.DEEPSLATE_EMERALD_ORE_WALL);

        BlockStateModelGenerator.BlockTexturePool prismarineBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PRISMARINE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool darkPrismarinePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DARK_PRISMARINE);
        prismarineBricksPool.wall(ModBlocks.PRISMARINE_BRICK_WALL);
        darkPrismarinePool.wall(ModBlocks.DARK_PRISMARINE_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        //itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEGENDARY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLAMEBERGE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_DRAGONSWORD_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIENDBLADE_LONGSWORD, Models.HANDHELD);
    }
}
