/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minetweaker.mc172.recipes;

import minetweaker.api.recipes.ShapedRecipe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 *
 * @author Stan
 */
public class ShapedRecipeOre extends ShapedOreRecipe {
	private final ShapedRecipe recipe;
	
	public ShapedRecipeOre(Object[] contents, ShapedRecipe recipe) {
		super((ItemStack) recipe.getOutput().getInternal(), contents);
		
		if (recipe.isMirrored()) {
			setMirrored(true);
		}
		
		this.recipe = recipe;
	}
	
	@Override
    public boolean matches(InventoryCrafting inventory, World world) {
		return recipe.matches(MCCraftingInventory.get(inventory));
	}
	
	@Override
    public ItemStack getCraftingResult(InventoryCrafting inventory) {
		return ((ItemStack) recipe.getCraftingResult(MCCraftingInventory.get(inventory)).getInternal()).copy();
	}
}