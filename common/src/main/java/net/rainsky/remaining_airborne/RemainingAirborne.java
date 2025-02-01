package net.rainsky.remaining_airborne;

import immersive_aircraft.Items;
import immersive_aircraft.cobalt.registration.Registration;
import immersive_aircraft.item.DyeableAircraftItem;
import net.rainsky.remaining_airborne.entity.ZekePlaneEntity;
import net.rainsky.remaining_airborne.entity.CorsairPlaneEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import immersive_aircraft.WeaponRegistry;
import net.rainsky.remaining_airborne.entity.weapon.FixedCannonWeapon;
import net.rainsky.remaining_airborne.entity.weapon.BombPylonWeapon;
import net.rainsky.remaining_airborne.entity.weapon.FixedGunWeapon;
//import immersive_aircraft.entity.weapon.BulletWeapon;
import immersive_aircraft.entity.misc.WeaponMount;
import immersive_aircraft.item.WeaponItem;

import java.util.function.Supplier;

import static immersive_aircraft.Items.baseProps;

public class RemainingAirborne {
    public static final String MOD_ID = "remaining_airborne";

    public static Supplier<Item> ZEKE_PLANE_ITEM;
    public static Supplier<Item> CORSAIR_PLANE_ITEM;
    public static Supplier<Item> FIXED_CANNON_ITEM;
    public static Supplier<Item> FIXED_GUN_ITEM;
    public static Supplier<Item> BOMB_PYLON_ITEM;

    public static Supplier<EntityType<ZekePlaneEntity>> ZEKE_PLANE_ENTITY;
    public static Supplier<EntityType<CorsairPlaneEntity>> CORSAIR_PLANE_ENTITY;

    public static void init() {
        // Add to WeaponsRegistry
        WeaponRegistry.register(locate("bomb_pylon"), BombPylonWeapon::new);
        WeaponRegistry.register(locate("fixed_cannon"), FixedCannonWeapon::new);
        WeaponRegistry.register(locate("fixed_gun"), FixedGunWeapon::new);

        // Register items
        ZEKE_PLANE_ITEM = register("zeke_plane", () -> new DyeableAircraftItem(baseProps().stacksTo(1), world -> new ZekePlaneEntity(ZEKE_PLANE_ENTITY.get(), world)));
        CORSAIR_PLANE_ITEM = register("corsair_plane", () -> new DyeableAircraftItem(baseProps().stacksTo(1), world -> new CorsairPlaneEntity(CORSAIR_PLANE_ENTITY.get(), world)));
        // Register Weapons
        FIXED_CANNON_ITEM = register("fixed_cannon", () -> new WeaponItem(baseProps().stacksTo(1), WeaponMount.Type.FRONT));
        FIXED_GUN_ITEM = register("fixed_gun", () -> new WeaponItem(baseProps().stacksTo(1), WeaponMount.Type.FRONT));
        BOMB_PYLON_ITEM = register("bomb_pylon", () -> new WeaponItem(baseProps().stacksTo(1), WeaponMount.Type.DROP));

        // Register entities
        ZEKE_PLANE_ENTITY = register("zeke_plane", EntityType.Builder
                .of(ZekePlaneEntity::new, MobCategory.MISC)
                .sized(2.0f, 3.0f)
                .clientTrackingRange(12)
                .fireImmune()
        );

        CORSAIR_PLANE_ENTITY = register("corsair_plane", EntityType.Builder
                .of(CorsairPlaneEntity::new, MobCategory.MISC)
                .sized(2.25f, 3.5f)
                .clientTrackingRange(12)
                .fireImmune()
        );
    }

    static Supplier<Item> register(String name, Supplier<Item> item) {
        Supplier<Item> register = Registration.register(BuiltInRegistries.ITEM, RemainingAirborne.locate(name), item);
        Items.items.add(register);
        return register;
    }

    static <T extends Entity> Supplier<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation id = RemainingAirborne.locate(name);
        return Registration.register(BuiltInRegistries.ENTITY_TYPE, id, () -> builder.build(id.toString()));
    }

    public static ResourceLocation locate(String name) {
        return new ResourceLocation(RemainingAirborne.MOD_ID, name);
    }
}
