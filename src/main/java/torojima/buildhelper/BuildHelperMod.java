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
    public static final String VERSION = "1.0.4";
    public static final String UPDATEJSON = "https://github.com/ArnoSaxena/buildhelper/blob/master/bin/update.json";
    
    public static ItemSandWaterWand sandWaterWand = new ItemSandWaterWand();
    public static ItemFillWandDirt fillWandDirt = new ItemFillWandDirt();;
    public static ItemFillWandIronore fillWandIronore = new ItemFillWandIronore();
    public static ItemFillWandCobble fillWandCobble = new ItemFillWandCobble();
    public static ItemFillWandStone fillWandStone = new ItemFillWandStone();
    public static ItemExchangeWand exchangeWand = new ItemExchangeWand();
    public static ItemFillWandAir fillWandAir = new ItemFillWandAir();
    public static ItemGapFillWand gapFillWand = new ItemGapFillWand();
    public static ItemGapFillWaterWand gapFillWaterWand = new ItemGapFillWaterWand();
    public static ItemCubeDiggerWand cubeDiggerWand = new ItemCubeDiggerWand();
    public static ItemRemoveWaterWand removeWaterWand = new ItemRemoveWaterWand();
    public static ItemFillWand allFillWand = new ItemFillWand();
    public static ItemArrowWand arrowWand = new ItemArrowWand();
    public static ItemGrowWand growWand = new ItemGrowWand();
    public static ItemTorchWand torchWand = new ItemTorchWand();
    public static ItemFillDownWand fillDownWand = new ItemFillDownWand();
    
    @Instance
    public static BuildHelperMod instance;
    
    @SidedProxy(serverSide="torojima.buildhelper.common.proxy.ProxyServer", 
    		clientSide="torojima.buildhelper.common.proxy.ProxyClient")
    public static ProxyCommon proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = LogManager.getLogger(BuildHelperMod.MODID);
		proxy.registerEvents();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
