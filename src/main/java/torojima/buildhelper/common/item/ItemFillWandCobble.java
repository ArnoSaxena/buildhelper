// <copyright file="ItemFillWandCobble.java">
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
// <summary>Item class for the cobble fill wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class ItemFillWandCobble extends ItemFillWand
{
	public static final String NAME = "fillwandcobble_item";
	
	public ItemFillWandCobble(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public ActionResultType useOn(ItemUseContext iuc)
	{
		this.usedBlocks.put(iuc.getPlayer().getName(), Blocks.COBBLESTONE.defaultBlockState());
		return super.useOn(iuc);
	}
}
