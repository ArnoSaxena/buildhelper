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

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;

public class ItemGapFillWand extends ItemExchangeWand
{
	public static final String NAME = "gapfillwand_item";

	public ItemGapFillWand(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
	{		
		if(this.status == NONE)
		{
			this.fillBlocks.put(iuc.getPlayer().getName(), Blocks.AIR.getDefaultState());
			this.status = NAMED;
		}
		//iuc.getPlayer().getHeldItem(iuc.getPlayer().getActiveHand()).setDamage(this.status);
		return super.onItemUse(iuc);
	}
}
