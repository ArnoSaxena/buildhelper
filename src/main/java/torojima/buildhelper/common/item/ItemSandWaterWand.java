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

import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSandWaterWand extends Item
{
	public static final String NAME = "sandwaterwand_item";
	
	public ItemSandWaterWand(Properties properties)
	{
		super(properties);
	}
		
	@Override
    public ActionResultType useOn(ItemUseContext iuc)
	{
		BlockPos pos = iuc.getClickedPos();
    	switch(iuc.getClickedFace())
    	{
    		case UP:
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1));
    			break;
    	
    		case NORTH:
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    			break;
    		case SOUTH:
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    			break;
    		case WEST:
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    			break;
    		case EAST:
    			this.placeSandColumn(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    			break;
    		default:
    			break;
    	}
		
		if(this.placeSandColumn(iuc.getLevel(), pos))
		{
			return ActionResultType.SUCCESS;
		}		
		return ActionResultType.FAIL;
	}
	
    private boolean placeSandColumn(World world, BlockPos pos)
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
    
    private boolean blockPosIsWater(World world, BlockPos pos)
    {
    	return world.getBlockState(pos).getMaterial() == Material.WATER;
    }
}
