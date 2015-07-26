package minetweaker.mods.gregtech.machines;

import gregtech.api.GregTech_API;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Access point to Plate Bender recipes.
 * 
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.PlateBender")
@ModOnly("gregtech")
public class PlateBender {
	/**
	 * Adds a plate bender recipe.
	 * 
	 * @param output recipe output
	 * @param input recipe input
	 * @param durationTicks bending time, in ticks
	 * @param euPerTick eu consumption per tick
	 */
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack input, int durationTicks, int euPerTick) {
		MineTweakerAPI.apply(new AddRecipeAction(output, input, durationTicks, euPerTick));
	}
	
	// ######################
	// ### Action classes ###
	// ######################
	
	private static class AddRecipeAction extends OneWayAction {
		private final IItemStack output;
		private final IItemStack input;
		private final int duration;
		private final int euPerTick;
		
		public AddRecipeAction(IItemStack output, IItemStack input, int duration, int euPerTick) {
			this.output = output;
			this.input = input;
			this.duration = duration;
			this.euPerTick = euPerTick;
		}

		@Override
		public void apply() {
			GregTech_API.sRecipeAdder.addBenderRecipe(
					MineTweakerMC.getItemStack(input),
					MineTweakerMC.getItemStack(output),
					duration,
					euPerTick);
		}

		@Override
		public String describe() {
			return "Adding plate bender recipe for " + output;
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 79 * hash + (this.output != null ? this.output.hashCode() : 0);
			hash = 79 * hash + (this.input != null ? this.input.hashCode() : 0);
			hash = 79 * hash + this.duration;
			hash = 79 * hash + this.euPerTick;
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final AddRecipeAction other = (AddRecipeAction) obj;
			if (this.output != other.output && (this.output == null || !this.output.equals(other.output))) {
				return false;
			}
			if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
				return false;
			}
			if (this.duration != other.duration) {
				return false;
			}
			if (this.euPerTick != other.euPerTick) {
				return false;
			}
			return true;
		}
	}
}
