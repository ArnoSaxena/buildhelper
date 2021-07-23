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

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.core.BlockPos;

public class ItemTorchWand extends Item
{
	public static final String NAME = "torchwand_item";
	
	public ItemTorchWand(Properties properties)
	{
		super(properties);
	}
	
    @Override
    public InteractionResult useOn(UseOnContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getClickedPos();
		if(iuc.getLevel().getBlockState(pos).getBlock() == Blocks.TORCH
				|| iuc.getLevel().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
		{
			return InteractionResult.FAIL;
		}
    	
    	Boolean isWallTorch = false;
    	switch(iuc.getClickedFace())
    	{
    	case DOWN:
    		return InteractionResult.FAIL;
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
    		return InteractionResult.FAIL;
    	}
    	
    	if(iuc.getLevel().getBlockState(torchPos).getBlock() == Blocks.AIR)
    	{
    		if (isWallTorch)
    		{
    			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.WALL_TORCH
    			        .defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, iuc.getClickedFace()));
    		}
    		else
    		{
    			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.TORCH.defaultBlockState());
    		}
    		return InteractionResult.SUCCESS;
    	}
    	return InteractionResult.FAIL;
    }
}
