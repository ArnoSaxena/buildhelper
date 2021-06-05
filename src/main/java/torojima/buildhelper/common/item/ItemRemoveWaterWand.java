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

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class ItemRemoveWaterWand extends ItemGapFillWand
{
	public static final String NAME = "removewaterwand_item";
	
	public ItemRemoveWaterWand(Properties properties)
	{
		super(properties);
	}

	@Override
    public ActionResultType useOn(ItemUseContext iuc)
	{
		if(this.status == NONE)
		{
			this.fillBlocks.put(iuc.getPlayer().getName(), Blocks.WATER.defaultBlockState());
			this.usedBlocks.put(iuc.getPlayer().getName(), Blocks.AIR.defaultBlockState());
			this.status = FILL;
		}
		return super.useOn(iuc);
	}
}
