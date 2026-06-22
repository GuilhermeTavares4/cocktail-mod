package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import tocraft.walkers.network.impl.SwapPackets;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import java.util.Arrays;
import java.util.List;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class ModEvents {
    private static final List<Holder<MobEffect>> slowedMorphs =
            Arrays.asList(
                    MorphEffects.CREEPER_MORPH,
                    MorphEffects.ZOMBIE_MORPH,
                    MorphEffects.SLIME_MORPH
            );

    private static final List<Holder<MobEffect>> jumpingMorphs =
            Arrays.asList(
                    MorphEffects.SLIME_MORPH
            );

    @SubscribeEvent
    public static void onMobEffectApplicable(MobEffectEvent.Applicable event) {
        if (!(event.getEntity() instanceof Player player)) return;

        Holder<MobEffect> newEffect = event.getEffectInstance().getEffect();
        boolean isNewEffectAMorph = newEffect.value().getDescriptionId().contains("morph");

        if (isNewEffectAMorph) {
            for (var activeEffectInstance : player.getActiveEffects()) {
                Holder<MobEffect> currentActiveEffect = activeEffectInstance.getEffect();
                boolean isActiveEffectAMorph = currentActiveEffect.value().getDescriptionId().contains("morph");

                if (isActiveEffectAMorph) {
                    player.removeEffect(currentActiveEffect);
                    cleanupMorph(player, currentActiveEffect, true);
                    break;
                }
            }
        }
    }
    @SubscribeEvent
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        if (!(event.getEntity() instanceof Player player)) return;

        var effect = event.getEffectInstance().getEffect();
        cleanupMorph(player, effect, false);
    }
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        if (event.getEntity().getType().toString().contains("slime")) {
            event.setDamageMultiplier(0);
        }
    }

    public static void cleanupMorph(Player player, Holder<MobEffect> effect, boolean isEffectOverlapping) {
        if (effect.toString().contains("morph")) {
            if (!isEffectOverlapping) {
                SwapPackets.sendSwapRequest(); // remove morph
            }
            if (effect.toString().contains("skeleton")) {
                player.getInventory().clearOrCountMatchingItems(
                stack -> {
                        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
                        if (data == null) return false;
                        CompoundTag tag = data.copyTag();
                        return tag.getBoolean("cocktailmod:skeleton_bow");
                    }, 1,
                    player.inventoryMenu.getCraftSlots()
                );
            }
            if (slowedMorphs.contains(effect)) {
                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
            }
            if (jumpingMorphs.contains(effect)) {
                player.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(0.42);
            }
        }
    }
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
        player.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(0.42);

        SwapPackets.sendSwapRequest();
    }
}
