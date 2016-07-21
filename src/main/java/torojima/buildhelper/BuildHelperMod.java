// <copyright file="BuildHelperMod.java">
// Copyright (c) 2016 All Right Reserved, http://buildhelper.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2016-07-21</date>
// <summary>Base mod class for the Buildhelper mod</summary>

package torojima.buildhelper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import torojima.buildhelper.common.item.*;
import torojima.buildhelper.common.proxy.*;

@Mod(modid = BuildHelperMod.MODID, 
version = BuildHelperMod.VERSION, 
updateJSON=BuildHelperMod.UPDATEJSON)
public class BuildHelperMod
{
    public static final String MODID = "buildhelper";
    public static final String VERSION = "0.3.0";
    public static final String UPDATEJSON = "https://github.com/ArnoSaxena/buildhelper/blob/master/bin/update.json";
    
    public static ItemSandWaterWand sandWaterWand;
    public static ItemFillWandDirt fillWandDirt;
    public static ItemFillWandCobble fillWandCobble;
    public static ItemFillWandStone fillWandStone;
    public static ItemExchangeWand exchangeWand;
    public static ItemFillWandAir fillWandAir;
    public static ItemGapFillWand gapFillWand;
    public static ItemGapFillWaterWand gapFillWaterWand;
    public static ItemCubeDiggerWand cubeDiggerWand;
    
    @Instance
    public static BuildHelperMod instance;
    
    @SidedProxy(serverSide="torojima.buildhelper.common.proxy.ProxyServer", 
    		clientSide="torojima.buildhelper.common.proxy.ProxyClient")
    public static ProxyServer proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	sandWaterWand = new ItemSandWaterWand();
		GameRegistry.addRecipe(new ItemStack(sandWaterWand, 1), new Object[]
		{
			"  S", 
			" - ", 
			"N  ", 
			Character.valueOf('S'), Blocks.SAND, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('W'), Items.WATER_BUCKET
		});
		
    	fillWandDirt = new ItemFillWandDirt();
		GameRegistry.addRecipe(new ItemStack(fillWandDirt, 1), new Object[]
		{
			"  D", 
			" - ",
			"N  ", 
			Character.valueOf('D'), Blocks.DIRT, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
    	fillWandCobble = new ItemFillWandCobble();
		GameRegistry.addRecipe(new ItemStack(fillWandCobble, 1), new Object[]
		{
			"  C", 
			" - ",
			"N  ", 
			Character.valueOf('D'), Blocks.COBBLESTONE, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
    	fillWandStone = new ItemFillWandStone();
		GameRegistry.addRecipe(new ItemStack(fillWandStone, 1), new Object[]
		{
			"  S", 
			" - ",
			"N  ", 
			Character.valueOf('D'), Blocks.STONE, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
		exchangeWand = new ItemExchangeWand();
		GameRegistry.addRecipe(new ItemStack(exchangeWand, 1), new Object[]
		{
			"  D", 
			" - ", 
			"E  ", 
			Character.valueOf('D'), Items.DIAMOND, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('E'), Items.EMERALD
		});
		
		fillWandAir = new ItemFillWandAir();
		GameRegistry.addRecipe(new ItemStack(fillWandAir, 1), new Object[]
		{
			"  S", 
			" - ", 
			"N  ", 
			Character.valueOf('S'), Items.IRON_SHOVEL, 
			Character.valueOf('-'), Items.STICK,
			Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
		gapFillWand = new ItemGapFillWand();
		GameRegistry.addRecipe(new ItemStack(gapFillWand, 1), new Object[]
		{
				"  E", 
				" - ", 
				"N  ", 
				Character.valueOf('E'), Items.EMERALD, 
				Character.valueOf('-'), Items.STICK,
				Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
		gapFillWaterWand = new ItemGapFillWaterWand();
		GameRegistry.addRecipe(new ItemStack(gapFillWaterWand, 1), new Object[]
		{
				"  E", 
				" - ", 
				"N  ", 
				Character.valueOf('E'), Items.WATER_BUCKET, 
				Character.valueOf('-'), Items.STICK,
				Character.valueOf('N'), Items.GOLD_NUGGET
		});
		
		cubeDiggerWand = new ItemCubeDiggerWand();
		GameRegistry.addRecipe(new ItemStack(cubeDiggerWand, 1), new Object[]
		{
				"  S", 
			    " - ", 
			    "N  ", 
			    Character.valueOf('S'), Items.DIAMOND_SHOVEL, 
			    Character.valueOf('-'), Items.STICK,
				Character.valueOf('N'), Items.GOLD_NUGGET
		});
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerModels();
    }
}
