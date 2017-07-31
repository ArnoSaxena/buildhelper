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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import torojima.buildhelper.common.item.*;
import torojima.buildhelper.common.proxy.*;

@Mod(modid = BuildHelperMod.MODID, 
version = BuildHelperMod.VERSION, 
updateJSON=BuildHelperMod.UPDATEJSON)
public class BuildHelperMod
{
	public static Logger logger;
	
    public static final String MODID = "buildhelper";
    public static final String VERSION = "0.5.0";
    public static final String UPDATEJSON = "https://github.com/ArnoSaxena/buildhelper/blob/master/bin/update.json";
    
    public static ItemSandWaterWand sandWaterWand;
    public static ItemFillWandDirt fillWandDirt;
    public static ItemFillWandIronore fillWandIronore;
    public static ItemFillWandCobble fillWandCobble;
    public static ItemFillWandStone fillWandStone;
    public static ItemExchangeWand exchangeWand;
    public static ItemFillWandAir fillWandAir;
    public static ItemGapFillWand gapFillWand;
    public static ItemGapFillWaterWand gapFillWaterWand;
    public static ItemCubeDiggerWand cubeDiggerWand;
    public static ItemRemoveWaterWand removeWaterWand;
    public static ItemFillWand allFillWand;
    
    @Instance
    public static BuildHelperMod instance;
    
    @SidedProxy(serverSide="torojima.buildhelper.common.proxy.ProxyServer", 
    		clientSide="torojima.buildhelper.common.proxy.ProxyClient")
    public static ProxyServer proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = LogManager.getLogger(BuildHelperMod.MODID);
    	
    	sandWaterWand = new ItemSandWaterWand();
    	fillWandDirt = new ItemFillWandDirt();
    	fillWandIronore = new ItemFillWandIronore();
    	fillWandCobble = new ItemFillWandCobble();
    	fillWandStone = new ItemFillWandStone();
		exchangeWand = new ItemExchangeWand();
		fillWandAir = new ItemFillWandAir();
		gapFillWand = new ItemGapFillWand();
		gapFillWaterWand = new ItemGapFillWaterWand();
		cubeDiggerWand = new ItemCubeDiggerWand();
		removeWaterWand = new ItemRemoveWaterWand();
		allFillWand = new ItemFillWand();
		
		proxy.registerModels();
		proxy.registerModelVariants();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
