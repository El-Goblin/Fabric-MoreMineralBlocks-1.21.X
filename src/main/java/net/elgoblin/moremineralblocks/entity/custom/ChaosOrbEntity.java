package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.ChaosOrbItem;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.mixin.itemgroup.ItemGroupAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChaosOrbEntity extends ThrownItemEntity {

    private Random random = Random.create();
    private int effectsCount = 0;

//    private List<Consumer<HitResult>> chaosEffects = new ArrayList<>(List.of(
//            this::spawnPig,
//            this::getMythicItem,
//            this::spawnSkeletonHorse,
//            this::getElytra,
//            this::getFullDiamondArmorSet,
//            this::getFullDiamondToolsSet,
//            this::applyBeaconEffect
//    ));

    private List<Consumer<HitResult>> pointChaosEffects = new ArrayList<>(List.of(
            this::spawnPig,
            this::getMythicItem,
            this::spawnSkeletonHorse,
            this::getElytra,
            this::getFullDiamondArmorSet,
            this::getFullDiamondToolsSet
    ));
    private List<BiConsumer<HitResult, List<LivingEntity>>> areaChaosEffects = new ArrayList<>(List.of(
            this::applyBeaconEffect
    ));
    private List<Consumer<HitResult>> globalChaosEffects = new ArrayList<>(List.of(
            this::beginThunderstorm,
            this::randomizePlayersPositions
    ));
    private List<BiConsumer<HitResult, List<LivingEntity>>> selfAreaChaosEffects = new ArrayList<>(List.of(
            this::receiveDoubleDamage,
            this::goDownXBlocks
    ));;
    private List<BiConsumer<HitResult, List<LivingEntity>>> targetsOrSelfChaosEffects = new ArrayList<>(List.of(
            this::teleportisDodge,
            this::adventureGamemode
    ));
    private List<Consumer<HitResult>> selfChaosEffects = new ArrayList<>();

    public ChaosOrbEntity(EntityType<? extends ChaosOrbEntity> entityType, World world) {
        super(entityType, world);
    }

    public ChaosOrbEntity(World world, LivingEntity owner) {
        super(ModEntities.CHAOS_ORB, owner, world);
    }

    public ChaosOrbEntity(World world, double x, double y, double z) {
        super(ModEntities.CHAOS_ORB, x, y, z, world);
    }

    private int nextCategory() {
        int pointCategory = pointChaosEffects.size();
        int areaCategory = pointCategory + areaChaosEffects.size();
        int selfAreaCategory = areaCategory + selfAreaChaosEffects.size();
        int selfCategory = selfAreaCategory + selfChaosEffects.size();
        int globalCategory = selfCategory + globalChaosEffects.size();
        int targetSelfCategory = globalCategory + targetsOrSelfChaosEffects.size();

        int category = this.random.nextBetween(0, targetSelfCategory-1);

        if (category < pointCategory) {
            return 0;
        } else if (category < areaCategory) {
            return 1;
        } else if (category < selfAreaCategory) {
            return 2;
        } else if (category < selfCategory) {
            return 3;
        } else if (category < globalCategory) {
            return 4;
        }
        else {return 5;}
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

            int eventCategory = nextCategory();
            int nextEffect;

            Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

            switch (eventCategory) {
                // POINT
                case 0:
                    nextEffect = random.nextBetween(0, pointChaosEffects.size()-1);
                    pointChaosEffects.get(nextEffect).accept(hitResult);
                    break;
                //AREA
                case 1:

                    nextEffect = random.nextBetween(0, areaChaosEffects.size()-1);
                    areaChaosEffects.get(nextEffect).accept(hitResult, list);
                    break;
                //SELF AREA
                case 2:
                    if (!list.contains((LivingEntity) (this.getOwner()))){
                        list.add((LivingEntity) (this.getOwner()));
                    }
                    nextEffect = random.nextBetween(0, selfAreaChaosEffects.size()-1);
                    selfAreaChaosEffects.get(nextEffect).accept(hitResult, list);
                    break;
                //SELF
                case 3:
                    nextEffect = random.nextBetween(0, selfChaosEffects.size()-1);
                    selfChaosEffects.get(nextEffect).accept(hitResult);
                    break;
                //GLOBAL
                case 4:
                    nextEffect = random.nextBetween(0, globalChaosEffects.size()-1);
                    globalChaosEffects.get(nextEffect).accept(hitResult);
                //TARGET SELF
                case 5:
                    if (list.isEmpty()) {
                        list.add((LivingEntity) (this.getOwner()));
                    }
                    nextEffect = random.nextBetween(0, targetsOrSelfChaosEffects.size()-1);
                    targetsOrSelfChaosEffects.get(nextEffect).accept(hitResult, list);
                    break;
            }

//            int nextEffect = this.random.nextBetween(0, this.effectsCount - 1);
//            chaosEffects.get(nextEffect).accept(hitResult);

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
            LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            bolt.refreshPositionAndAngles(hitResult.getPos(),1f, 1f);
            this.getWorld().spawnEntity(bolt);
        }
    }

    private void goDownXBlocks(HitResult hitResult, List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
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

    }

    private void randomizePlayersPositions(HitResult hitResult) {
        List<ServerPlayerEntity> players = this.getServer().getPlayerManager().getPlayerList();

        for (ServerPlayerEntity playerEntity : players) {
            ServerWorld dimension = playerEntity.getServer().getWorld(playerEntity.getWorld().getRegistryKey());
            TeleportTarget teleportTarget = new TeleportTarget(dimension,
                    new Vec3d((double)this.random.nextBetween((int) (playerEntity.getX()-2000.0D), (int) (playerEntity.getX()+2000.0D)),
                            playerEntity.getY(),
                            (double)this.random.nextBetween((int) (playerEntity.getZ()-2000.0D), (int) (playerEntity.getZ()+2000.0D))),
                    new Vec3d(0, 0, 0),
                    playerEntity.getYaw(),
                    playerEntity.getPitch(),
                    TeleportTarget.NO_OP);
            playerEntity.teleportTo(teleportTarget);
            playerEntity.sendMessage(Text.of(String.format("tpeado")));
        }

    }

    private void applyBeaconEffect(HitResult hitResult, List<LivingEntity> entities) {
        List<RegistryEntry<StatusEffect>> pool = BeaconBlockEntity.EFFECTS_BY_LEVEL.stream().flatMap(List::stream).toList();
        long poolSize = pool.size();
        int nextEffect = this.random.nextBetween(0, (int) poolSize-1);
        int nextLevel = this.random.nextBetween(0, 9);

        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(pool.get(nextEffect), 72000, nextLevel);
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

    private void get5ChaosOrbs(HitResult hitResult) {
        ItemStack chaosOrbs = ModItems.CHAOS_ORB.getDefaultStack();
        chaosOrbs.setCount(5);
        this.dropStack(chaosOrbs, 0);
    }

    private void getFullDiamondToolsSet(HitResult hitResult) {
        int material = random.nextBetween(0, 4);

        switch (material) {
            case 0:
                this.dropStack(Items.WOODEN_AXE.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_HOE.getDefaultStack(), 0);
                break;
            case 1:
                this.dropStack(Items.STONE_AXE.getDefaultStack(), 0);
                this.dropStack(Items.STONE_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.STONE_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.STONE_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.STONE_HOE.getDefaultStack(), 0);
                break;
            case 2:
                this.dropStack(Items.IRON_AXE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.IRON_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.IRON_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_HOE.getDefaultStack(), 0);
                break;
            case 3:
                this.dropStack(Items.GOLDEN_AXE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_HOE.getDefaultStack(), 0);
                break;
            case 4:
                this.dropStack(Items.DIAMOND_AXE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_HOE.getDefaultStack(), 0);
                break;
        }
    }

    private void getFullDiamondArmorSet(HitResult hitResult) {
        int material = random.nextBetween(0, 4);

        switch (material) {
            case 0:
                this.dropStack(Items.LEATHER_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_BOOTS.getDefaultStack(), 0);
                break;
            case 1:
                this.dropStack(Items.IRON_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.IRON_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.IRON_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.IRON_BOOTS.getDefaultStack(), 0);
                break;
            case 2:
                this.dropStack(Items.GOLDEN_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_BOOTS.getDefaultStack(), 0);
                break;
            case 3:
                this.dropStack(Items.DIAMOND_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_BOOTS.getDefaultStack(), 0);
                break;
            case 4:
                this.dropStack(Items.CHAINMAIL_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_BOOTS.getDefaultStack(), 0);
                break;
        }
    }

    private void receiveDoubleDamage(HitResult hitResult, List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.DOUBLE_DAMAGE_TAKEN, 72000, 1);
            entity.addStatusEffect(effect);
        }
    }

    private void teleportisDodge(HitResult hitResult, List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.TELEPORTIS_DODGE, 72000, 1);
            entity.addStatusEffect(effect);
        }
    }

    private void adventureGamemode(HitResult hitResult, List<LivingEntity> entities) {
        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.TELEPORTIS_DODGE, 72000, 1);
            entity.addStatusEffect(effect);
        }
    }


    private void getMythicItem(HitResult hitResult) {
        List<ItemStack> mythicItems = new ArrayList<>();

        mythicItems.add(ModItems.LEGENDARY_PICKAXE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_SHOVEL.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_AXE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_HOE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_SWORD.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_ROCKET.getDefaultStack());
        mythicItems.add(ModItems.SURVIVAL_DEBUG_STICK.getDefaultStack());
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

        ItemStack trialSpawner = Items.TRIAL_SPAWNER.getDefaultStack();
        mythicItems.add(trialSpawner);

        List<ItemStack> spawnEggs = ((ChaosOrbItem) (this.getDefaultItem())).getOrCreateSpawnEggList();

        int nextEgg = this.random.nextBetween(0, (int) spawnEggs.size()-1);
        mythicItems.add(spawnEggs.get(nextEgg));

        int nextItem = this.random.nextBetween(0, (int) mythicItems.size()-1);
        ItemStack reward = mythicItems.get(nextItem);

        if (reward.getItem() == Items.TRIAL_SPAWNER) {
            ItemStack newEgg = spawnEggs.get(nextEgg);
            newEgg.setCount(1);
            this.dropStack(newEgg, 0);
        }
        this.dropStack(reward, 0);
    }
}
