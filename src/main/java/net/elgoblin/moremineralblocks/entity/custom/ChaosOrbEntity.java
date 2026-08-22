package net.elgoblin.moremineralblocks.entity.custom;

import com.mojang.datafixers.util.Pair;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.component.ModAttachmentTypes;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.gamerule.ChaosOrbGameRules;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.ChaosOrbItem;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.terrain.SingleBlockSphereJob;
import net.elgoblin.moremineralblocks.terrain.TerrainJobsManager;
import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChaosOrbEntity extends ThrowableItemProjectile {

    private final Map<String, SimpleParticleType> particleMap = Map.of("minecraft:haste", ModParticles.CHAOS_ORB_HASTE_PARTICLE,
            "minecraft:jump_boost", ModParticles.CHAOS_ORB_JUMP_BOOST_PARTICLE,
            "minecraft:regeneration", ModParticles.CHAOS_ORB_REGENERATION_PARTICLE,
            "minecraft:resistance", ModParticles.CHAOS_ORB_RESISTANCE_PARTICLE,
            "minecraft:speed", ModParticles.CHAOS_ORB_SPEED_PARTICLE,
            "minecraft:strength", ModParticles.CHAOS_ORB_STRENGTH_PARTICLE);

    private ServerLevel level = null;
    private Entity user = null;
    private final RandomSource random = RandomSource.create();
    private String seededEvent = "none";
    private final Map<String, Pair<Integer, Integer>> eventMap = Map.ofEntries(
//            Map.entry("mobpack", new Pair<>(0,0)),
            Map.entry("mythicitem", new Pair<>(0,0)),
//            Map.entry("skeletonhorse", new Pair<>(0,2)),
            Map.entry("progression", new Pair<>(0,1)),
//            Map.entry("armor", new Pair<>(0,4)),
//            Map.entry("tools", new Pair<>(0,5)),
            Map.entry("chaos", new Pair<>(0,2)),
            Map.entry("explosion", new Pair<>(0,3)),
            Map.entry("fireexplosion", new Pair<>(0,4)),
            Map.entry("food", new Pair<>(0,5)),
            Map.entry("book", new Pair<>(0,6)),
            Map.entry("prize", new Pair<>(0, 7)),
            Map.entry("xp", new Pair<>(0, 8)),
            Map.entry("terrainsphere", new Pair<>(0,9)),

            Map.entry("smallboing", new Pair<>(1,0)),
            Map.entry("beacon", new Pair<>(1,1)),

//            Map.entry("range", new Pair<>(2, 0)),
            Map.entry("fragile", new Pair<>(2,0)),
            Map.entry("snowybodyguards", new Pair<>(2,1)),

            Map.entry("nightowl", new Pair<>(3,0)),
//
            Map.entry("storm", new Pair<>(4,0)),
            Map.entry("teleport", new Pair<>(4,1)),
//
            Map.entry("counterblink", new Pair<>(5,0)),
            Map.entry("blinking", new Pair<>(5,1)),
            Map.entry("20", new Pair<>(5,2)),
            Map.entry("scale", new Pair<>(5,3)),
            Map.entry("levitation", new Pair<>(5,4)),
//            Map.entry("adyacentblockplacing", new Pair<>(5,5)),
//
            Map.entry("help", new Pair<>(6,0))
//            Map.entry("skyblock", new Pair<>(6,1))
    );

    private boolean tunneler = false;
    private LinkedList<BlockPos> tunnelQueue = new LinkedList<>();

    private List<Consumer<HitResult>> pointChaosEffects = new ArrayList<>(List.of(
//            this::spawnMobPack,
            this::getMythicItem,
//            this::spawnSkeletonHorse,
            this::breakGameProgression,
//            this::getArmorSet,
//            this::getToolsSet,
            this::spawn5ChaosOrbs,
            this::explosion, // Ponerle timer
            this::fireExplosion, // Ponerle timer
            this::getFood,
            this::getEnchantedBook,
            this::smallPrize,
            this::xp,
            this::voidSphere
//            this::getInfiniteItem
    ));
    private List<BiConsumer<HitResult, AABB>> areaChaosEffects = new ArrayList<>(List.of(
            this::smallBoing,
            this::applyBeaconEffect
    ));
    private List<BiConsumer<HitResult, AABB>> selfAreaChaosEffects = new ArrayList<>(List.of(
//            this::increaseInteractionRange,
            this::fragile,
            this::snowyBodyguards
    ));
    private List<Consumer<HitResult>> selfChaosEffects = new ArrayList<>(List.of(
            this::nightOwl
//            this::crash
    ));
    private List<Consumer<HitResult>> globalChaosEffects = new ArrayList<>(List.of(
            this::beginThunderstorm,
            this::randomizePlayersPositions
//            this::createSkyblock
            // Skyblock se va a ir agregando en cada llamado hasta que salga una vez.
    ));
    private List<BiConsumer<HitResult, AABB>> targetsOrSelfChaosEffects = new ArrayList<>(List.of(
            this::counterBlinking,
////            this::adventureGamemode
////            this::onanaHands,
            this::blinking,
            this::moveXBlocks,
            this::changeScale,
            this::levitation
//            this::blocksPlacedOnAdyacentPositions
    ));

    private List<Consumer<HitResult>> debugChaosEffects = new ArrayList<>(List.of(
            this::debugHelp
//            this::createSkyblock
    ));

    //

    private int nextCategory() {

        int pointChaosEffectsInterval = pointChaosEffects.size();
        int areaChaosEffectsInterval = pointChaosEffectsInterval + areaChaosEffects.size();
        int selfAreaChaosEffectsInterval = areaChaosEffectsInterval + selfAreaChaosEffects.size();
        int selfChaosEffectsInterval = selfAreaChaosEffectsInterval + selfChaosEffects.size();
        int globalChaosEffectsInterval = selfChaosEffectsInterval + globalChaosEffects.size();
        int targetsOrSelfChaosEffectsInterval = globalChaosEffectsInterval + targetsOrSelfChaosEffects.size();


        int category = this.random.nextIntBetweenInclusive(0, targetsOrSelfChaosEffectsInterval-1);

        if (category < pointChaosEffectsInterval) {
            return 0;
        } else if (category < areaChaosEffectsInterval) {
            return 1;
        } else if (category < selfAreaChaosEffectsInterval) {
            return 2;
        } else if (category < selfChaosEffectsInterval) {
            return 3;
        } else if (category < globalChaosEffectsInterval) {
            return 4;
        } else { // Targets or Self Chaos Effects Interval
            return 5;
        }
    }

    public ChaosOrbEntity(final EntityType<? extends ChaosOrbEntity> type, final Level level) {
        super(type, level);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
    }

    public ChaosOrbEntity(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, mob, level, itemStack);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
        if (this.getOwner() != null) {
            user = this.getOwner();
            if (itemStack.getCustomName() != null) {
                if (user instanceof Player player && player.isCreative()) {
                    seededEvent = itemStack.getCustomName().getString().toLowerCase();
                    if (seededEvent.equalsIgnoreCase("tunneler")) {
                        tunneler = true;
                    }
                    else {
                        tunneler = !eventMap.containsKey(seededEvent) && tunneler;
                    }
                }
            }
        }

    }

    public ChaosOrbEntity(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, x, y, z, level, itemStack);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
        if (this.getOwner() != null) {
            user = this.getOwner();
            if (itemStack.getCustomName() != null) {
                if (user instanceof Player player && player.isCreative()) {
                    seededEvent = itemStack.getCustomName().getString().toLowerCase();
                    if (seededEvent.equalsIgnoreCase("tunneler")) {
                        tunneler = true;
                    }
                    else {
                        tunneler = !eventMap.containsKey(seededEvent) && tunneler;
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tunneler) {
            if (this.level().isClientSide()) { return; }

            BlockPos center = new BlockPos(new Vec3i((int) this.getX(), (int) this.getY(), (int) this.getZ()));

            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    for (int y = -5; y <= 5; y++) {
                        if (x * x + y * y + z * z > 25) {
                            continue;
                        }
                        BlockPos currentBlock = center.offset(x, y, z);
                        this.tunnelQueue.add(currentBlock);
                    }
                }
            }
            while (!tunnelQueue.isEmpty()) {
                BlockPos blockToRemove = tunnelQueue.pop();
                BlockState currentState = level.getBlockState(blockToRemove);

                MinecraftServer server = level.getServer();
                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);

                if (!currentState.isAir() && !protectorManager.isProtected(blockToRemove)) {
                    level.setBlock(blockToRemove, Blocks.AIR.defaultBlockState(), 50);
                }
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOS_ORB;
    }

    public int effectCount() {
        return this.pointChaosEffects.size() +
                this.areaChaosEffects.size() +
                this.selfAreaChaosEffects.size() +
                this.selfChaosEffects.size() +
                this.globalChaosEffects.size() +
                this.targetsOrSelfChaosEffects.size();
    }

    private ParticleOptions getParticle() {
        ItemStack item = this.getItem();
        return item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(item));
    }

    @Override
    public void handleEntityEvent(final byte id) {
    }

    @Override
    protected void onHitEntity(final EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        double knockback = random.nextIntBetweenInclusive(1,20);
        knockback = knockback * 0.4;

        if (user == null) { return; }

        double x = entity.getX() - user.getX();
        double z = entity.getZ() - user.getZ();

        double distance = Math.sqrt(x * x + z * z);
        x = x / distance;
        z = z / distance;

        if (distance > 0) {
            entity.push(x * knockback, 0.2, z * knockback);
        }
    }

    @Override
    protected void onHit(final HitResult hitResult) {
        super.onHit(hitResult);
        if (this.level().isClientSide()) { return; }

        level.broadcastEntityEvent(this, (byte) 3);

        int eventCategory = nextCategory();
        int nextEffect;
        AABB boundingBox = this.getBoundingBox();

        if (eventMap.containsKey(seededEvent)) {
            eventCategory = eventMap.get(seededEvent).getFirst();
        }

        switch (eventCategory) {
            case 0: // POINT
                // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                if (!pointChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, pointChaosEffects.size()-1);
                    }
                    pointChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 1: //AREA
                if (!areaChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, areaChaosEffects.size()-1);
                    }
                    areaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 2: //SELF AREA
                if (!selfAreaChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, selfAreaChaosEffects.size()-1);
                    }
                    selfAreaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 3: //SELF
                if (!selfChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, selfChaosEffects.size()-1);
                    }
                    selfChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 4: //GLOBAL
                if (!globalChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, globalChaosEffects.size()-1);
                    }
                    globalChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 5: //TARGET SELF
                if (!targetsOrSelfChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, targetsOrSelfChaosEffects.size()-1);
                    }
                    targetsOrSelfChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 6: // DEBUG
                nextEffect = 0;
                if (eventMap.containsKey(seededEvent)) {
                    nextEffect = eventMap.get(seededEvent).getSecond();
                }
                debugChaosEffects.get(nextEffect).accept(hitResult);
        }

        this.discard();
    }

    // EVENTS

    private void smallBoing(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Small Boing");
        List<Entity> entities = level.getEntitiesOfClass(Entity.class, boundingBox.inflate(32.0, 32.0, 32.0),
                entity -> !(entity.is(EntityTypes.ITEM_FRAME)) && !(entity.is(EntityTypes.ITEM)));

        double knockback = 10;

        double chaosOrbX = this.getX();
        double chaosOrbY = this.getY();
        double chaosOrbZ = this.getZ();
        double x;
        double y;
        double z;
        double entityX;
        double entityY;
        double entityZ;

        for (var entity : entities) {
            entityX = entity.getX();
            entityY = entity.getY();
            entityZ = entity.getZ();

            x = entityX - chaosOrbX;
            y = entityY - chaosOrbY;
            z = entityZ - chaosOrbZ;

            double distance = Math.sqrt(x*x + y*y + z*z);

            x = x / distance;
            y = y / distance;
            z = z / distance;

            if (distance > 0) {
                Vec3 force = new Vec3(x * knockback, y * 5.0, z * knockback);
                entity.push(force);

                entity.setDeltaMovement(force);
                entity.hurtMarked = true;
            }
        }
    }

    private void getMythicItem(HitResult hitResult) {
        sendMessageToUser("Mythic Item");
        List<ItemStack> mythicItems = new ArrayList<>();

        ItemStack light = Items.LIGHT.getDefaultInstance();
        light.setCount(32);
        mythicItems.add(light);

        ItemStack bedrock = Items.BEDROCK.getDefaultInstance();
        bedrock.setCount(64);
        mythicItems.add(bedrock);

        ItemStack reinforcedDeepslate = Items.REINFORCED_DEEPSLATE.getDefaultInstance();
        reinforcedDeepslate.setCount(64);
        mythicItems.add(reinforcedDeepslate);

        ItemStack endPortalFrame = Items.END_PORTAL_FRAME.getDefaultInstance();
        endPortalFrame.setCount(12);
        mythicItems.add(endPortalFrame);

        ItemStack buddingAmethyst = Items.BUDDING_AMETHYST.getDefaultInstance();
        buddingAmethyst.setCount(64);
        mythicItems.add(buddingAmethyst);

        ItemStack trialSpawner = Items.TRIAL_SPAWNER.getDefaultInstance();
        mythicItems.add(trialSpawner);

        ItemStack spawner = Items.SPAWNER.getDefaultInstance();
        mythicItems.add(spawner);

        List<ItemStack> spawnEggs = ((ChaosOrbItem) (this.getDefaultItem())).getOrCreateSpawnEggList();

        mythicItems.add(ModItems.LEGENDARY_PICKAXE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SHOVEL.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_AXE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_HOE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SWORD.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_LONGSWORD.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SPEAR.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_ROCKET.getDefaultInstance());
//        mythicItems.add(ModItems.SURVIVAL_DEBUG_STICK.getDefaultStack());
        mythicItems.add(ModItems.DIMENSIONAL_POCKET.getDefaultInstance());
        mythicItems.add(ModItems.FLASH.getDefaultInstance());

        int nextItem = this.random.nextIntBetweenInclusive(0, mythicItems.size() -1);
        ItemStack reward = mythicItems.get(nextItem);

        int nextEgg = this.random.nextIntBetweenInclusive(0, spawnEggs.size() -1);
        mythicItems.add(spawnEggs.get(nextEgg));

        if (reward.getItem() == Items.TRIAL_SPAWNER || reward.getItem() == Items.SPAWNER) {
            ItemStack newEgg = spawnEggs.get(nextEgg);
            newEgg.setCount(1);
            this.spawnAtLocation(level, newEgg, 0);
        }

        this.spawnAtLocation(level, reward, 0);
    }

    private void xp(HitResult hitResult) {
        sendMessageToUser("XP");
        ExperienceOrb.award(level, this.position(), (int) Math.pow(Math.min(random.nextIntBetweenInclusive(8,32), random.nextIntBetweenInclusive(16,32)),3));
    }

    private void getEnchantedBook(HitResult hitResult) {
        sendMessageToUser("Book");
        ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultInstance();
        Registry<Enchantment> enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        List<Holder.Reference<Enchantment>> enchantments = enchantmentRegistry.listElements().toList();
        Holder.Reference<Enchantment> enchantmentHolder = enchantments.get(this.random.nextIntBetweenInclusive(0, enchantments.size() - 1));
        Enchantment enchantment = enchantmentHolder.value();
        int enchantmentLevel = this.random.nextIntBetweenInclusive(1, enchantment.getMaxLevel());
        EnchantmentHelper.updateEnchantments(enchantedBook, mutable -> mutable.set(enchantmentHolder, enchantmentLevel));
        this.spawnAtLocation(level, enchantedBook, 0f);
    }

    private void randomizePlayersPositions(HitResult hitResult) {
        sendMessageToUser("Teleport");
        int forceTeleport = random.nextInt(20);
        if (forceTeleport == 0 && user != null && user instanceof Player player) {
            // Notar que de esta forma se aumenta la estadistica de veces usadas el item. Me parece correcto
            ModItems.CHAOS_MIRROR.use(level, player, InteractionHand.MAIN_HAND);
        }
        else {
            this.spawnAtLocation(level, ModItems.CHAOS_MIRROR.getDefaultInstance(), 0f);
        }
    }

    private void explosion(HitResult hitResult) {
        sendMessageToUser("Explosion");
        int kase = random.nextInt(19);

        if (kase > 16 && user != null) {
            level.explode(this, user.getX(), user.getY(), user.getZ(),(float) 8.0, Level.ExplosionInteraction.BLOCK);
        }
        else {
            level.explode(this, this.getX(), this.getY(), this.getZ(),(float) ((kase==0) ? 127.0 : 8.0), Level.ExplosionInteraction.BLOCK);
        }
    }

    private void fireExplosion(HitResult hitResult) {
        sendMessageToUser("Fire Explosion");
        int kase = random.nextInt(19);

        LargeFireball fireballEntity;
        if (user != null) {
            if (kase > 16) {
                fireballEntity = new LargeFireball(level, (LivingEntity) user, new Vec3(0, -1.0f, 0), 4);
                fireballEntity.setPos(user.getX(), user.getY()+2, user.getZ());
            }
            else {
                fireballEntity = new LargeFireball(level, (LivingEntity) user, new Vec3(0, -1.0f, 0), 8);
                fireballEntity.setPos(this.getX(), this.getY(), this.getZ());
            }
            level.addFreshEntity(fireballEntity);
        }
    }

    private void spawn5ChaosOrbs(HitResult hitResult) {
        sendMessageToUser("5 Chaos Orbs");
        if (user != null) {
            ItemStack chaosOrbs = new ItemStack(ModItems.CHAOS_ORB, 5);

            ChaosOrbEntity chaosOrbEntity = new ChaosOrbEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, chaosOrbs);
            chaosOrbEntity.setOwner(user);
            chaosOrbEntity.shoot( 1.0f, 2.0f, 0f, 0.5f, 0.0f);

            level.addFreshEntity(chaosOrbEntity);

            chaosOrbEntity = new ChaosOrbEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, chaosOrbs);
            chaosOrbEntity.setOwner(user);
            chaosOrbEntity.shoot( -1.0f, 2.0f, 0f, 0.5f, 0.0f);

            level.addFreshEntity(chaosOrbEntity);

            chaosOrbEntity = new ChaosOrbEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, chaosOrbs);
            chaosOrbEntity.setOwner(user);
            chaosOrbEntity.shoot( 0f, 2.0f, 1.0f, 0.5f, 0.0f);

            level.addFreshEntity(chaosOrbEntity);

            chaosOrbEntity = new ChaosOrbEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, chaosOrbs);
            chaosOrbEntity.setOwner(user);
            chaosOrbEntity.shoot( 0f, 2.0f, -1.0f, 0.5f, 0.0f);

            level.addFreshEntity(chaosOrbEntity);

            chaosOrbEntity = new ChaosOrbEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, chaosOrbs);
            chaosOrbEntity.setOwner(user);
            chaosOrbEntity.shoot( 0f, 2.0f, 0.0f, 0f, 0f);

            level.addFreshEntity(chaosOrbEntity);
        }
    }

    private void fragile(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Fragile");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(32.0, 16.0, 32.0), EntitySelector.NO_SPECTATORS);
        level.sendParticles(ModParticles.CHAOS_ORB_FRAGILE_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 3.0, 0.0, 1.0);


        for (LivingEntity entity : entities) {
            MobEffectInstance effect = new MobEffectInstance(ModEffects.FRAGILE, 6000, 0);
            if (entity != null) {
                entity.addEffect(effect);
            }
        }
    }

    private void snowyBodyguards(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Snowy Bodyguards");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(16.0, 8.0, 16.0), EntitySelector.NO_SPECTATORS);
        if (user != null) {
            entities.add((LivingEntity) user);
        }
        //level.sendParticles(ModParticles.CHAOS_ORB_FRAGILE_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 3.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            MobEffectInstance effect = new MobEffectInstance(ModEffects.SNOWY_BODYGUARDS, 24000, 0);
            if (entity != null) {
                entity.addEffect(effect);
            }
        }
    }

