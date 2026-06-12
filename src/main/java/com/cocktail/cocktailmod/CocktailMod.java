package com.cocktail.cocktailmod;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponents;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.alchemy.PotionContents;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;

import net.neoforged.neoforge.common.NeoForge;

import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import tocraft.walkers.api.PlayerShape;

@Mod(CocktailMod.MODID)
public class CocktailMod {
    public static final String MODID = "cocktailmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MODID);

    public static final DeferredHolder<Potion, Potion> COCKTAIL_POTION =
            POTIONS.register("cocktail",
                    () -> new Potion()
            );

    public CocktailMod(IEventBus modEventBus) {
        POTIONS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBrewing(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                Items.EGG,
                COCKTAIL_POTION
        );
    }

    @SubscribeEvent
    public void onDrink(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return; // garante que foi consumido por um player

        ItemStack consumed = event.getItem();
        if (consumed.getItem() != Items.POTION) return; // garante que o item consumido foi uma poção

        var contents = consumed.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return;
        contents.potion().ifPresent(holder -> {
            if (holder.is(COCKTAIL_POTION)) {
                Zombie zombie = new Zombie(EntityType.ZOMBIE, player.serverLevel());
                PlayerShape.updateShapes(player, zombie);
            }
        });
    }
}