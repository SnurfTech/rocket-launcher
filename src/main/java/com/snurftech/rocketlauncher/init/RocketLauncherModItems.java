/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.snurftech.rocketlauncher.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;

import java.util.function.Function;

import com.snurftech.rocketlauncher.item.RocketLauncherItem;
import com.snurftech.rocketlauncher.item.RocketItem;
import com.snurftech.rocketlauncher.RocketLauncherMod;

public class RocketLauncherModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(RocketLauncherMod.MODID);
	public static final DeferredItem<Item> ROCKET_LAUNCHER;
	public static final DeferredItem<Item> ROCKET;
	static {
		ROCKET_LAUNCHER = register("rocket_launcher", RocketLauncherItem::new);
		ROCKET = register("rocket", RocketItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}
}