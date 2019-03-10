// <copyright file="ItemCubeDiggerWand.java">
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
// <summary>Item class for the cube digger wand item</summary>

package torojima.buildhelper.common.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCubeDiggerWand extends Item
{
	public static final String NAME = "cubediggerwand_item";

	public ItemCubeDiggerWand(Properties properties)
	{
		super(properties);
	}
	
    @Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
    {    	
    	BlockPos startPos;
    	BlockPos endPos;
    	BlockPos pos = iuc.getPos();
    	World worldIn = iuc.getWorld();
    	
    	// side: 0 => bottom;  1 => top;  2 => z-;  3 => z+;  4 => x-;  5 => x+
    	switch(iuc.getFace())
    	{
    	case DOWN:
    		startPos = new BlockPos(pos.getX() -1, pos.getY(),    pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +2, pos.getZ() +1);
    		break;
    	case UP:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -2, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY(),    pos.getZ() +1);
    		break;
    	case NORTH:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -1, pos.getZ());
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +1, pos.getZ() +2);
    		break;
    	case SOUTH:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -1, pos.getZ() -2);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +1, pos.getZ());
    		break;
    	case WEST:
    		startPos = new BlockPos(pos.getX(),    pos.getY() -1, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +2, pos.getY() +1, pos.getZ() +1);
    		break;
    	case EAST:
    		startPos = new BlockPos(pos.getX() -2, pos.getY() -1, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX(),    pos.getY() +1, pos.getZ() +1);
    		break;
    	default:
            return EnumActionResult.FAIL;
    	}
    	    	
		for(int x = startPos.getX(); x <= endPos.getX(); x++)
		{
			for(int y = startPos.getY(); y <= endPos.getY(); y++)
			{
				for(int z = startPos.getZ(); z <= endPos.getZ(); z++)
				{
					BlockPos currentPos = new BlockPos(x,y,z);
					Block currentBlock = worldIn.getBlockState(currentPos).getBlock();
					
			    	if(this.isDiggableBlock(currentBlock)
			    			&& !this.isBedRock(worldIn, currentPos))
			    	{
			    		worldIn.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 3);
			    	}
				}
			}
		}
        return EnumActionResult.PASS;
    }
    
	protected boolean isBedRock(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.BEDROCK;
	}
    
    private boolean isDiggableBlock(Block block)
    {
    	ArrayList<Block> diggableBlocks = new ArrayList<Block>();
    	diggableBlocks.add(Blocks.DIRT);
    	diggableBlocks.add(Blocks.GRASS_BLOCK);
    	diggableBlocks.add(Blocks.COARSE_DIRT);
    	diggableBlocks.add(Blocks.GRAVEL);
    	diggableBlocks.add(Blocks.STONE);
    	diggableBlocks.add(Blocks.ANDESITE);
    	diggableBlocks.add(Blocks.DIORITE);
    	diggableBlocks.add(Blocks.GRANITE);
    	diggableBlocks.add(Blocks.COBBLESTONE);
    	diggableBlocks.add(Blocks.SAND);
    	diggableBlocks.add(Blocks.SANDSTONE);
    	diggableBlocks.add(Blocks.GRASS);
    	diggableBlocks.add(Blocks.END_STONE);
    	diggableBlocks.add(Blocks.NETHERRACK);
    	diggableBlocks.add(Blocks.RED_SANDSTONE);
    	diggableBlocks.add(Blocks.RED_SAND);
    	
    	return diggableBlocks.contains(block);
    }
}
