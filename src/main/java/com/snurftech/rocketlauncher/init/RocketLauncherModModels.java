/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.snurftech.rocketlauncher.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.snurftech.rocketlauncher.client.model.ModelRocket_Launcher;
import com.snurftech.rocketlauncher.client.model.ModelRocket;

@EventBusSubscriber(Dist.CLIENT)
public class RocketLauncherModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelRocket.LAYER_LOCATION, ModelRocket::createBodyLayer);
		event.registerLayerDefinition(ModelRocket_Launcher.LAYER_LOCATION, ModelRocket_Launcher::createBodyLayer);
	}
}