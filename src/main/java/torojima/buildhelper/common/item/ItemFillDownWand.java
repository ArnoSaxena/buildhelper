// <copyright file="ItemFillDownWand.java">
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
// <date>2018-07-14</date>
// <summary>Item class for the fill down wand item</summary>


package torojima.buildhelper.common.item;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFillDownWand extends Item
{
	public static final String NAME = "filldownwand_item";
		
	public ItemFillDownWand(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
	{
		BlockPos pos = iuc.getPos();
		
		IBlockState fillBlock = iuc.getWorld().getBlockState(pos);
		
		if (fillBlock.getBlock() == Blocks.BEDROCK)
		{
			fillBlock = Blocks.SAND.getDefaultState();
		}
		
    	switch(iuc.getFace())
    	{
    		case UP:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    	
    		case NORTH:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			break;
    		case SOUTH:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    		case WEST:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		case EAST:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		default:
    			break;
    	}
    	
		if(this.placeDownColumns(iuc.getWorld(), pos, fillBlock))
		{
			return EnumActionResult.SUCCESS;
		}		
		return EnumActionResult.FAIL;
	}
	
    private boolean placeDownColumns(World world, BlockPos pos, IBlockState fillBlock)
    {
    	boolean putFillBlock = false;

    	if(this.blockPosIsTargetBlock(world, pos))
    	{
        	BlockPos posYIter = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        	while(this.blockPosIsTargetBlock(world, posYIter))
        	{
        		world.setBlockState(posYIter, fillBlock);
        		putFillBlock = true;
        		posYIter = new BlockPos(posYIter.getX(), posYIter.getY() - 1, posYIter.getZ());
        	}
    	}
    	return putFillBlock;    	
    }
    
    private boolean blockPosIsTargetBlock(World world, BlockPos pos)
    {
    	return world.getBlockState(pos).getMaterial() == Material.AIR
    			|| world.getBlockState(pos).getMaterial() == Material.WATER
    			|| world.getBlockState(pos).getMaterial() == Material.LAVA
    	    	|| world.getBlockState(pos).getMaterial() == Material.LEAVES
    	    	|| world.getBlockState(pos).getBlock() == Blocks.GRASS
    	    	|| world.getBlockState(pos).getBlock() == Blocks.TALL_GRASS
    			|| world.getBlockState(pos).getMaterial() == Material.PLANTS;
    }
}
