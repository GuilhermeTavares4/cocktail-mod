package com.cocktail.cocktailmod.potion;

import static com.cocktail.cocktailmod.potion.CocktailPotions.COCKTAIL_POTION;
import static com.cocktail.cocktailmod.potion.CocktailPotions.COCKTAIL_ZOMBIE_POTION;

import com.cocktail.cocktailmod.CocktailMod;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = CocktailMod.MODID)
public class CocktailPotionsRecipes {
    @SubscribeEvent
    public static void registerBrewing(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.AWKWARD, Items.EGG, COCKTAIL_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.ROTTEN_FLESH, COCKTAIL_ZOMBIE_POTION);
    }
}
