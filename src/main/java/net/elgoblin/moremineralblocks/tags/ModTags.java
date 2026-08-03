package net.elgoblin.moremineralblocks.tags;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> INCORRECT_FOR_LEGENDARY_TOOL = createTag("incorrect_for_legendary_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> LEGENDARY_REPAIR = createTag("legendary_repair");
        public static final TagKey<Item> LONGSWORDS = createTag("longsword");
        public static final TagKey<Item> LEGENDARY_TOOLS = createTag("legendary_tools");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, name));
        }
    }
}
