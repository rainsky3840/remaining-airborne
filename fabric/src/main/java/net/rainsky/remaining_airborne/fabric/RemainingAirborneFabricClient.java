package net.rainsky.remaining_airborne.fabric;

import net.rainsky.remaining_airborne.RemainingAirborneClient;
import net.fabricmc.api.ClientModInitializer;

public class RemainingAirborneFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RemainingAirborneClient.init();
    }
}
