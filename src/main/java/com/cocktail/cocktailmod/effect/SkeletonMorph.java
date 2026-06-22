package com.cocktail.cocktailmod.effect;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import tocraft.walkers.api.PlayerShape;

public class SkeletonMorph extends MobEffect {
    public SkeletonMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            Skeleton skeleton = new Skeleton(EntityType.SKELETON, player.serverLevel());
            PlayerShape.updateShapes(player, skeleton);

            ItemStack bow = new ItemStack(Items.BOW);
            bow.set(DataComponents.CUSTOM_NAME, Component.literal("Skeleton Morph's Bow"));
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("cocktailmod:skeleton_bow", true);
            bow.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            player.getInventory().add(bow);
        }
    }
}