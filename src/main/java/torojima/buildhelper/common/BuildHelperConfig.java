package torojima.buildhelper.common;

import net.minecraftforge.common.ForgeConfigSpec;

public class BuildHelperConfig
{
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	//public static final Wands WANDS = new Wands(BUILDER);
	//public static final WandRecipes WANDRECIPES = new WandRecipes(BUILDER);
	public static final ForgeConfigSpec sp﻿ec = BUILDER.build();

	public static class Wands
	{
		public final ForgeConfigSpec.ConfigValue<Boolean> SandInWaterWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> DirtWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> IronOreWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CobbleWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> StoneWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> ExchangeWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> AirWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> GapFillWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> WaterInGapsWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CubeDiggerWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> RemoveWaterWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> UniversalFillWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> ArrowWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> GrowWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> TorchWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> FillDownWandEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CopyPasteWandEnabled;
		
		public Wands(ForgeConfigSpec.Builder builder)
		{
			builder.push("Wands");
			SandInWaterWandEnabled = builder
                    .comment("Enables/Disables the Sand in Water Wand [false/true|default:true]")
                    //.translation("enable.recipe.sandinwaterwand.config")
                    .define("SandInWaterWandRecipe", true);
			DirtWandEnabled = builder
                    .comment("Enables/Disables the Dirt Wand [false/true|default:true]")
                    .define("DirtWandRecipe", true);
			IronOreWandEnabled = builder
                    .comment("Enables/Disables the Iron Ore Wand [false/true|default:true]")
                    .define("IronOreWandRecipe", true);
			CobbleWandEnabled = builder
                    .comment("Enables/Disables the Cobble Wand [false/true|default:true]")
                    .define("CobbleWandRecipe", true);
			StoneWandEnabled = builder
                    .comment("Enables/Disables the Stone Wand [false/true|default:true]")
                    .define("StoneWandRecipe", true);
			ExchangeWandEnabled = builder
                    .comment("Enables/Disables the Exchange Wand [false/true|default:true]")
                    .define("ExchangeWandRecipe", true);
			AirWandEnabled = builder
                    .comment("Enables/Disables the Air Wand [false/true|default:true]")
                    .define("AirWandRecipe", true);
			GapFillWandEnabled = builder
                    .comment("Enables/Disables the Gap Fill Wand [false/true|default:true]")
                    .define("GapFillWandRecipe", true);
			WaterInGapsWandEnabled = builder
                    .comment("Enables/Disables the Water in Gaps Wand [false/true|default:true]")
                    .define("WaterInGapsWandRecipe", true);
			CubeDiggerWandEnabled = builder
                    .comment("Enables/Disables the Cube Digger Wand [false/true|default:true]")
                    .define("CubeDiggerWandRecipe", true);
			RemoveWaterWandEnabled = builder
                    .comment("Enables/Disables the Remove Water Wand [false/true|default:true]")
                    .define("RemoveWaterWandRecipe", true);
			UniversalFillWandEnabled = builder
                    .comment("Enables/Disables the Universal Fill Wand [false/true|default:true]")
                    .define("UniversalFillWandRecipe", true);
			ArrowWandEnabled = builder
                    .comment("Enables/Disables the Arrow Wand [false/true|default:true]")
                    .define("ArrowWandRecipe", true);
			GrowWandEnabled = builder
                    .comment("Enables/Disables the Grow Wand [false/true|default:true]")
                    .define("GrowWandRecipe", true);
			TorchWandEnabled = builder
                    .comment("Enables/Disables the Torch Wand [false/true|default:true]")
                    .define("TorchWandRecipe", true);
			FillDownWandEnabled = builder
                    .comment("Enables/Disables the Fill Down Wand [false/true|default:true]")
                    .define("FillDownWandRecipe", true);
			CopyPasteWandEnabled = builder
                    .comment("Enables/Disables the Copy Paste Wand [false/true|default:true]")
                    .define("CopyPasteWandRecipe", true);
			builder.pop();
		}
	}	
	
