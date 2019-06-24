// <copyright file="ItemFillWandAir.java">
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
// <summary>Item class for the air fill wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class ItemFillWandAir extends ItemFillWand
{
	public static final String NAME = "fillwandair_item";

	public ItemFillWandAir(Properties properties)
	{
		super(properties);
	}

	@Override
    public ActionResultType onItemUse(ItemUseContext iuc)
	{
		this.usedBlocks.put(iuc.getPlayer().getName(), Blocks.AIR.getDefaultState());
		return super.onItemUse(iuc);
	}
}


