package net.rainsky.remaining_airborne.fabric;

import immersive_aircraft.fabric.CommonFabric;
import net.rainsky.remaining_airborne.RemainingAirborne;
import net.fabricmc.api.ModInitializer;

public class RemainingAirborneFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Force loading the Immersive Aircraft class to have networking and registration loaded
        new CommonFabric();
        RemainingAirborne.init();
    }
}