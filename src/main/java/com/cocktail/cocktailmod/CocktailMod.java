package com.cocktail.cocktailmod;

import com.cocktail.cocktailmod.potion.CocktailPotions;
import com.cocktail.cocktailmod.effect.MorphEffects;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tocraft.walkers.api.PlayerShape;

@Mod(CocktailMod.MODID)
public class CocktailMod {
    public static final String MODID = "cocktailmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CocktailMod(IEventBus modEventBus) {
        CocktailPotions.register(modEventBus);
        MorphEffects.register(modEventBus);
    }

//    @SubscribeEvent
//    public void onDrink(LivingEntityUseItemEvent.Finish event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return; // garante que foi consumido por um player
//
//        ItemStack consumed = event.getItem();
//        if (consumed.getItem() != Items.POTION) return; // garante que o item consumido foi uma poção
//        pdateShapes(player, zombie);
//        var contents = consumed.get(DataComponents.POTION_CONTENTS);
//        if (contents == null) return;
//        contents.potion().ifPresent(holder -> {
//            if (holder.is(COCKTAIL_ZOMBIE_POTION)) {
//                Zombie zombie = new Zombie(EntityType.ZOMBIE, player.serverLevel());
//                PlayerShape.updateShapes(player, zombie);
//            }
//        });
//    }
}