	public static class WandRecipes
	{	
		public final ForgeConfigSpec.ConfigValue<Boolean> SandInWaterWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> DirtWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> IronOreWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CobbleWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> StoneWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> ExchangeWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> AirWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> GapFillWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> WaterInGapsWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CubeDiggerWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> RemoveWaterWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> UniversalFillWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> ArrowWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> GrowWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> TorchWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> FillDownWandRecipeEnabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> CopyPasteWandRecipeEnabled;
		
		public WandRecipes(ForgeConfigSpec.Builder builder)
		{
			builder.push("WandRecipes");
			SandInWaterWandRecipeEnabled = builder
                    .comment("Enables/Disables the Sand in Water Wand Recipe [false/true|default:true]")
                    //.translation("enable.recipe.sandinwaterwand.config")
                    .define("SandInWaterWandRecipe", true);
			DirtWandRecipeEnabled = builder
                    .comment("Enables/Disables the Dirt Wand Recipe [false/true|default:true]")
                    .define("DirtWandRecipe", true);
			IronOreWandRecipeEnabled = builder
                    .comment("Enables/Disables the Iron Ore Wand Recipe [false/true|default:true]")
                    .define("IronOreWandRecipe", true);
			CobbleWandRecipeEnabled = builder
                    .comment("Enables/Disables the Cobble Wand Recipe [false/true|default:true]")
                    .define("CobbleWandRecipe", true);
			StoneWandRecipeEnabled = builder
                    .comment("Enables/Disables the Stone Wand Recipe [false/true|default:true]")
                    .define("StoneWandRecipe", true);
			ExchangeWandRecipeEnabled = builder
                    .comment("Enables/Disables the Exchange Wand Recipe [false/true|default:true]")
                    .define("ExchangeWandRecipe", true);
			AirWandRecipeEnabled = builder
                    .comment("Enables/Disables the Air Wand Recipe [false/true|default:true]")
                    .define("AirWandRecipe", true);
			GapFillWandRecipeEnabled = builder
                    .comment("Enables/Disables the Gap Fill Wand Recipe [false/true|default:true]")
                    .define("GapFillWandRecipe", true);
			WaterInGapsWandRecipeEnabled = builder
                    .comment("Enables/Disables the Water in Gaps Wand Recipe [false/true|default:true]")
                    .define("WaterInGapsWandRecipe", true);
			CubeDiggerWandRecipeEnabled = builder
                    .comment("Enables/Disables the Cube Digger Wand Recipe [false/true|default:true]")
                    .define("CubeDiggerWandRecipe", true);
			RemoveWaterWandRecipeEnabled = builder
                    .comment("Enables/Disables the Remove Water Wand Recipe [false/true|default:true]")
                    .define("RemoveWaterWandRecipe", true);
			UniversalFillWandRecipeEnabled = builder
                    .comment("Enables/Disables the Universal Fill Wand Recipe [false/true|default:true]")
                    .define("UniversalFillWandRecipe", true);
			ArrowWandRecipeEnabled = builder
                    .comment("Enables/Disables the Arrow Wand Recipe [false/true|default:true]")
                    .define("ArrowWandRecipe", true);
			GrowWandRecipeEnabled = builder
                    .comment("Enables/Disables the Grow Wand Recipe [false/true|default:true]")
                    .define("GrowWandRecipe", true);
			TorchWandRecipeEnabled = builder
                    .comment("Enables/Disables the Torch Wand Recipe [false/true|default:true]")
                    .define("TorchWandRecipe", true);
			FillDownWandRecipeEnabled = builder
                    .comment("Enables/Disables the Fill Down Wand Recipe [false/true|default:true]")
                    .define("FillDownWandRecipe", true);
			CopyPasteWandRecipeEnabled = builder
                    .comment("Enables/Disables the Copy Paste Wand Recipe [false/true|default:true]")
                    .define("CopyPasteWandRecipe", true);
			builder.pop();
		}
	}
}
