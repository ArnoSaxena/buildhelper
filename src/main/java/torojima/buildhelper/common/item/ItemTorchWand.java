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

import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;

public class ItemTorchWand extends Item
{
	public static final String NAME = "torchwand_item";
	
	public ItemTorchWand(Properties properties)
	{
		super(properties);
	}
	
    @Override
    public ActionResultType onItemUse(ItemUseContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getPos();
		if(iuc.getWorld().getBlockState(pos).getBlock() == Blocks.TORCH
				|| iuc.getWorld().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
		{
			return ActionResultType.FAIL;
		}
    	
    	Boolean isWallTorch = false;
    	switch(iuc.getFace())
    	{
    	case DOWN:
    		return ActionResultType.FAIL;
    	case UP:
    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
    		break;
    	case NORTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
    		isWallTorch = true;
    		break;
    	case SOUTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
    		isWallTorch = true;
    		break;
    	case WEST:
    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	case EAST:
    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	default:
    		return ActionResultType.FAIL;
    	}
    	
    	if(iuc.getWorld().getBlockState(torchPos).getBlock() == Blocks.AIR)
    	{
    		if (isWallTorch)
    		{
    			iuc.getWorld().setBlockState(torchPos, Blocks.WALL_TORCH.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, iuc.getFace()));
    		}
    		else
    		{
    			iuc.getWorld().setBlockState(torchPos, Blocks.TORCH.getDefaultState());
    		}
    		return ActionResultType.SUCCESS;
    	}
    	return ActionResultType.FAIL;
    }
}
