package net.rainsky.remaining_airborne;

import immersive_aircraft.ItemColors;
import immersive_aircraft.WeaponRendererRegistry;
import immersive_aircraft.client.render.entity.weaponRenderer.SimpleWeaponRenderer;
import immersive_aircraft.cobalt.registration.Registration;
import net.rainsky.remaining_airborne.client.ZekePlaneEntityRenderer;
import net.rainsky.remaining_airborne.client.CorsairPlaneEntityRenderer;

import static immersive_aircraft.ItemColors.getDyeColor;

public class RemainingAirborneClient {
    public static void init() {
        Registration.register(RemainingAirborne.ZEKE_PLANE_ENTITY.get(), ZekePlaneEntityRenderer::new);
        Registration.register(RemainingAirborne.CORSAIR_PLANE_ENTITY.get(), CorsairPlaneEntityRenderer::new);

        WeaponRendererRegistry.register(RemainingAirborne.locate("fixed_cannon"), new SimpleWeaponRenderer("fixed_cannon"));
        WeaponRendererRegistry.register(RemainingAirborne.locate("fixed_gun"), new SimpleWeaponRenderer("fixed_gun"));
        WeaponRendererRegistry.register(RemainingAirborne.locate("bomb_pylon"), new SimpleWeaponRenderer("bomb_pylon"));
    }

    static {
        ItemColors.ITEM_COLORS.put(RemainingAirborne.ZEKE_PLANE_ITEM.get(), getDyeColor(0xFFFFFF));
        ItemColors.ITEM_COLORS.put(RemainingAirborne.CORSAIR_PLANE_ITEM.get(), getDyeColor(0x3D748F));
    }
}