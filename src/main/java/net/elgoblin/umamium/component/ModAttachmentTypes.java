package net.elgoblin.umamium.component;

import com.mojang.serialization.Codec;
import net.elgoblin.umamium.Umamium;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public class ModAttachmentTypes {

    public static final AttachmentType<Boolean> NIGHT_OWL =
            AttachmentRegistry.create(
                    Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "night_owl"),
                    builder -> builder
                            .persistent(Codec.BOOL)
                            .copyOnDeath()
            );

//    public static final AttachmentType<Long> ADYACENT_BLOCK_PLACING =
//            AttachmentRegistry.create(
//                    Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "adyacent_block_placing"),
//                    builder -> builder
//                            .persistent(Codec.LONG)
//                            .copyOnDeath()
//                            .syncWith(
//                                    ByteBufCodecs.LONG,
//                                    AttachmentSyncPredicate.targetOnly()
//                            )
//            );

    public static void registerAttachmentTypes() {
        Umamium.LOGGER.info("Registering Attachments for " + Umamium.MOD_ID);
    }
}
