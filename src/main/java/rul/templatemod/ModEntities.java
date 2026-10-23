package rul.templatemod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModEntities {
    
    private static <T extends LivingEntity> EntityType<T> registerEntity
    (String name, EntityType.EntityFactory<T> factory, SpawnGroup spawnGroup, float width, float height) {
        Identifier id = Identifier.of(TemplateMod.MOD_ID, name);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        EntityType<T> entityType = EntityType.Builder.create(factory, spawnGroup).dimensions(width, height).build(key);
        return Registry.register(Registries.ENTITY_TYPE, key, entityType);
    }
    
    public static final EntityType<IguanaEntity> IGUANA = registerEntity("iguana", IguanaEntity::new, SpawnGroup.CREATURE, 1f, 0.15f);

    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(IGUANA, IguanaEntity.createAttributes());
    }
}