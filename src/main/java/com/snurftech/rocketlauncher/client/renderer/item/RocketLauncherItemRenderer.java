package com.snurftech.rocketlauncher.client.renderer.item;

import org.joml.Vector3f;

import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.EntityModel;

import java.util.function.Function;
import java.util.Set;
import java.util.Optional;
import java.util.Map;

import com.snurftech.rocketlauncher.client.model.ModelRocket_Launcher;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

@EventBusSubscriber(Dist.CLIENT)
public class RocketLauncherItemRenderer implements SpecialModelRenderer<ItemStack> {
	@SubscribeEvent
	public static void registerItemRenderers(RegisterSpecialModelRendererEvent event) {
		event.register(ResourceLocation.parse("rocket_launcher:rocket_launcher"), RocketLauncherItemRenderer.Unbaked.MAP_CODEC);
	}

	private static final Map<Integer, Function<EntityModelSet, RocketLauncherItemRenderer>> MODELS = Map
			.ofEntries(Map.entry(-1, modelSet -> new RocketLauncherItemRenderer(new ModelRocket_Launcher(modelSet.bakeLayer(ModelRocket_Launcher.LAYER_LOCATION)), ResourceLocation.parse("rocket_launcher:textures/item/rocket_launcher_texture.png"))));
	private final EntityModel<LivingEntityRenderState> model;
	private final ResourceLocation texture;
	private final LivingEntityRenderState renderState;
	private final long start;

	private RocketLauncherItemRenderer(EntityModel<LivingEntityRenderState> model, ResourceLocation texture) {
		this.model = model;
		this.texture = texture;
		this.renderState = new LivingEntityRenderState();
		this.start = System.currentTimeMillis();
	}

	@Override
	public void render(ItemStack itemstack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean glint) {
		poseStack.pushPose();
		poseStack.translate(0.5, isInventory(displayContext) ? 1.5 : 2, 0.5);
		poseStack.scale(1, -1, displayContext == ItemDisplayContext.GUI ? -1 : 1);
		VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(bufferSource, model.renderType(texture), false, glint);
		renderState.ageInTicks = (System.currentTimeMillis() - start) / 50.0f;
		model.setupAnim(renderState);
		model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	@Override
	public ItemStack extractArgument(ItemStack itemstack) {
		return itemstack;
	}

	@Override
	public void getExtents(Set<Vector3f> extentsSet) {
		PoseStack posestack = new PoseStack();
		this.model.root().getExtentsForGui(posestack, extentsSet);
	}

	private static boolean isInventory(ItemDisplayContext type) {
		return type == ItemDisplayContext.GUI || type == ItemDisplayContext.FIXED;
	}

	public record Unbaked(int index) implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<RocketLauncherItemRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder
				.mapCodec(instance -> instance.group(ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("index").xmap(opt -> opt.orElse(-1), i -> i == -1 ? Optional.empty() : Optional.of(i)).forGetter(RocketLauncherItemRenderer.Unbaked::index))
						.apply(instance, RocketLauncherItemRenderer.Unbaked::new));

		@Override
		public MapCodec<RocketLauncherItemRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
			return RocketLauncherItemRenderer.MODELS.get(index).apply(modelSet);
		}
	}
}