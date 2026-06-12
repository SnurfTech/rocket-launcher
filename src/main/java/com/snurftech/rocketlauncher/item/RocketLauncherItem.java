package com.snurftech.rocketlauncher.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import com.snurftech.rocketlauncher.procedures.RocketLauncherRangedItemShootsProjectileProcedure;
import com.snurftech.rocketlauncher.procedures.RocketLauncherCanUseRangedItemProcedure;
import com.snurftech.rocketlauncher.entity.RocketProjectileEntity;

public class RocketLauncherItem extends Item {
	public RocketLauncherItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
		return 72000;
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = InteractionResult.FAIL;
		if (RocketLauncherCanUseRangedItemProcedure.execute(entity))
			if (entity.getAbilities().instabuild || findAmmo(entity) != ItemStack.EMPTY) {
				ar = InteractionResult.CONSUME;
				entity.startUsingItem(hand);
			}
		return ar;
	}

	@Override
	public boolean releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			ItemStack stack = findAmmo(player);
			if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				RocketProjectileEntity projectile = RocketProjectileEntity.shoot(world, entity, world.getRandom());
				if (player.getAbilities().instabuild) {
					projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				} else {
					if (stack.isDamageableItem()) {
						if (world instanceof ServerLevel serverLevel)
							stack.hurtAndBreak(1, serverLevel, player, _stkprov -> {
							});
					} else {
						stack.shrink(1);
					}
				}
				RocketLauncherRangedItemShootsProjectileProcedure.execute(entity);
			}
		}
		return super.releaseUsing(itemstack, world, entity, time);
	}

	private ItemStack findAmmo(Player player) {
		return new ItemStack(RocketProjectileEntity.PROJECTILE_ITEM.getItem());
	}
}