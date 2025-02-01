package net.rainsky.remaining_airborne.forge;

import net.rainsky.remaining_airborne.RemainingAirborne;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@SuppressWarnings("unused")
@Mod(RemainingAirborne.MOD_ID)
@Mod.EventBusSubscriber(modid = RemainingAirborne.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RemainingAirborneForge {
    private static boolean registered = false;

    @SubscribeEvent
    public static void onRegistryEvent(RegisterEvent event) {
        if (!registered) {
            registered = true;
            RemainingAirborne.init();
        }
    }
}