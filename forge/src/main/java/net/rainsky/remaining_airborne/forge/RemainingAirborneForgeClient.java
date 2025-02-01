package net.rainsky.remaining_airborne.forge;

import net.rainsky.remaining_airborne.RemainingAirborne;
import net.rainsky.remaining_airborne.RemainingAirborneClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = RemainingAirborne.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RemainingAirborneForgeClient {
    @SubscribeEvent
    public static void setup(EntityRenderersEvent.RegisterRenderers event) {
        RemainingAirborneClient.init();
    }
}
