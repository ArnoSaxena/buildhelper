// <copyright file="UniversalFillWandMesh.java">
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
// <summary>Dynamically changing model for universal fill wand</summary>

package torojima.buildhelper.common.meshDefinitions;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.ItemFillWand;;

public class UniversalFillWandMesh implements ItemMeshDefinition
{
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack)
	{
	if(stack.getItem() == BuildHelperMod.allFillWand)
	{
		ItemFillWand ifw = (ItemFillWand)stack.getItem();
		ResourceLocation registryName = BuildHelperMod.gapFillWand.getRegistryName();
		switch (ifw.getStatus())
		{
		case ItemFillWand.NONE:
			return new ModelResourceLocation(registryName, "inventory");
		case ItemFillWand.NAMED:
			return new ModelResourceLocation(registryName + "_c1", "inventory");
		case ItemFillWand.CHARGED:
			return new ModelResourceLocation(registryName + "_c2", "inventory");
		default:
			return new ModelResourceLocation(registryName, "inventory");
		}
	}
	return null;	
	}
}
