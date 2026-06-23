package com.cocktail.cocktailmod.ability;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import tocraft.walkers.Walkers;
import tocraft.walkers.ability.GenericShapeAbility;

public class ShootArrowAbility extends GenericShapeAbility<Skeleton> {
    public static final MapCodec<ShootArrowAbility> CODEC =
            MapCodec.unit(ShootArrowAbility::new);

    @Override
    public void onUse(Player player, Skeleton shape, Level world) {
        if (world.isClientSide) return;

        ItemStack held = player.getMainHandItem();
        CustomData data = held.get(DataComponents.CUSTOM_DATA);
        if (data == null || !data.copyTag().getBoolean("cocktailmod:skeleton_bow")) return;



        for (int i = 0; i < 7; i ++) {
            Arrow arrow = new Arrow(world, player, Items.ARROW.getDefaultInstance(), null);
            arrow.setPos(player.getX(), player.getEyeY(), player.getZ());
            float innacuracy = 7.0F;
            if (i == 0) {
                innacuracy = 1.0F;
            }
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4.0F, innacuracy);
            world.addFreshEntity(arrow);
        }
        world.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f);


    }

    @Override
    public ResourceLocation getId() { return Walkers.id("shoot_arrow"); }

    @Override
    public MapCodec<? extends GenericShapeAbility<?>> codec() { return CODEC; }

    @Override
    public Item getIcon() { return Items.ARROW; }
}