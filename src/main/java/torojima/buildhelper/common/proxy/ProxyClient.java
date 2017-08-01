// <copyright file="ProxyClient.java">
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
// <summary>class for client specific methods</summary>

package torojima.buildhelper.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.client.ClientEventHandler;
import torojima.buildhelper.common.meshDefinitions.*;

public class ProxyClient extends ProxyCommon
{
	@Override
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
	
	@Override
	public void registerModelVariants()
	{
//		BuildHelperMod.logger.info("registering model variants");
//		ModelBakery.registerItemVariants(BuildHelperMod.exchangeWand,
//				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory"),
//				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c1", "inventory"),
//				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c2", "inventory"),
//				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c3", "inventory")
//				);
//		ModelLoader.setCustomMeshDefinition(BuildHelperMod.exchangeWand, new ExchangeWandMesh());
//
//		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWand,
//				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName(), "inventory"),
//				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c1", "inventory"),
//				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c2", "inventory")
//				);
//		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWand, new GapFillWandMesh());
//
//		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWaterWand,
//				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName(), "inventory"),
//				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName() + "_c", "inventory")
//				);
//		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWaterWand, new GapFillWaterWandMesh());		
//		
//		ModelBakery.registerItemVariants(BuildHelperMod.allFillWand,
//				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName(), "inventory"),
//				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c1", "inventory"),
//				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c2", "inventory")
//				);
//		ModelLoader.setCustomMeshDefinition(BuildHelperMod.allFillWand, new UniversalFillWandMesh());
	}
}
