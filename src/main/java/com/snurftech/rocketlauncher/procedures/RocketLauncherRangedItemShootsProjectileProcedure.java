package com.snurftech.rocketlauncher.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.snurftech.rocketlauncher.init.RocketLauncherModItems;

public class RocketLauncherRangedItemShootsProjectileProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plr0 && _plr0.gameMode() == GameType.CREATIVE)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(RocketLauncherModItems.ROCKET.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		}
	}
}