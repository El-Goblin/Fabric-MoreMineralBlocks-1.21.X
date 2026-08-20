package net.elgoblin.moremineralblocks.component;

import com.mojang.serialization.Codec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class ModAttachmentTypes {

    public static final AttachmentType<Boolean> NIGHT_OWL =
            AttachmentRegistry.create(
                    Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "night_owl"),
                    builder -> builder
                            .persistent(Codec.BOOL)
                            .copyOnDeath()
            );

//    public static final AttachmentType<Long> ADYACENT_BLOCK_PLACING =
//            AttachmentRegistry.create(
//                    Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "adyacent_block_placing"),
//                    builder -> builder
//                            .persistent(Codec.LONG)
//                            .copyOnDeath()
//                            .syncWith(
//                                    ByteBufCodecs.LONG,
//                                    AttachmentSyncPredicate.targetOnly()
//                            )
//            );

    public static void registerAttachmentTypes() {
        MoreMineralBlocks.LOGGER.info("Registering Attachments for " + MoreMineralBlocks.MOD_ID);
    }
}
