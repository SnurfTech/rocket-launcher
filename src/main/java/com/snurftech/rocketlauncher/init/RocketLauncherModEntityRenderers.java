/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.snurftech.rocketlauncher.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.snurftech.rocketlauncher.client.renderer.RocketProjectileRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class RocketLauncherModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RocketLauncherModEntities.ROCKET_PROJECTILE.get(), RocketProjectileRenderer::new);
	}
}