/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.snurftech.rocketlauncher.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import com.snurftech.rocketlauncher.RocketLauncherMod;

@EventBusSubscriber
public class RocketLauncherModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RocketLauncherMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(RocketLauncherModItems.ROCKET_LAUNCHER.get());
			tabData.accept(RocketLauncherModItems.ROCKET.get());
		}
	}
}