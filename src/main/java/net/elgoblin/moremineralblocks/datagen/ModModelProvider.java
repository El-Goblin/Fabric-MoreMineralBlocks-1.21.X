package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.model.Model;
import net.minecraft.util.Identifier;

import java.util.Optional;

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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PROTECTOR_BLOCK);

        BlockStateModelGenerator.BlockTexturePool goldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GOLD_BLOCK);
        goldPool.stairs(ModBlocks.GOLD_STAIRS);
        goldPool.slab(ModBlocks.GOLD_SLAB);
        goldPool.fence(ModBlocks.GOLD_FENCE);
        goldPool.fenceGate(ModBlocks.GOLD_FENCE_GATE);
        goldPool.wall(ModBlocks.GOLD_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.GOLD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.GOLD_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_GOLD_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_GOLD_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_BRICKS);

        BlockStateModelGenerator.BlockTexturePool goldBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GOLD_BRICKS);
        goldBrickPool.stairs(ModBlocks.GOLD_BRICK_STAIRS);
        goldBrickPool.slab(ModBlocks.GOLD_BRICK_SLAB);
        goldBrickPool.fence(ModBlocks.GOLD_BRICK_FENCE);
        goldBrickPool.fenceGate(ModBlocks.GOLD_BRICK_FENCE_GATE);
        goldBrickPool.wall(ModBlocks.GOLD_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedGoldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_GOLD_BLOCK);
        calcifiedGoldPool.stairs(ModBlocks.CALCIFIED_GOLD_STAIRS);
        calcifiedGoldPool.slab(ModBlocks.CALCIFIED_GOLD_SLAB);
        calcifiedGoldPool.fence(ModBlocks.CALCIFIED_GOLD_FENCE);
        calcifiedGoldPool.fenceGate(ModBlocks.CALCIFIED_GOLD_FENCE_GATE);
        calcifiedGoldPool.wall(ModBlocks.CALCIFIED_GOLD_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedGoldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_GOLD_BLOCK);
        polishedGoldPool.stairs(ModBlocks.POLISHED_GOLD_STAIRS);
        polishedGoldPool.slab(ModBlocks.POLISHED_GOLD_SLAB);
        polishedGoldPool.fence(ModBlocks.POLISHED_GOLD_FENCE);
        polishedGoldPool.fenceGate(ModBlocks.POLISHED_GOLD_FENCE_GATE);
        polishedGoldPool.wall(ModBlocks.POLISHED_GOLD_WALL);

        BlockStateModelGenerator.BlockTexturePool diamondPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIAMOND_BLOCK);
        diamondPool.stairs(ModBlocks.DIAMOND_STAIRS);
        diamondPool.slab(ModBlocks.DIAMOND_SLAB);
        diamondPool.fence(ModBlocks.DIAMOND_FENCE);
        diamondPool.fenceGate(ModBlocks.DIAMOND_FENCE_GATE);
        diamondPool.wall(ModBlocks.DIAMOND_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.DIAMOND_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.DIAMOND_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_DIAMOND_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DIAMOND_BRICKS);

        BlockStateModelGenerator.BlockTexturePool diamondBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DIAMOND_BRICKS);
        diamondBrickPool.stairs(ModBlocks.DIAMOND_BRICK_STAIRS);
        diamondBrickPool.slab(ModBlocks.DIAMOND_BRICK_SLAB);
        diamondBrickPool.fence(ModBlocks.DIAMOND_BRICK_FENCE);
        diamondBrickPool.fenceGate(ModBlocks.DIAMOND_BRICK_FENCE_GATE);
        diamondBrickPool.wall(ModBlocks.DIAMOND_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedDiamondPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
        calcifiedDiamondPool.stairs(ModBlocks.CALCIFIED_DIAMOND_STAIRS);
        calcifiedDiamondPool.slab(ModBlocks.CALCIFIED_DIAMOND_SLAB);
        calcifiedDiamondPool.fence(ModBlocks.CALCIFIED_DIAMOND_FENCE);
        calcifiedDiamondPool.fenceGate(ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE);
        calcifiedDiamondPool.wall(ModBlocks.CALCIFIED_DIAMOND_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedDiamondPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_DIAMOND_BLOCK);
        polishedDiamondPool.stairs(ModBlocks.POLISHED_DIAMOND_STAIRS);
        polishedDiamondPool.slab(ModBlocks.POLISHED_DIAMOND_SLAB);
        polishedDiamondPool.fence(ModBlocks.POLISHED_DIAMOND_FENCE);
        polishedDiamondPool.fenceGate(ModBlocks.POLISHED_DIAMOND_FENCE_GATE);
        polishedDiamondPool.wall(ModBlocks.POLISHED_DIAMOND_WALL);

        BlockStateModelGenerator.BlockTexturePool ironPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.IRON_BLOCK);
        ironPool.stairs(ModBlocks.IRON_STAIRS);
        ironPool.slab(ModBlocks.IRON_SLAB);
        ironPool.fence(ModBlocks.IRON_FENCE);
        ironPool.fenceGate(ModBlocks.IRON_FENCE_GATE);
        ironPool.wall(ModBlocks.IRON_WALL);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_IRON_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_IRON_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_BRICKS);

        BlockStateModelGenerator.BlockTexturePool ironBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.IRON_BRICKS);
        ironBrickPool.stairs(ModBlocks.IRON_BRICK_STAIRS);
        ironBrickPool.slab(ModBlocks.IRON_BRICK_SLAB);
        ironBrickPool.fence(ModBlocks.IRON_BRICK_FENCE);
        ironBrickPool.fenceGate(ModBlocks.IRON_BRICK_FENCE_GATE);
        ironBrickPool.wall(ModBlocks.IRON_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedIronPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_IRON_BLOCK);
        calcifiedIronPool.stairs(ModBlocks.CALCIFIED_IRON_STAIRS);
        calcifiedIronPool.slab(ModBlocks.CALCIFIED_IRON_SLAB);
        calcifiedIronPool.fence(ModBlocks.CALCIFIED_IRON_FENCE);
        calcifiedIronPool.fenceGate(ModBlocks.CALCIFIED_IRON_FENCE_GATE);
        calcifiedIronPool.wall(ModBlocks.CALCIFIED_IRON_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedIronPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_IRON_BLOCK);
        polishedIronPool.stairs(ModBlocks.POLISHED_IRON_STAIRS);
        polishedIronPool.slab(ModBlocks.POLISHED_IRON_SLAB);
        polishedIronPool.fence(ModBlocks.POLISHED_IRON_FENCE);
        polishedIronPool.fenceGate(ModBlocks.POLISHED_IRON_FENCE_GATE);
        polishedIronPool.wall(ModBlocks.POLISHED_IRON_WALL);

        BlockStateModelGenerator.BlockTexturePool emeraldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.EMERALD_BLOCK);
        emeraldPool.stairs(ModBlocks.EMERALD_STAIRS);
        emeraldPool.slab(ModBlocks.EMERALD_SLAB);
        emeraldPool.fence(ModBlocks.EMERALD_FENCE);
        emeraldPool.fenceGate(ModBlocks.EMERALD_FENCE_GATE);
        emeraldPool.wall(ModBlocks.EMERALD_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.EMERALD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.EMERALD_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_EMERALD_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_EMERALD_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_EMERALD_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_BRICKS);

        BlockStateModelGenerator.BlockTexturePool emeraldBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.EMERALD_BRICKS);
        emeraldBrickPool.stairs(ModBlocks.EMERALD_BRICK_STAIRS);
        emeraldBrickPool.slab(ModBlocks.EMERALD_BRICK_SLAB);
        emeraldBrickPool.fence(ModBlocks.EMERALD_BRICK_FENCE);
        emeraldBrickPool.fenceGate(ModBlocks.EMERALD_BRICK_FENCE_GATE);
        emeraldBrickPool.wall(ModBlocks.EMERALD_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedEmeraldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_EMERALD_BLOCK);
        calcifiedEmeraldPool.stairs(ModBlocks.CALCIFIED_EMERALD_STAIRS);
        calcifiedEmeraldPool.slab(ModBlocks.CALCIFIED_EMERALD_SLAB);
        calcifiedEmeraldPool.fence(ModBlocks.CALCIFIED_EMERALD_FENCE);
        calcifiedEmeraldPool.fenceGate(ModBlocks.CALCIFIED_EMERALD_FENCE_GATE);
        calcifiedEmeraldPool.wall(ModBlocks.CALCIFIED_EMERALD_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedEmeraldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_EMERALD_BLOCK);
        polishedEmeraldPool.stairs(ModBlocks.POLISHED_EMERALD_STAIRS);
        polishedEmeraldPool.slab(ModBlocks.POLISHED_EMERALD_SLAB);
        polishedEmeraldPool.fence(ModBlocks.POLISHED_EMERALD_FENCE);
        polishedEmeraldPool.fenceGate(ModBlocks.POLISHED_EMERALD_FENCE_GATE);
        polishedEmeraldPool.wall(ModBlocks.POLISHED_EMERALD_WALL);

        BlockStateModelGenerator.BlockTexturePool chiseledGoldPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CHISELED_EMERALD_BLOCK);
        chiseledGoldPool.stairs(ModBlocks.CHISELED_EMERALD_STAIRS);
        chiseledGoldPool.slab(ModBlocks.CHISELED_EMERALD_SLAB);
        chiseledGoldPool.fence(ModBlocks.CHISELED_EMERALD_FENCE);
        chiseledGoldPool.fenceGate(ModBlocks.CHISELED_EMERALD_FENCE_GATE);
        chiseledGoldPool.wall(ModBlocks.CHISELED_EMERALD_WALL);

        BlockStateModelGenerator.BlockTexturePool amethystPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.AMETHYST_BLOCK);
        amethystPool.stairs(ModBlocks.AMETHYST_STAIRS);
        amethystPool.slab(ModBlocks.AMETHYST_SLAB);
        amethystPool.fence(ModBlocks.AMETHYST_FENCE);
        amethystPool.fenceGate(ModBlocks.AMETHYST_FENCE_GATE);
        amethystPool.wall(ModBlocks.AMETHYST_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.AMETHYST_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.AMETHYST_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_AMETHYST_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMETHYST_BRICKS);

        BlockStateModelGenerator.BlockTexturePool amethystBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AMETHYST_BRICKS);
        amethystBrickPool.stairs(ModBlocks.AMETHYST_BRICK_STAIRS);
        amethystBrickPool.slab(ModBlocks.AMETHYST_BRICK_SLAB);
        amethystBrickPool.fence(ModBlocks.AMETHYST_BRICK_FENCE);
        amethystBrickPool.fenceGate(ModBlocks.AMETHYST_BRICK_FENCE_GATE);
        amethystBrickPool.wall(ModBlocks.AMETHYST_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedAmethystPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
        calcifiedAmethystPool.stairs(ModBlocks.CALCIFIED_AMETHYST_STAIRS);
        calcifiedAmethystPool.slab(ModBlocks.CALCIFIED_AMETHYST_SLAB);
        calcifiedAmethystPool.fence(ModBlocks.CALCIFIED_AMETHYST_FENCE);
        calcifiedAmethystPool.fenceGate(ModBlocks.CALCIFIED_AMETHYST_FENCE_GATE);
        calcifiedAmethystPool.wall(ModBlocks.CALCIFIED_AMETHYST_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedAmethystPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_AMETHYST_BLOCK);
        polishedAmethystPool.stairs(ModBlocks.POLISHED_AMETHYST_STAIRS);
        polishedAmethystPool.slab(ModBlocks.POLISHED_AMETHYST_SLAB);
        polishedAmethystPool.fence(ModBlocks.POLISHED_AMETHYST_FENCE);
        polishedAmethystPool.fenceGate(ModBlocks.POLISHED_AMETHYST_FENCE_GATE);
        polishedAmethystPool.wall(ModBlocks.POLISHED_AMETHYST_WALL);

        BlockStateModelGenerator.BlockTexturePool coalPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.COAL_BLOCK);
        coalPool.stairs(ModBlocks.COAL_STAIRS);
        coalPool.slab(ModBlocks.COAL_SLAB);
        coalPool.fence(ModBlocks.COAL_FENCE);
        coalPool.fenceGate(ModBlocks.COAL_FENCE_GATE);
        coalPool.wall(ModBlocks.COAL_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.COAL_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.COAL_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_COAL_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_COAL_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COAL_BRICKS);

        BlockStateModelGenerator.BlockTexturePool coalBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COAL_BRICKS);
        coalBrickPool.stairs(ModBlocks.COAL_BRICK_STAIRS);
        coalBrickPool.slab(ModBlocks.COAL_BRICK_SLAB);
        coalBrickPool.fence(ModBlocks.COAL_BRICK_FENCE);
        coalBrickPool.fenceGate(ModBlocks.COAL_BRICK_FENCE_GATE);
        coalBrickPool.wall(ModBlocks.COAL_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedCoalPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_COAL_BLOCK);
        calcifiedCoalPool.stairs(ModBlocks.CALCIFIED_COAL_STAIRS);
        calcifiedCoalPool.slab(ModBlocks.CALCIFIED_COAL_SLAB);
        calcifiedCoalPool.fence(ModBlocks.CALCIFIED_COAL_FENCE);
        calcifiedCoalPool.fenceGate(ModBlocks.CALCIFIED_COAL_FENCE_GATE);
        calcifiedCoalPool.wall(ModBlocks.CALCIFIED_COAL_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedCoalPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_COAL_BLOCK);
        polishedCoalPool.stairs(ModBlocks.POLISHED_COAL_STAIRS);
        polishedCoalPool.slab(ModBlocks.POLISHED_COAL_SLAB);
        polishedCoalPool.fence(ModBlocks.POLISHED_COAL_FENCE);
        polishedCoalPool.fenceGate(ModBlocks.POLISHED_COAL_FENCE_GATE);
        polishedCoalPool.wall(ModBlocks.POLISHED_COAL_WALL);

        BlockStateModelGenerator.BlockTexturePool netheritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.NETHERITE_BLOCK);
        netheritePool.stairs(ModBlocks.NETHERITE_STAIRS);
        netheritePool.slab(ModBlocks.NETHERITE_SLAB);
        netheritePool.fence(ModBlocks.NETHERITE_FENCE);
        netheritePool.fenceGate(ModBlocks.NETHERITE_FENCE_GATE);
        netheritePool.wall(ModBlocks.NETHERITE_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.NETHERITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.NETHERITE_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_NETHERITE_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHERITE_BRICKS);

        BlockStateModelGenerator.BlockTexturePool netheriteBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.NETHERITE_BRICKS);
        netheriteBrickPool.stairs(ModBlocks.NETHERITE_BRICK_STAIRS);
        netheriteBrickPool.slab(ModBlocks.NETHERITE_BRICK_SLAB);
        netheriteBrickPool.fence(ModBlocks.NETHERITE_BRICK_FENCE);
        netheriteBrickPool.fenceGate(ModBlocks.NETHERITE_BRICK_FENCE_GATE);
        netheriteBrickPool.wall(ModBlocks.NETHERITE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedNetheritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
        calcifiedNetheritePool.stairs(ModBlocks.CALCIFIED_NETHERITE_STAIRS);
        calcifiedNetheritePool.slab(ModBlocks.CALCIFIED_NETHERITE_SLAB);
        calcifiedNetheritePool.fence(ModBlocks.CALCIFIED_NETHERITE_FENCE);
        calcifiedNetheritePool.fenceGate(ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE);
        calcifiedNetheritePool.wall(ModBlocks.CALCIFIED_NETHERITE_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedNetheritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_NETHERITE_BLOCK);
        polishedNetheritePool.stairs(ModBlocks.POLISHED_NETHERITE_STAIRS);
        polishedNetheritePool.slab(ModBlocks.POLISHED_NETHERITE_SLAB);
        polishedNetheritePool.fence(ModBlocks.POLISHED_NETHERITE_FENCE);
        polishedNetheritePool.fenceGate(ModBlocks.POLISHED_NETHERITE_FENCE_GATE);
        polishedNetheritePool.wall(ModBlocks.POLISHED_NETHERITE_WALL);

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
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_LAPIS_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_LAPIS_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAPIS_BRICKS);

        BlockStateModelGenerator.BlockTexturePool lapisBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAPIS_BRICKS);
        lapisBrickPool.stairs(ModBlocks.LAPIS_BRICK_STAIRS);
        lapisBrickPool.slab(ModBlocks.LAPIS_BRICK_SLAB);
        lapisBrickPool.fence(ModBlocks.LAPIS_BRICK_FENCE);
        lapisBrickPool.fenceGate(ModBlocks.LAPIS_BRICK_FENCE_GATE);
        lapisBrickPool.wall(ModBlocks.LAPIS_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedLapisPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_LAPIS_BLOCK);
        calcifiedLapisPool.stairs(ModBlocks.CALCIFIED_LAPIS_STAIRS);
        calcifiedLapisPool.slab(ModBlocks.CALCIFIED_LAPIS_SLAB);
        calcifiedLapisPool.fence(ModBlocks.CALCIFIED_LAPIS_FENCE);
        calcifiedLapisPool.fenceGate(ModBlocks.CALCIFIED_LAPIS_FENCE_GATE);
        calcifiedLapisPool.wall(ModBlocks.CALCIFIED_LAPIS_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedLapisPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_LAPIS_BLOCK);
        polishedLapisPool.stairs(ModBlocks.POLISHED_LAPIS_STAIRS);
        polishedLapisPool.slab(ModBlocks.POLISHED_LAPIS_SLAB);
        polishedLapisPool.fence(ModBlocks.POLISHED_LAPIS_FENCE);
        polishedLapisPool.fenceGate(ModBlocks.POLISHED_LAPIS_FENCE_GATE);
        polishedLapisPool.wall(ModBlocks.POLISHED_LAPIS_WALL);

        BlockStateModelGenerator.BlockTexturePool redstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.REDSTONE_BLOCK);
        redstonePool.stairs(ModBlocks.REDSTONE_STAIRS);
        redstonePool.slab(ModBlocks.REDSTONE_SLAB);
        redstonePool.fence(ModBlocks.REDSTONE_FENCE);
        redstonePool.fenceGate(ModBlocks.REDSTONE_FENCE_GATE);
        redstonePool.wall(ModBlocks.REDSTONE_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.REDSTONE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.REDSTONE_TRAPDOOR);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_REDSTONE_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.REDSTONE_BRICKS);

        BlockStateModelGenerator.BlockTexturePool redstoneBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.REDSTONE_BRICKS);
        redstoneBrickPool.stairs(ModBlocks.REDSTONE_BRICK_STAIRS);
        redstoneBrickPool.slab(ModBlocks.REDSTONE_BRICK_SLAB);
        redstoneBrickPool.fence(ModBlocks.REDSTONE_BRICK_FENCE);
        redstoneBrickPool.fenceGate(ModBlocks.REDSTONE_BRICK_FENCE_GATE);
        redstoneBrickPool.wall(ModBlocks.REDSTONE_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool calcifiedRedstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
        calcifiedRedstonePool.stairs(ModBlocks.CALCIFIED_REDSTONE_STAIRS);
        calcifiedRedstonePool.slab(ModBlocks.CALCIFIED_REDSTONE_SLAB);
        calcifiedRedstonePool.fence(ModBlocks.CALCIFIED_REDSTONE_FENCE);
        calcifiedRedstonePool.fenceGate(ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE);
        calcifiedRedstonePool.wall(ModBlocks.CALCIFIED_REDSTONE_WALL);

        BlockStateModelGenerator.BlockTexturePool polishedRedstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_REDSTONE_BLOCK);
        polishedRedstonePool.stairs(ModBlocks.POLISHED_REDSTONE_STAIRS);
        polishedRedstonePool.slab(ModBlocks.POLISHED_REDSTONE_SLAB);
        polishedRedstonePool.fence(ModBlocks.POLISHED_REDSTONE_FENCE);
        polishedRedstonePool.fenceGate(ModBlocks.POLISHED_REDSTONE_FENCE_GATE);
        polishedRedstonePool.wall(ModBlocks.POLISHED_REDSTONE_WALL);

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

//    private static Model item(TextureKey... requiredTextureKeys) {
//        return new Model(Optional.of(Identifier.of(MoreMineralBlocks.MOD_ID+":item/longswords_parent")), Optional.empty(), requiredTextureKeys);
//    }
//
//    public static final Model LONGSWORD = item(TextureKey.LAYER0);
//public static final Model LONGSWORD = Models.item(Identifier.of(MoreMineralBlocks.MOD_ID, "item/longswords_parent"));

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        //itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEGENDARY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEGENDARY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEGENDARY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEGENDARY_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEGENDARY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLAMEBERGE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_DRAGONSWORD_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIENDBLADE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEGENDARY_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLASH, Models.HANDHELD);
        //itemModelGenerator.register(ModItems.MAGIC_MIRROR, Models.HANDHELD);
        //itemModelGenerator.register(ModItems.GOLDEN_MAGIC_MIRROR, Models.HANDHELD);
        //itemModelGenerator.register(ModItems.CHAOS_MIRROR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LA_LECHONA, Models.HANDHELD);
    }
}
