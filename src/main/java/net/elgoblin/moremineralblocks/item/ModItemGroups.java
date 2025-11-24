package net.elgoblin.moremineralblocks.item;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SporeBlossomBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
//    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(MoreMineralBlocks.MOD_ID, "pink_garnet_items"),
//            FabricItemGroup.builder()
//                    .displayName(Text.translatable("itemgroup.moremineralblocks.pink_garnet_items"))
//                    .entries((displayContext, entries) -> {
//
//                        entries.add(ModItems.CHISEL);
//                    })
//                    .build());

    public static final ItemGroup MOD_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(MoreMineralBlocks.MOD_ID, "mod_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.GOLD_STAIRS))
                    .displayName(Text.translatable("itemgroup.moremineralblocks.mod_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(Blocks.GOLD_BLOCK);
                        entries.add(ModBlocks.GOLD_STAIRS);
                        entries.add(ModBlocks.GOLD_SLAB);
                        entries.add(ModBlocks.GOLD_FENCE);
                        entries.add(ModBlocks.GOLD_FENCE_GATE);
                        entries.add(ModBlocks.GOLD_WALL);
                        entries.add(ModBlocks.GOLD_DOOR);
                        entries.add(ModBlocks.GOLD_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_GOLD_BLOCK);
                        entries.add(ModBlocks.POLISHED_GOLD_STAIRS);
                        entries.add(ModBlocks.POLISHED_GOLD_SLAB);
                        entries.add(ModBlocks.POLISHED_GOLD_WALL);
                        entries.add(ModBlocks.POLISHED_GOLD_FENCE);
                        entries.add(ModBlocks.POLISHED_GOLD_FENCE_GATE);

                        entries.add(ModBlocks.GOLD_BRICKS);
                        entries.add(ModBlocks.GOLD_BRICK_STAIRS);
                        entries.add(ModBlocks.GOLD_BRICK_SLAB);
                        entries.add(ModBlocks.GOLD_BRICK_WALL);
                        entries.add(ModBlocks.GOLD_BRICK_FENCE);
                        entries.add(ModBlocks.GOLD_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_GOLD_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_GOLD_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_GOLD_SLAB);
                        entries.add(ModBlocks.CALCIFIED_GOLD_WALL);
                        entries.add(ModBlocks.CALCIFIED_GOLD_FENCE);
                        entries.add(ModBlocks.CALCIFIED_GOLD_FENCE_GATE);

                        entries.add(Blocks.DIAMOND_BLOCK);
                        entries.add(ModBlocks.DIAMOND_STAIRS);
                        entries.add(ModBlocks.DIAMOND_SLAB);
                        entries.add(ModBlocks.DIAMOND_FENCE);
                        entries.add(ModBlocks.DIAMOND_FENCE_GATE);
                        entries.add(ModBlocks.DIAMOND_WALL);
                        entries.add(ModBlocks.DIAMOND_DOOR);
                        entries.add(ModBlocks.DIAMOND_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_DIAMOND_BLOCK);
                        entries.add(ModBlocks.POLISHED_DIAMOND_STAIRS);
                        entries.add(ModBlocks.POLISHED_DIAMOND_SLAB);
                        entries.add(ModBlocks.POLISHED_DIAMOND_WALL);
                        entries.add(ModBlocks.POLISHED_DIAMOND_FENCE);
                        entries.add(ModBlocks.POLISHED_DIAMOND_FENCE_GATE);

                        entries.add(ModBlocks.DIAMOND_BRICKS);
                        entries.add(ModBlocks.DIAMOND_BRICK_STAIRS);
                        entries.add(ModBlocks.DIAMOND_BRICK_SLAB);
                        entries.add(ModBlocks.DIAMOND_BRICK_WALL);
                        entries.add(ModBlocks.DIAMOND_BRICK_FENCE);
                        entries.add(ModBlocks.DIAMOND_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_DIAMOND_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_DIAMOND_SLAB);
                        entries.add(ModBlocks.CALCIFIED_DIAMOND_WALL);
                        entries.add(ModBlocks.CALCIFIED_DIAMOND_FENCE);
                        entries.add(ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE);

                        entries.add(Blocks.IRON_BLOCK);
                        entries.add(ModBlocks.IRON_STAIRS);
                        entries.add(ModBlocks.IRON_SLAB);
                        entries.add(ModBlocks.IRON_FENCE);
                        entries.add(ModBlocks.IRON_FENCE_GATE);
                        entries.add(ModBlocks.IRON_WALL);

                        entries.add(ModBlocks.POLISHED_IRON_BLOCK);
                        entries.add(ModBlocks.POLISHED_IRON_STAIRS);
                        entries.add(ModBlocks.POLISHED_IRON_SLAB);
                        entries.add(ModBlocks.POLISHED_IRON_WALL);
                        entries.add(ModBlocks.POLISHED_IRON_FENCE);
                        entries.add(ModBlocks.POLISHED_IRON_FENCE_GATE);

                        entries.add(ModBlocks.IRON_BRICKS);
                        entries.add(ModBlocks.IRON_BRICK_STAIRS);
                        entries.add(ModBlocks.IRON_BRICK_SLAB);
                        entries.add(ModBlocks.IRON_BRICK_WALL);
                        entries.add(ModBlocks.IRON_BRICK_FENCE);
                        entries.add(ModBlocks.IRON_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_IRON_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_IRON_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_IRON_SLAB);
                        entries.add(ModBlocks.CALCIFIED_IRON_WALL);
                        entries.add(ModBlocks.CALCIFIED_IRON_FENCE);
                        entries.add(ModBlocks.CALCIFIED_IRON_FENCE_GATE);

                        entries.add(Blocks.EMERALD_BLOCK);
                        entries.add(ModBlocks.EMERALD_STAIRS);
                        entries.add(ModBlocks.EMERALD_SLAB);
                        entries.add(ModBlocks.EMERALD_FENCE);
                        entries.add(ModBlocks.EMERALD_FENCE_GATE);
                        entries.add(ModBlocks.EMERALD_WALL);
                        entries.add(ModBlocks.EMERALD_DOOR);
                        entries.add(ModBlocks.EMERALD_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_EMERALD_BLOCK);
                        entries.add(ModBlocks.POLISHED_EMERALD_STAIRS);
                        entries.add(ModBlocks.POLISHED_EMERALD_SLAB);
                        entries.add(ModBlocks.POLISHED_EMERALD_WALL);
                        entries.add(ModBlocks.POLISHED_EMERALD_FENCE);
                        entries.add(ModBlocks.POLISHED_EMERALD_FENCE_GATE);

                        entries.add(ModBlocks.EMERALD_BRICKS);
                        entries.add(ModBlocks.EMERALD_BRICK_STAIRS);
                        entries.add(ModBlocks.EMERALD_BRICK_SLAB);
                        entries.add(ModBlocks.EMERALD_BRICK_WALL);
                        entries.add(ModBlocks.EMERALD_BRICK_FENCE);
                        entries.add(ModBlocks.EMERALD_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_EMERALD_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_EMERALD_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_EMERALD_SLAB);
                        entries.add(ModBlocks.CALCIFIED_EMERALD_WALL);
                        entries.add(ModBlocks.CALCIFIED_EMERALD_FENCE);
                        entries.add(ModBlocks.CALCIFIED_EMERALD_FENCE_GATE);

                        entries.add(ModBlocks.CHISELED_EMERALD_BLOCK);
                        entries.add(ModBlocks.CHISELED_EMERALD_STAIRS);
                        entries.add(ModBlocks.CHISELED_EMERALD_SLAB);
                        entries.add(ModBlocks.CHISELED_EMERALD_WALL);
                        entries.add(ModBlocks.CHISELED_EMERALD_FENCE);
                        entries.add(ModBlocks.CHISELED_EMERALD_FENCE_GATE);

                        entries.add(Blocks.LAPIS_BLOCK);
                        entries.add(ModBlocks.LAPIS_STAIRS);
                        entries.add(ModBlocks.LAPIS_SLAB);
                        entries.add(ModBlocks.LAPIS_FENCE);
                        entries.add(ModBlocks.LAPIS_FENCE_GATE);
                        entries.add(ModBlocks.LAPIS_WALL);
                        entries.add(ModBlocks.LAPIS_DOOR);
                        entries.add(ModBlocks.LAPIS_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_LAPIS_BLOCK);
                        entries.add(ModBlocks.POLISHED_LAPIS_STAIRS);
                        entries.add(ModBlocks.POLISHED_LAPIS_SLAB);
                        entries.add(ModBlocks.POLISHED_LAPIS_WALL);
                        entries.add(ModBlocks.POLISHED_LAPIS_FENCE);
                        entries.add(ModBlocks.POLISHED_LAPIS_FENCE_GATE);

                        entries.add(ModBlocks.LAPIS_BRICKS);
                        entries.add(ModBlocks.LAPIS_BRICK_STAIRS);
                        entries.add(ModBlocks.LAPIS_BRICK_SLAB);
                        entries.add(ModBlocks.LAPIS_BRICK_WALL);
                        entries.add(ModBlocks.LAPIS_BRICK_FENCE);
                        entries.add(ModBlocks.LAPIS_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_LAPIS_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_LAPIS_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_LAPIS_SLAB);
                        entries.add(ModBlocks.CALCIFIED_LAPIS_WALL);
                        entries.add(ModBlocks.CALCIFIED_LAPIS_FENCE);
                        entries.add(ModBlocks.CALCIFIED_LAPIS_FENCE_GATE);

                        entries.add(ModBlocks.COAL_STAIRS);
                        entries.add(ModBlocks.COAL_SLAB);
                        entries.add(ModBlocks.COAL_FENCE);
                        entries.add(ModBlocks.COAL_FENCE_GATE);
                        entries.add(ModBlocks.COAL_WALL);
                        entries.add(ModBlocks.COAL_DOOR);
                        entries.add(ModBlocks.COAL_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_COAL_BLOCK);
                        entries.add(ModBlocks.POLISHED_COAL_STAIRS);
                        entries.add(ModBlocks.POLISHED_COAL_SLAB);
                        entries.add(ModBlocks.POLISHED_COAL_WALL);
                        entries.add(ModBlocks.POLISHED_COAL_FENCE);
                        entries.add(ModBlocks.POLISHED_COAL_FENCE_GATE);

                        entries.add(ModBlocks.COAL_BRICKS);
                        entries.add(ModBlocks.COAL_BRICK_STAIRS);
                        entries.add(ModBlocks.COAL_BRICK_SLAB);
                        entries.add(ModBlocks.COAL_BRICK_WALL);
                        entries.add(ModBlocks.COAL_BRICK_FENCE);
                        entries.add(ModBlocks.COAL_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_COAL_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_COAL_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_COAL_SLAB);
                        entries.add(ModBlocks.CALCIFIED_COAL_WALL);
                        entries.add(ModBlocks.CALCIFIED_COAL_FENCE);
                        entries.add(ModBlocks.CALCIFIED_COAL_FENCE_GATE);

                        entries.add(ModBlocks.NETHERITE_STAIRS);
                        entries.add(ModBlocks.NETHERITE_SLAB);
                        entries.add(ModBlocks.NETHERITE_FENCE);
                        entries.add(ModBlocks.NETHERITE_FENCE_GATE);
                        entries.add(ModBlocks.NETHERITE_WALL);
                        entries.add(ModBlocks.NETHERITE_DOOR);
                        entries.add(ModBlocks.NETHERITE_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_NETHERITE_BLOCK);
                        entries.add(ModBlocks.POLISHED_NETHERITE_STAIRS);
                        entries.add(ModBlocks.POLISHED_NETHERITE_SLAB);
                        entries.add(ModBlocks.POLISHED_NETHERITE_WALL);
                        entries.add(ModBlocks.POLISHED_NETHERITE_FENCE);
                        entries.add(ModBlocks.POLISHED_NETHERITE_FENCE_GATE);

                        entries.add(ModBlocks.NETHERITE_BRICKS);
                        entries.add(ModBlocks.NETHERITE_BRICK_STAIRS);
                        entries.add(ModBlocks.NETHERITE_BRICK_SLAB);
                        entries.add(ModBlocks.NETHERITE_BRICK_WALL);
                        entries.add(ModBlocks.NETHERITE_BRICK_FENCE);
                        entries.add(ModBlocks.NETHERITE_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_NETHERITE_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_NETHERITE_SLAB);
                        entries.add(ModBlocks.CALCIFIED_NETHERITE_WALL);
                        entries.add(ModBlocks.CALCIFIED_NETHERITE_FENCE);
                        entries.add(ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE);

                        entries.add(ModBlocks.AMETHYST_STAIRS);
                        entries.add(ModBlocks.AMETHYST_SLAB);
                        entries.add(ModBlocks.AMETHYST_FENCE);
                        entries.add(ModBlocks.AMETHYST_FENCE_GATE);
                        entries.add(ModBlocks.AMETHYST_WALL);
                        entries.add(ModBlocks.AMETHYST_DOOR);
                        entries.add(ModBlocks.AMETHYST_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_AMETHYST_BLOCK);
                        entries.add(ModBlocks.POLISHED_AMETHYST_STAIRS);
                        entries.add(ModBlocks.POLISHED_AMETHYST_SLAB);
                        entries.add(ModBlocks.POLISHED_AMETHYST_WALL);
                        entries.add(ModBlocks.POLISHED_AMETHYST_FENCE);
                        entries.add(ModBlocks.POLISHED_AMETHYST_FENCE_GATE);

                        entries.add(ModBlocks.AMETHYST_BRICKS);
                        entries.add(ModBlocks.AMETHYST_BRICK_STAIRS);
                        entries.add(ModBlocks.AMETHYST_BRICK_SLAB);
                        entries.add(ModBlocks.AMETHYST_BRICK_WALL);
                        entries.add(ModBlocks.AMETHYST_BRICK_FENCE);
                        entries.add(ModBlocks.AMETHYST_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_AMETHYST_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_AMETHYST_SLAB);
                        entries.add(ModBlocks.CALCIFIED_AMETHYST_WALL);
                        entries.add(ModBlocks.CALCIFIED_AMETHYST_FENCE);
                        entries.add(ModBlocks.CALCIFIED_AMETHYST_FENCE_GATE);

                        entries.add(ModBlocks.REDSTONE_STAIRS);
                        entries.add(ModBlocks.REDSTONE_SLAB);
                        entries.add(ModBlocks.REDSTONE_FENCE);
                        entries.add(ModBlocks.REDSTONE_FENCE_GATE);
                        entries.add(ModBlocks.REDSTONE_WALL);
                        entries.add(ModBlocks.REDSTONE_DOOR);
                        entries.add(ModBlocks.REDSTONE_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_REDSTONE_BLOCK);
                        entries.add(ModBlocks.POLISHED_REDSTONE_STAIRS);
                        entries.add(ModBlocks.POLISHED_REDSTONE_SLAB);
                        entries.add(ModBlocks.POLISHED_REDSTONE_WALL);
                        entries.add(ModBlocks.POLISHED_REDSTONE_FENCE);
                        entries.add(ModBlocks.POLISHED_REDSTONE_FENCE_GATE);

                        entries.add(ModBlocks.REDSTONE_BRICKS);
                        entries.add(ModBlocks.REDSTONE_BRICK_STAIRS);
                        entries.add(ModBlocks.REDSTONE_BRICK_SLAB);
                        entries.add(ModBlocks.REDSTONE_BRICK_WALL);
                        entries.add(ModBlocks.REDSTONE_BRICK_FENCE);
                        entries.add(ModBlocks.REDSTONE_BRICK_FENCE_GATE);

                        entries.add(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
                        entries.add(ModBlocks.CALCIFIED_REDSTONE_STAIRS);
                        entries.add(ModBlocks.CALCIFIED_REDSTONE_SLAB);
                        entries.add(ModBlocks.CALCIFIED_REDSTONE_WALL);
                        entries.add(ModBlocks.CALCIFIED_REDSTONE_FENCE);
                        entries.add(ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE);

                        entries.add(Blocks.CALCITE);
                        entries.add(ModBlocks.CALCITE_STAIRS);
                        entries.add(ModBlocks.CALCITE_SLAB);
                        entries.add(ModBlocks.CALCITE_FENCE);
                        entries.add(ModBlocks.CALCITE_FENCE_GATE);
                        entries.add(ModBlocks.CALCITE_WALL);

                        entries.add(Blocks.OBSIDIAN);
                        entries.add(ModBlocks.OBSIDIAN_STAIRS);
                        entries.add(ModBlocks.OBSIDIAN_SLAB);
                        entries.add(ModBlocks.OBSIDIAN_FENCE);
                        entries.add(ModBlocks.OBSIDIAN_FENCE_GATE);
                        entries.add(ModBlocks.OBSIDIAN_WALL);

                        entries.add(Blocks.BEDROCK);
                        entries.add(ModBlocks.BEDROCK_STAIRS);
                        entries.add(ModBlocks.BEDROCK_SLAB);
                        entries.add(ModBlocks.BEDROCK_FENCE);
                        entries.add(ModBlocks.BEDROCK_FENCE_GATE);
                        entries.add(ModBlocks.BEDROCK_WALL);

                        entries.add(Blocks.SCULK);
                        entries.add(ModBlocks.SCULK_STAIRS);
                        entries.add(ModBlocks.SCULK_SLAB);
                        entries.add(ModBlocks.SCULK_FENCE);
                        entries.add(ModBlocks.SCULK_FENCE_GATE);
                        entries.add(ModBlocks.SCULK_WALL);

                        entries.add(Blocks.PACKED_ICE);
                        entries.add(ModBlocks.PACKED_ICE_STAIRS);
                        entries.add(ModBlocks.PACKED_ICE_SLAB);
                        entries.add(ModBlocks.PACKED_ICE_FENCE);
                        entries.add(ModBlocks.PACKED_ICE_FENCE_GATE);
                        entries.add(ModBlocks.PACKED_ICE_WALL);

                        entries.add(Blocks.BLUE_ICE);
                        entries.add(ModBlocks.BLUE_ICE_STAIRS);
                        entries.add(ModBlocks.BLUE_ICE_SLAB);
                        entries.add(ModBlocks.BLUE_ICE_FENCE);
                        entries.add(ModBlocks.BLUE_ICE_FENCE_GATE);
                        entries.add(ModBlocks.BLUE_ICE_WALL);

                        entries.add(Blocks.ICE);
                        entries.add(ModBlocks.ICE_STAIRS);
                        entries.add(ModBlocks.ICE_SLAB);
                        entries.add(ModBlocks.ICE_FENCE);
                        entries.add(ModBlocks.ICE_FENCE_GATE);
                        entries.add(ModBlocks.ICE_WALL);

                        entries.add(Blocks.DEEPSLATE_EMERALD_ORE);
                        entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_STAIRS);
                        entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_SLAB);
                        entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE);
                        entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE_GATE);
                        entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_WALL);

                        entries.add(ModBlocks.PRISMARINE_BRICK_WALL);
                        entries.add(ModBlocks.DARK_PRISMARINE_WALL);
                    })
                    .build());

    /*public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(MoreMineralBlocks.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.moremineralblocks.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        //entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        //entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        //entries.add(ModBlocks.PINK_GARNET_ORE);
                        //entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
                        //entries.add(ModBlocks.MAGIC_BLOCK);

                        //entries.add(ModBlocks.PINK_GARNET_STAIRS);
                        //entries.add(ModBlocks.PINK_GARNET_SLAB);
                        //entries.add(ModBlocks.PINK_GARNET_BUTTON);
                        //entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
                        //entries.add(ModBlocks.PINK_GARNET_FENCE);
                        //entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);
                        //entries.add(ModBlocks.PINK_GARNET_WALL);

                        entries.add(ModBlocks.GOLD_STAIRS);
                        entries.add(ModBlocks.GOLD_SLAB);
                        entries.add(ModBlocks.GOLD_FENCE);
                        entries.add(ModBlocks.GOLD_FENCE_GATE);
                        entries.add(ModBlocks.GOLD_WALL);
                        entries.add(ModBlocks.GOLD_TEST);
                    })
                    .build());*/

    public static void registerItemGroups() {
        MoreMineralBlocks.LOGGER.info("Registering Item Groups for " + MoreMineralBlocks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.CHAOS_ORB);
        });
    }
}
