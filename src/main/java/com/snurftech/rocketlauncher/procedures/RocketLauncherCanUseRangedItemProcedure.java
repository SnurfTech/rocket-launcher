package com.snurftech.rocketlauncher.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.snurftech.rocketlauncher.init.RocketLauncherModItems;

public class RocketLauncherCanUseRangedItemProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (hasEntityInInventory(entity, new ItemStack(RocketLauncherModItems.ROCKET.get())) || entity instanceof Player _plr1 && _plr1.gameMode() == GameType.CREATIVE) {
			return true;
		}
		return false;
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}