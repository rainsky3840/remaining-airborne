package net.rainsky.remaining_airborne.entity;

import immersive_aircraft.entity.AircraftEntity;
import immersive_aircraft.entity.AirplaneEntity;
import immersive_aircraft.entity.misc.Trail;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rainsky.remaining_airborne.RemainingAirborne;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class ZekePlaneEntity extends AirplaneEntity
{
    public ZekePlaneEntity(EntityType<? extends AircraftEntity> entityType, Level world) {
        super(entityType, world, true);
    }

    @Override
    public Item asItem() {
        return RemainingAirborne.ZEKE_PLANE_ITEM.get();
    }

    private final List<Trail> trails = List.of(new Trail(40), new Trail(40), new Trail(40), new Trail(40));

    @Override
    public List<Trail> getTrails() {
        return trails;
    }

    protected void trail(Matrix4f transform, int index, float x, float y, float z) {
        Vector4f p0 = transformPosition(transform, x, y - 0.15f, z);
        Vector4f p1 = transformPosition(transform, x, y + 0.15f, z);

        float trailStrength = Math.max(0.0f, Math.min(1.0f, (float) (Math.sqrt(getDeltaMovement().length()) * (0.5f + (pressingInterpolatedX.getSmooth() * x) * 0.025f) - 0.25f)));
        getTrails().get(index).add(p0, p1, trailStrength);
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide) {
            if (isWithinParticleRange()) {
                Matrix4f transform = getVehicleTransform();
                Matrix3f normalTransform = getVehicleNormalTransform();

                // Trails
                trail(transform, 0, -6.75f, 1.76f, 0.9f);
                trail(transform, 1, 6.75f, 1.76f, 0.9f);

                // Smoke
                float power = getEnginePower();
                if (power > 0.05) {
                    Vector4f p = transformPosition(transform, (tickCount % 2 == 0 ? -0.6f : 0.6f), 1.75f, 1.5f);
                    Vector3f vel = transformVector(normalTransform, 0.0f, 0.0f, -0.5f);
                    Vec3 velocity = getDeltaMovement();
                    level().addParticle(ParticleTypes.LARGE_SMOKE, p.x(), p.y(), p.z(), vel.x() + velocity.x, vel.y() + velocity.y, vel.z() + velocity.z);
                }
            } else {
                trails.get(0).add(ZERO_VEC4, ZERO_VEC4, 0.0f);
                trails.get(1).add(ZERO_VEC4, ZERO_VEC4, 0.0f);
            }
        }
    }

    @Override
    public double getZoom() {
        return 9.0;
    }

//    @Override
//    public int getDefaultDyeColor() {
//        return 0xFFFFFF;
//    }
}
