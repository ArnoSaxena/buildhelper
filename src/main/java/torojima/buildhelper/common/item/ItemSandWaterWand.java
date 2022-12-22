// <copyright file="ItemSandWaterWand.java">
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
// <summary>Item class for the fill water with sand wand item</summary>

package torojima.buildhelper.common.item;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;



public class ItemSandWaterWand extends Item
{
	public static final String NAME = "sandwaterwand_item";
	
	public ItemSandWaterWand(Properties properties)
	{
		super(properties);
	}
		
	@Override
    public InteractionResult useOn(UseOnContext iuc)
	{
		BlockPos pos = iuc.getClickedPos();
		Boolean blocksChanged = false;
    	switch(iuc.getClickedFace())
    	{
    		case UP:
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1));
    			break;
    	
    		case NORTH:
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    			break;
    		case SOUTH:
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    			break;
    		case WEST:
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    			break;
    		case EAST:
    		    blocksChanged |= this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    			break;
    		default:
    			break;
    	}
		
		//if(this.placeSandColumn(iuc.getLevel(), pos))
    	if(blocksChanged)
		{
			return InteractionResult.SUCCESS;
		}		
		return InteractionResult.FAIL;
	}
	
    private boolean placeSandColumn(Level world, BlockPos pos)
    {
    	boolean putSand = false;

    	if(this.blockPosIsWater(world, pos))
    	{
        	BlockPos posYIter = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        	while(this.blockPosIsWater(world, posYIter))
        	{
        		world.setBlockAndUpdate(posYIter, Blocks.SAND.defaultBlockState());
        		
        		putSand = true;
        		posYIter = new BlockPos(posYIter.getX(), posYIter.getY() - 1, posYIter.getZ());
        	}
    	}
    	return putSand;    	
    }
    
    private boolean blockPosIsWater(Level world, BlockPos pos)
    {
    	return world.getBlockState(pos).getMaterial() == Material.WATER;
    }
}
