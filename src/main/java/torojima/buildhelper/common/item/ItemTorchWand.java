// <copyright file="ItemTorchWand.java">
// Copyright (c) 2018 All Right Reserved, http://buildhelper.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2018-03-30</date>
// <summary>Item class for the torch wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;

public class ItemTorchWand extends Item
{
	public static final String NAME = "torchwand_item";
	
	public ItemTorchWand(Properties properties)
	{
		super(properties);
	}
	
    @Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getPos();
    	
    	Boolean torchBelow = false;
    	switch(iuc.getFace())
    	{
    	case DOWN:
    		return EnumActionResult.FAIL;
    	case UP:
    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
    		if(iuc.getWorld().getBlockState(pos).getBlock() == Blocks.TORCH)
    		{
    			torchBelow = true;
    		}
    		break;
    	case NORTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
    		break;
    	case SOUTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
    		break;
    	case WEST:
    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
    		break;
    	case EAST:
    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
    		break;
    	default:
    		return EnumActionResult.FAIL;
    	}
    	if(iuc.getWorld().getBlockState(torchPos).getBlock() == Blocks.AIR 
    			&& !torchBelow)
    	{
    		iuc.getWorld().setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(Blocks.TORCH.getDefaultState(), iuc.getFace(), iuc.getWorld().getBlockState(pos), iuc.getWorld(), torchPos, pos, iuc.getPlayer().getActiveHand()));
    		return EnumActionResult.SUCCESS;
    	}
    	return EnumActionResult.FAIL;
    }
}
