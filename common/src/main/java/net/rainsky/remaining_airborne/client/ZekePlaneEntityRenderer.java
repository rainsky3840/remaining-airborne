package net.rainsky.remaining_airborne.client;

import immersive_aircraft.client.render.entity.renderer.AircraftEntityRenderer;
import immersive_aircraft.client.render.entity.renderer.utils.ModelPartRenderHandler;
import immersive_aircraft.entity.AircraftEntity;
import net.rainsky.remaining_airborne.RemainingAirborne;
import net.rainsky.remaining_airborne.entity.ZekePlaneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ZekePlaneEntityRenderer extends AircraftEntityRenderer<ZekePlaneEntity> {
    private static final ResourceLocation ID = RemainingAirborne.locate("zeke_plane");

    private final ModelPartRenderHandler<ZekePlaneEntity> model = new ModelPartRenderHandler<ZekePlaneEntity>()
            .add("dyed_body", (model, object, vertexConsumerProvider, entity, matrixStack, light, time, modelPartRenderer) ->
                    renderDyed(model, object, vertexConsumerProvider, entity, matrixStack, light, time, false, false));
//            .add("dyed_body_highlights", (model, object, vertexConsumerProvider, entity, matrixStack, light, time, modelPartRenderer) ->
//                    renderDyed(model, object, vertexConsumerProvider, entity, matrixStack, light, time, true, false));

    @Override
    protected ResourceLocation getModelId() {
        return ID;
    }

    public ZekePlaneEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected ModelPartRenderHandler<ZekePlaneEntity> getModel(AircraftEntity entity) {
        return model;
    }
}
