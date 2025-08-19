package net.elgoblin.moremineralblocks.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import java.util.List;
import java.util.Map;

public class AdventureEffect extends StatusEffect {
    private Integer counter = 0;

    public AdventureEffect(StatusEffectCategory category, int color) {
        super(category, color);
        counter = 0;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            counter = counter+1;
            if (entity.isPlayer()) {
                MinecraftServer server = entity.getServer();
                List<ServerPlayerEntity> list = server.getPlayerManager().getPlayerList();
                if (!list.isEmpty()) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)list.get(0);
                    serverPlayerEntity.changeGameMode(GameMode.ADVENTURE);
                }
            }
            if (counter % 100 == 0) {
                entity.sendMessage(Text.of("Pasaron 5 segundos"));
            }
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
