package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.mixin.itemgroup.ItemGroupAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ChaosOrbEntity extends ThrownItemEntity {

    private Random random = Random.create();
    private int effectsCount = 8;
    private List<Consumer<HitResult>> chaosEffects = new ArrayList<>(List.of(
//              this::crash,
            this::spawnPig,
            this::beginThunderstorm,
            this::goDownXBlocks,
            this::randomizePlayersPositions,
            this::applyBeaconEffect,
            this::getMythicItem,
            this::spawnSkeletonHorse,
            this::getElytra
    ));

    public ChaosOrbEntity(EntityType<? extends ChaosOrbEntity> entityType, World world) {
        super(entityType, world);
    }

    public ChaosOrbEntity(World world, LivingEntity owner) {
        super(ModEntities.CHAOS_ORB, owner, world);
    }

    public ChaosOrbEntity(World world, double x, double y, double z) {
        super(ModEntities.CHAOS_ORB, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);

            int nextEffect = this.random.nextBetween(0, this.effectsCount - 1);
            chaosEffects.get(nextEffect).accept(hitResult);

            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOS_ORB;
    }

    private void spawnPig(HitResult hitResult) {
        PigEntity chickenEntity = EntityType.PIG.create(this.getWorld());
        if (chickenEntity != null) {
            chickenEntity.setBreedingAge(0);
            chickenEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);

            this.getWorld().spawnEntity(chickenEntity);
        }
    }

    private void spawnSkeletonHorse(HitResult hitResult) {
        SkeletonHorseEntity skeletonHorseEntity = EntityType.SKELETON_HORSE.create(this.getWorld());
        skeletonHorseEntity.setTrapped(true);
        if (skeletonHorseEntity != null) {
            skeletonHorseEntity.setBreedingAge(0);
            skeletonHorseEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);

            this.getWorld().spawnEntity(skeletonHorseEntity);
        }
    }

    private void beginThunderstorm(HitResult hitResult) {
        // En el comando no se preocuparon por si es null. Si en algun momento se rompe quizas chequear esto
        if (!this.getWorld().isClient) {
            ServerWorld world = this.getServer().getOverworld();
            world.setWeather(0, UniformIntProvider.create(3600, 15600).get(world.getRandom()), true, true);
            LivingEntity entity = (LivingEntity) this.getOwner();
            if (entity != null) {
                entity.sendMessage(Text.of("Its raining"));
            }
        }
    }

    private void goDownXBlocks(HitResult hitResult) {
        Entity entity = this.getOwner();
        ServerWorld server = entity.getServer().getWorld(entity.getWorld().getRegistryKey());
        TeleportTarget teleportTarget = new TeleportTarget(server,
                new Vec3d(entity.getX(),
                        entity.getY() - 20.0,
                        entity.getZ()),
                new Vec3d(0, 0, 0),
                entity.getYaw(),
                entity.getPitch(),
                TeleportTarget.NO_OP);
        entity.teleportTo(teleportTarget);
        entity.sendMessage(Text.of(String.format("-20")));
    }

    private void randomizePlayersPositions(HitResult hitResult) {
        List<ServerPlayerEntity> players = this.getServer().getPlayerManager().getPlayerList();

        for (ServerPlayerEntity playerEntity : players) {
            ServerWorld dimension = playerEntity.getServer().getWorld(playerEntity.getWorld().getRegistryKey());
            TeleportTarget teleportTarget = new TeleportTarget(dimension,
                    new Vec3d((double)this.random.nextBetween(-2000, 2000),
                            playerEntity.getY(),
                            (double)this.random.nextBetween(-2000, 2000)),
                    new Vec3d(0, 0, 0),
                    playerEntity.getYaw(),
                    playerEntity.getPitch(),
                    TeleportTarget.NO_OP);
            playerEntity.teleportTo(teleportTarget);
            playerEntity.sendMessage(Text.of(String.format("tpeado")));
        }

    }

    private void applyBeaconEffect(HitResult hitResult) {
        LivingEntity entity = (LivingEntity) this.getOwner();
        List<RegistryEntry<StatusEffect>> pool = BeaconBlockEntity.EFFECTS_BY_LEVEL.stream().flatMap(List::stream).toList();
        long poolSize = pool.size();
        int nextEffect = this.random.nextBetween(0, (int) poolSize-1);
        StatusEffectInstance effect = new StatusEffectInstance(pool.get(nextEffect), 72000);
        if (entity != null) {
            entity.addStatusEffect(effect);
        }
    }

    private void crash(HitResult hitResult) {
//        float a = 1/0;
    }

    private void saveAndQuit(HitResult hitResult) {
    }

    private void getElytra(HitResult hitResult) {
        this.dropStack(Items.ELYTRA.getDefaultStack(), 0);
    }

    private void getMythicItem(HitResult hitResult) {
        List<ItemStack> mythicItems = new ArrayList<>();

        mythicItems.add(ModItems.LEGENDARY_PICKAXE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_ROCKET.getDefaultStack());
        mythicItems.add(ModItems.SURVIVAL_DEBUG_STICK.getDefaultStack());
        mythicItems.add(Items.DRAGON_EGG.getDefaultStack());
        mythicItems.add(Items.LIGHT.getDefaultStack());

        ItemStack bedrock = Items.BEDROCK.getDefaultStack();
        bedrock.setCount(64);
        mythicItems.add(bedrock);

        ItemStack reinforcedDeepslate = Items.REINFORCED_DEEPSLATE.getDefaultStack();
        reinforcedDeepslate.setCount(64);
        mythicItems.add(reinforcedDeepslate);

        ItemStack endPortalFrame = Items.END_PORTAL_FRAME.getDefaultStack();
        endPortalFrame.setCount(12);
        mythicItems.add(endPortalFrame);

        ItemStack buddingAmethyst = Items.BUDDING_AMETHYST.getDefaultStack();
        buddingAmethyst.setCount(64);
        mythicItems.add(buddingAmethyst);

//        List<ItemStack> spawnEggs = new ArrayList<>();

//        DynamicRegistryManager registryManager = this.getRegistryManager();
//
//        ItemGroup spawnEggsGroup = registryManager.get(RegistryKeys.ITEM_GROUP).get(Identifier.of("spawn_eggs"));
//
//        for (Item item : Registries.ITEM.stream().toList()) {
//            this.getOwner().sendMessage(Text.of(spawnEggsGroup.getDisplayName()));
//            this.getOwner().sendMessage(Text.of(item.getName()));
//            if (spawnEggsGroup.contains(item.getDefaultStack())) {
//                if (item != Items.TRIAL_SPAWNER && item != Items.SPAWNER) {
//                    this.getOwner().sendMessage(Text.of(String.valueOf(3)));
//                    ItemStack egg = item.getDefaultStack();
//                    egg.setCount(16);
//                    spawnEggs.add(egg);
//                }
//            }
//        }
//
//        this.getOwner().sendMessage(Text.of(String.valueOf(spawnEggs.size())));
//        this.getOwner().sendMessage(Text.of(String.valueOf(mythicItems.size())));

        //int nextEgg = this.random.nextBetween(0, (int) spawnEggs.size()-1);
        //mythicItems.add(spawnEggs.get(nextEgg));

        int nextItem = this.random.nextBetween(0, (int) mythicItems.size()-1);
        ItemStack reward = mythicItems.get(nextItem);
        this.dropStack(reward, 0);
    }
}