//    private void blocksPlacedOnAdyacentPositions(HitResult hitResult, AABB boundingBox) {
//        sendMessageToUser("Adyacent Block Placing");
//        List<ServerPlayer> entities = level.getEntitiesOfClass(ServerPlayer.class, boundingBox.inflate(16.0, 8.0, 16.0), EntitySelector.NO_SPECTATORS);
//
//        if (entities.isEmpty() && user != null) {
//            entities.add((ServerPlayer) user);
//        }
//        //level.sendParticles(ModParticles.CHAOS_ORB_FRAGILE_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 3.0, 0.0, 1.0);
//
//        for (ServerPlayer player : entities) {
//            if (player != null) {
//                player.setAttached(ModAttachmentTypes.ADYACENT_BLOCK_PLACING, random.nextLong());
//            }
//        }
//    }

    private void counterBlinking(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Counter Blink");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(32.0, 16.0, 32.0), EntitySelector.NO_SPECTATORS);
        if (entities.isEmpty() && user != null) {
            entities.add((LivingEntity) user);
        }

        level.sendParticles(ModParticles.CHAOS_ORB_COUNTER_BLINK_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            MobEffectInstance effect = new MobEffectInstance(ModEffects.COUNTER_BLINK, 9600, 0);
            if (entity != null) {
                entity.addEffect(effect);
            }
        }
    }

    private void blinking(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Blinking");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(32.0, 16.0, 32.0), EntitySelector.NO_SPECTATORS);
        if (entities.isEmpty() && user != null) {
            entities.add((LivingEntity) user);
        }
        level.sendParticles(ModParticles.CHAOS_ORB_BLINKING_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            MobEffectInstance effect = new MobEffectInstance(ModEffects.BLINKING, 1200, 0);
            if (entity != null) {
                entity.addEffect(effect);
            }
        }
    }

    private void levitation(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Levitation");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(64.0, 32.0, 64.0), EntitySelector.NO_SPECTATORS);
        if (entities.isEmpty() && user != null) {
            entities.add((LivingEntity) user);
        }
        //level.sendParticles(ModParticles.CHAOS_ORB_BLINKING_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            if (entity != null) {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.LEVITATION, 400, 0);
                entity.addEffect(effect);
            }
        }
    }

    private void nightOwl(HitResult hitResult) {
        sendMessageToUser("Night Owl");

        if (user != null && user instanceof ServerPlayer) {
            user.setAttached(ModAttachmentTypes.NIGHT_OWL, true);
        }
    }

    private void breakGameProgression(HitResult hitResult) {
        sendMessageToUser("Break Game Progression");
        List<ItemStack> rareItems = new ArrayList<>();
        rareItems.add(Items.ELYTRA.getDefaultInstance());
        rareItems.add(Items.MACE.getDefaultInstance());
        rareItems.add(Items.DRAGON_EGG.getDefaultInstance());
        rareItems.add(Items.BEACON.getDefaultInstance());
        rareItems.add(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultInstance());
        rareItems.add(Items.TOTEM_OF_UNDYING.getDefaultInstance());
        rareItems.add(Items.TRIDENT.getDefaultInstance());
        rareItems.add(Items.SHULKER_BOX.getDefaultInstance());
        rareItems.add(ModBlocks.PROTECTOR_BLOCK.asItem().getDefaultInstance());

        Registry<Enchantment> enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

        Optional<Holder.Reference<Enchantment>> mending = enchantmentRegistry.get(Enchantments.MENDING);
        if (mending.isPresent()) {
            ItemStack mendingBook = Items.ENCHANTED_BOOK.getDefaultInstance();
            EnchantmentHelper.updateEnchantments(mendingBook, mutable -> mutable.set(mending.get(), 1));
            rareItems.add(mendingBook);
        }

        Optional<Holder.Reference<Enchantment>> fortune = enchantmentRegistry.get(Enchantments.FORTUNE);
        if (fortune.isPresent()) {
            ItemStack fortuneBook = Items.ENCHANTED_BOOK.getDefaultInstance();
            EnchantmentHelper.updateEnchantments(fortuneBook, mutable -> mutable.set(fortune.get(), 3));
            rareItems.add(fortuneBook);
        }

        Optional<Holder.Reference<Enchantment>> looting = enchantmentRegistry.get(Enchantments.LOOTING);
        if (looting.isPresent()) {
            ItemStack lootingBook = Items.ENCHANTED_BOOK.getDefaultInstance();
            EnchantmentHelper.updateEnchantments(lootingBook, mutable -> mutable.set(looting.get(), 3));
            rareItems.add(lootingBook);
        }

        Optional<Holder.Reference<Enchantment>> silkTouch = enchantmentRegistry.get(Enchantments.SILK_TOUCH);
        if (silkTouch.isPresent()) {
            ItemStack silkTouchBook = Items.ENCHANTED_BOOK.getDefaultInstance();
            EnchantmentHelper.updateEnchantments(silkTouchBook, mutable -> mutable.set(silkTouch.get(), 1));
            rareItems.add(silkTouchBook);
        }

        ItemStack diamonds = Items.DIAMOND.getDefaultInstance();
        diamonds.setCount(32);
        rareItems.add(diamonds);

        ItemStack goldenCarrots = Items.GOLDEN_CARROT.getDefaultInstance();
        goldenCarrots.setCount(64);
        rareItems.add(goldenCarrots);

        ItemStack netherite = Items.NETHERITE_INGOT.getDefaultInstance();
        netherite.setCount(2);
        rareItems.add(netherite);

        ItemStack bookshelves = Items.BOOKSHELF.getDefaultInstance();
        bookshelves.setCount(15);
        rareItems.add(bookshelves);

        ItemStack goldenApples = Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance();
        goldenApples.setCount(8);
        rareItems.add(goldenApples);

        ItemStack sponges = Items.SPONGE.getDefaultInstance();
        sponges.setCount(64);
        rareItems.add(sponges);

        ItemStack iron_blocks = Items.IRON_BLOCK.getDefaultInstance();
        iron_blocks.setCount(16);
        rareItems.add(iron_blocks);

        int nextItem = this.random.nextIntBetweenInclusive(0, rareItems.size() -1);
        ItemStack reward = rareItems.get(nextItem);

        if (reward.getItem() == Items.BOOKSHELF) {
            this.spawnAtLocation(level, Items.ENCHANTING_TABLE.getDefaultInstance(), 0);
        }

        this.spawnAtLocation(level, reward, 0);
    }

    private void smallPrize(HitResult hitResult) {
        sendMessageToUser("Small Prize");
        List<ItemStack> prizes = new ArrayList<>();
        prizes.add(new ItemStack(Items.OAK_LOG, 64));
        prizes.add(new ItemStack(Items.BONE, 64));
        prizes.add(new ItemStack(Items.COAL, 64));
        prizes.add(new ItemStack(Items.STONE, 64));
        prizes.add(new ItemStack(Items.SOUL_SAND, 64));
        prizes.add(new ItemStack(Items.MAGMA_BLOCK, 64));
        prizes.add(new ItemStack(Items.OBSIDIAN, 20));
        prizes.add(new ItemStack(Items.BED.blue(), 1));
        prizes.add(new ItemStack(Items.ENDER_PEARL, 16));
        prizes.add(new ItemStack(Items.BLAZE_ROD, 12));
        prizes.add(new ItemStack(Items.POINTED_DRIPSTONE, 32));
        prizes.add(new ItemStack(Items.TURTLE_HELMET, 1));
        prizes.add(new ItemStack(Items.SCAFFOLDING, 64));

        int nextItem = this.random.nextIntBetweenInclusive(0, prizes.size() -1);
        ItemStack reward = prizes.get(nextItem);

        if (reward.getItem() == Items.POINTED_DRIPSTONE) {
            this.spawnAtLocation(level, new ItemStack(Items.LAVA_BUCKET, 1), 0);
            this.spawnAtLocation(level, new ItemStack(Items.WATER_BUCKET, 1), 0);
            this.spawnAtLocation(level, new ItemStack(Items.CAULDRON, 1), 0);
        }
        if (reward.getItem() == Items.STONE) {
            this.spawnAtLocation(level, new ItemStack(Items.STONE, 64), 0);
            this.spawnAtLocation(level, new ItemStack(Items.STONE, 64), 0);
        }

        this.spawnAtLocation(level, reward, 0);
    }

    private void getFood(HitResult hitResult) {
        sendMessageToUser("Food");
        List<Item> foodItems = new ArrayList<>();

        for (Item item : BuiltInRegistries.ITEM) {
            if (item.components().has(DataComponents.FOOD) &&
                    item.getDefaultMaxStackSize() > 1 || item == Items.SUSPICIOUS_STEW) {
                foodItems.add(item);
            }
        }

        ItemStack food = foodItems.get(this.random.nextInt(foodItems.size())).getDefaultInstance();
        if (!food.is(Items.ENCHANTED_GOLDEN_APPLE) && !food.is(Items.GOLDEN_APPLE)) {
            food.setCount(32);
        }

        if (food.is(Items.SUSPICIOUS_STEW)) {
            Registry<MobEffect> mobEffectRegistry = level.registryAccess().lookupOrThrow(Registries.MOB_EFFECT);
            List<Holder.Reference<MobEffect>> statusEffects = mobEffectRegistry.listElements().toList();

            if (!statusEffects.isEmpty()) {
                Holder.Reference<MobEffect> effectEntry = statusEffects.get(this.random.nextInt(statusEffects.size()));

                SuspiciousStewEffects.Entry chosenEffect = new SuspiciousStewEffects.Entry(effectEntry, 160);

                food.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(chosenEffect)));
                food.setCount(4);
            }
        }
        sendMessageToUser(food.toString());

        this.spawnAtLocation(level, food, 0.0F);
    }

    private void applyBeaconEffect(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Beacon Effect");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(32.0, 200.0, 32.0), EntitySelector.NO_SPECTATORS);
        List<Holder<MobEffect>> pool = BeaconBlockEntity.BEACON_EFFECTS.stream().flatMap(List::stream).toList();

        int nextEffect = this.random.nextInt(pool.size());
        int nextLevel = this.random.nextIntBetweenInclusive(0, 9); // 0 es lvl 1 y 9 es lvl 10
        Holder<MobEffect> effect = pool.get(nextEffect);
        sendMessageToUser(effect.getRegisteredName());

        SimpleParticleType effectParticle = particleMap.get(effect.getRegisteredName());
        if (effectParticle != null) {
            level.sendParticles(effectParticle, this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);
        }

        for (LivingEntity entity : entities) {
            if (entity != null) {
                MobEffectInstance effectInstance = new MobEffectInstance(effect, 24000, nextLevel);
                entity.addEffect(effectInstance);
            }
        }
    }

    private void moveXBlocks(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("-+20");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class,
                boundingBox.inflate(16.0, 8.0, 16.0),
                EntitySelector.NO_SPECTATORS
        );
        boolean goDown = this.random.nextBoolean();

        for (LivingEntity entity : entities) {
            // Ocurre cuando se muere el player antes de que se calcule la lista
            if (entity == null) { continue; }
            Vec3 targetPos = new Vec3(entity.getX(), entity.getY() + (goDown ? -20 : 20), entity.getZ());

            TeleportTransition teleportTarget = new TeleportTransition(level, targetPos, Vec3.ZERO,
                    entity.getYRot(),
                    entity.getXRot(),
                    TeleportTransition.DO_NOTHING
            );

            entity.teleport(teleportTarget);

            if (entity instanceof Player player) {
                player.sendSystemMessage(Component.literal(goDown ? "-20" : "+20"));
            }
        }
    }

    private record ScalePack(
            double scale,
            double max_hp,
            double step_height,
            double fall_height,
//            double fall_damage,
            double speed,
            double jump,
            double block_interaction_range,
            double entity_interaction_range,
            double block_break_speed) {

    }

    private static final List<ScalePack> scalePacks = new ArrayList<ScalePack>(List.of(
            new ScalePack(0.25,12, 0.6, 2, 0.08, 0.4, 3.5, 2.5, 1),
            new ScalePack(0.5, 16, 0.6, 2.5, 0.09, 0.42, 4, 3, 1),
            new ScalePack(1.5, 24, 1.126, 4.5,  0.125, 0.52, 5, 4, 2),
            new ScalePack(2, 30, 1.126, 6,  0.15, 0.62, 6.5, 4.5, 2)));

    private void applyAttributeChange(Holder<Attribute> attribute, double value, LivingEntity entity) {
        AttributeInstance currentStat = entity.getAttribute(attribute);
        if (currentStat != null) {
            currentStat.setBaseValue(value);
        }
    }

    private void changeScale(HitResult hitResult, AABB boundingBox) {
        sendMessageToUser("Scale");
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class,
                boundingBox.inflate(16.0, 8.0, 16.0),
                EntitySelector.NO_SPECTATORS
        );

        ScalePack chosenPack = scalePacks.get(this.random.nextInt(scalePacks.size()));

        if (user instanceof LivingEntity && !entities.contains(user)) {
            entities.add((LivingEntity) user);
        }

        for (LivingEntity entity : entities) {
            if (entity == null) continue;

            applyAttributeChange(Attributes.SCALE, chosenPack.scale, entity);

            if (entity instanceof Player player) {
                applyAttributeChange(Attributes.MAX_HEALTH, chosenPack.max_hp, player);
                applyAttributeChange(Attributes.STEP_HEIGHT, chosenPack.step_height, player);
                applyAttributeChange(Attributes.SAFE_FALL_DISTANCE, chosenPack.fall_height, player);
                applyAttributeChange(Attributes.MOVEMENT_SPEED, chosenPack.speed, player);
                applyAttributeChange(Attributes.JUMP_STRENGTH, chosenPack.jump, player);
                applyAttributeChange(Attributes.BLOCK_INTERACTION_RANGE, chosenPack.block_interaction_range, player);
                applyAttributeChange(Attributes.ENTITY_INTERACTION_RANGE, chosenPack.entity_interaction_range, player);
                applyAttributeChange(Attributes.MINING_EFFICIENCY, chosenPack.block_break_speed, player);

                player.getInventory().add(new ItemStack(ModItems.LA_LECHONA));
            }
        }
    }

    private void beginThunderstorm(HitResult hitResult) {
        sendMessageToUser("Storm");
        level.getServer().setWeatherParameters(0, ServerLevel.THUNDER_DURATION.sample(random), true, true);
        LightningBolt bolt = EntityTypes.LIGHTNING_BOLT.create(level, EntitySpawnReason.EVENT);
        if (bolt != null) {
            bolt.setPos(hitResult.getLocation());
            level.addFreshEntity(bolt);
        }
    }

    private void voidSphere(HitResult hitResult) {
        sendMessageToUser("Terrain Sphere");
        float randomNumber = random.nextFloat();
        while (randomNumber < 0.0000000001f) {
            randomNumber = random.nextFloat();
        }
        int radius = Math.max((int) (-1 * (5.6f * Math.log(randomNumber * 1369)/Math.log(1.375f) - 127)), 10);
        double multiplier = level.getGameRules().get(ChaosOrbGameRules.VOID_SPHERE_SIZE_MULTIPLIER);
        int fixedSize = level.getGameRules().get(ChaosOrbGameRules.VOID_SPHERE_FIXED_SIZE);
        if (multiplier > 1) {
            radius = (int) (radius * multiplier);
        }
        if (fixedSize >= 0) {
            radius = fixedSize;
        }
        BlockPos center = BlockPos.containing(hitResult.getLocation());

        if (user != null) {
            AABB boundingBox = this.getBoundingBox();
            List<Player> entities = level.getEntitiesOfClass(Player.class,
                    boundingBox.inflate(radius, radius * 0.25, radius),
                    EntitySelector.NO_SPECTATORS
            );
            Player jobArgument = null;
            if (user instanceof Player) {
                jobArgument = (Player) user;
            }
            TerrainJobsManager.TERRAIN_MANAGER.addJob(new SingleBlockSphereJob(level, jobArgument, center, radius, Blocks.AIR));

            MobEffectInstance slowFall = new MobEffectInstance(MobEffects.SLOW_FALLING, radius * 4, 0);

            for (Player player : entities) {
                player.addEffect(slowFall);
            }
        }
    }

    // DEBUG

    private void debugHelp(HitResult hitResult) {
        if (user != null && user instanceof Player player) {
            sendMessageToUser("MobPack");
            sendMessageToUser("MythicItem");
            sendMessageToUser("Progression");
            sendMessageToUser("Armor");
            sendMessageToUser("Tools");
            sendMessageToUser("Chaos");
            sendMessageToUser("TerrainSphere");
            sendMessageToUser("Explosion");
            sendMessageToUser("FireExplosion");
            sendMessageToUser("Book");
            sendMessageToUser("Prize");
            sendMessageToUser("Xp");
            sendMessageToUser("Beacon");
            sendMessageToUser("Fragile");
            sendMessageToUser("Storm");
            sendMessageToUser("Teleport");
            sendMessageToUser("CounterBlink");
            sendMessageToUser("Blinking");
            sendMessageToUser("20");
            sendMessageToUser("Scale");
            sendMessageToUser("Tunneler");
            sendMessageToUser("SmallBoing");
            sendMessageToUser("Levitation");
            sendMessageToUser("Nightowl");
            sendMessageToUser("SnowyBodyguards");
//            sendMessageToUser("AdyacentBlockPlacing");
        }
    }

    private boolean sendMessageToUser(String message) {
        if (user != null) {
            ((Player) user).sendSystemMessage(Component.literal(message));
            return true;
        }
        return false;
    }
}
