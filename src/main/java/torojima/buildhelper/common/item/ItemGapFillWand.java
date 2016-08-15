// <copyright file="ItemGapFillWand.java">
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
// <summary>Item class for the gap fill wand item</summary>

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

public class ItemGapFillWand extends ItemExchangeWand
{
	public static final String NAME = "gapfillwand";
	
	public ItemGapFillWand()
	{
		super(false);
		this.setHasSubtypes(true);
		this.setRegistryName(ItemGapFillWand.NAME);
		this.setUnlocalizedName(ItemGapFillWand.NAME);
		GameRegistry.register(this);
	}
	
	public ItemGapFillWand(boolean register)
	{
		super(register);
		if(register)
		{
			this.setRegistryName(ItemGapFillWand.NAME);
			this.setUnlocalizedName(ItemGapFillWand.NAME);
			GameRegistry.register(this);
		}
	}

	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{		
		if(this.status == NONE)
		{
			this.fillBlock.put(playerIn.getName(), Blocks.AIR.getDefaultState());
			this.status = NAMED;
		}
		stack.setItemDamage(this.status);
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
