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
//import torojima.buildhelper.common.itemMeshDefinitions.ItemExchangeWandMeshDefinition;

public class ProxyClient extends ProxyServer
{
	public void registerModels()
	{
		this.registerModel(BuildHelperMod.sandWaterWand);
		this.registerModel(BuildHelperMod.fillWandDirt);
		this.registerModel(BuildHelperMod.fillWandCobble);
		this.registerModel(BuildHelperMod.fillWandStone);
		this.registerModel(BuildHelperMod.fillWandAir);
		this.registerModel(BuildHelperMod.gapFillWand);
		this.registerModel(BuildHelperMod.gapFillWaterWand);
		this.registerModel(BuildHelperMod.cubeDiggerWand);
		this.registerModel(BuildHelperMod.exchangeWand);
		this.registerModel(BuildHelperMod.removeWaterWand);
		/*
		ModelBakery.registerItemVariants(BuildHelperMod.exchangeWand, 
				new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5), "inventory"),
				new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c2", "inventory"),
				new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c3", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.exchangeWand, new ItemExchangeWandMeshDefinition());
		*/
	}
	
	private void registerModel(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				item, 
				0, 
	    		new ModelResourceLocation(BuildHelperMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));		
	}
}
