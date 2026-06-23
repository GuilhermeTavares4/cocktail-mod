package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import com.cocktail.cocktailmod.effect.SkeletonMorph;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
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

    private static boolean isSwappingMorph = false;

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
                    isSwappingMorph = true;
                    player.removeEffect(currentActiveEffect);
                    isSwappingMorph = false;
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
        }
    }
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        if (!player.getActiveEffects().toString().contains("morph")) {
            return;
        }
        SwapPackets.sendSwapRequest();
    }

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (arrow.getOwner() instanceof Player player) {
                if (player.hasEffect(MorphEffects.SKELETON_MORPH)) {
                    arrow.getPersistentData().putBoolean("addExplosionOnHit", false); // trocar para true se quiser que a flecha exploda ao colidir com algo
                }
            }
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Level world = projectile.level();

        if (world.isClientSide()) return;

        if (projectile instanceof Arrow) {
            if (projectile.getPersistentData().getBoolean("addExplosionOnHit")) {
                world.explode(null, projectile.getX(), projectile.getY(), projectile.getZ(), 3.0f, Level.ExplosionInteraction.NONE);
                projectile.discard();
            }
        }
    }

    @SubscribeEvent
    public static void onMobEffectRemoved(MobEffectEvent.Remove event) {
        if (isSwappingMorph) return;
        if (!(event.getEntity() instanceof Player player)) return;

        var effect = event.getEffectInstance().getEffect();
        if (effect.toString().contains("morph")){
            cleanupMorph(player, effect, false);
        }
    }
}
