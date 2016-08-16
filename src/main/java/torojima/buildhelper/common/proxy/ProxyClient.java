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
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.meshDefinitions.*;

public class ProxyClient extends ProxyServer
{
	@Override
	public void registerModels()
	{
		BuildHelperMod.logger.info("registering models");
		this.registerModel(BuildHelperMod.sandWaterWand);
		this.registerModel(BuildHelperMod.fillWandDirt);
		this.registerModel(BuildHelperMod.fillWandCobble);
		this.registerModel(BuildHelperMod.fillWandStone);
		this.registerModel(BuildHelperMod.fillWandAir);
		this.registerModel(BuildHelperMod.gapFillWand);
		this.registerModel(BuildHelperMod.gapFillWaterWand);
		this.registerModel(BuildHelperMod.cubeDiggerWand);
		this.registerModel(BuildHelperMod.removeWaterWand);
		this.registerModel(BuildHelperMod.exchangeWand);
		this.registerModel(BuildHelperMod.allFillWand);
	}
	
	@Override
	public void registerModelVariants()
	{
		BuildHelperMod.logger.info("registering model variants");
		ModelBakery.registerItemVariants(BuildHelperMod.exchangeWand,
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c2", "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c3", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.exchangeWand, new ExchangeWandMesh());

		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWand,
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c2", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWand, new GapFillWandMesh());

		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWaterWand,
				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName() + "_c", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWaterWand, new GapFillWaterWandMesh());		
		
		ModelBakery.registerItemVariants(BuildHelperMod.allFillWand,
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c2", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.allFillWand, new UniversalFillWandMesh());
	}
	
	private void registerModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(
				item, 
				0, 
	    		new ModelResourceLocation(item.getRegistryName(), "inventory"));		
	}
}
