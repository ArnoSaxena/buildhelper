// <copyright file="ItemRemoveWaterWand.java">
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
// <summary>Item class for the remove water wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRemoveWaterWand extends ItemGapFillWand
{
	public static final String NAME = "removewaterwand";
	
	public ItemRemoveWaterWand()
	{
		super(false);
		this.setRegistryName(ItemRemoveWaterWand.NAME);
		this.setUnlocalizedName(ItemRemoveWaterWand.NAME);
		GameRegistry.register(this);
	}

	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(this.status == NONE)
		{
			this.fillBlocks.put(playerIn.getName(), Blocks.WATER.getDefaultState());
			this.usedBlocks.put(playerIn.getName(), Blocks.AIR.getDefaultState());
			this.status = FILL;
		}
		return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
