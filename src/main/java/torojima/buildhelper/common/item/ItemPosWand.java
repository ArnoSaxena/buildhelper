// <copyright file="ItemPosWand.java">
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
// <summary>abstract Item class for the basic position using wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import java.util.HashMap;
import java.util.Map;
import torojima.buildhelper.common.Constants;

public abstract class ItemPosWand extends Item
{
	protected Map<ITextComponent, BlockPos> startPoint;
	
	public ItemPosWand(Properties properties)
	{
		super(properties);
		this.startPoint = new HashMap<ITextComponent, BlockPos>();
	}
	
	protected boolean isStartPointPresent(ITextComponent username)
	{
		return this.startPoint.containsKey(username);
	}
	
	protected void putStartPos(BlockPos pos, ITextComponent username)
	{
		this.startPoint.put(username, pos);
	}
	
	protected BlockPos popStartPos(ITextComponent username)
	{		
		BlockPos pos = this.startPoint.get(username);
		this.startPoint.remove(username);
		return pos;		
	}	

	protected void resetWand(ITextComponent username)
	{
		this.startPoint.remove(username);
	}
	
	protected BlockPos getPosAllBig(BlockPos posA, BlockPos posB)
	{
		return new BlockPos(
				posA.getX() < posB.getX() ? posA.getX() : posB.getX(),
				posA.getY() < posB.getY() ? posA.getY() : posB.getY(), 
				posA.getZ() < posB.getZ() ? posA.getZ() : posB.getZ()
			);
	}
	
	protected BlockPos getPosAllSmall(BlockPos posA, BlockPos posB)
	{
		return new BlockPos(
				posA.getX() > posB.getX() ? posA.getX() : posB.getX(),
				posA.getY() > posB.getY() ? posA.getY() : posB.getY(), 
				posA.getZ() > posB.getZ() ? posA.getZ() : posB.getZ()
			);
	}

	protected boolean pointsInDistanceLimit(BlockPos startPos, BlockPos endPos)
	{
		int distX = Math.abs(startPos.getX() - endPos.getX());
		int distY = Math.abs(startPos.getY() - endPos.getY());
		int distZ = Math.abs(startPos.getZ() - endPos.getZ());

		if(distX <= Constants.LIMIT_FILL_X_Z
				&& distY <= Constants.LIMIT_FILL_Y
				&& distZ <= Constants.LIMIT_FILL_X_Z)
		{
			return true;
		}
		return false;
	}

}
