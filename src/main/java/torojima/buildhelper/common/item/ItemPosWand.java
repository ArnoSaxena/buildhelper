package torojima.buildhelper.common.item;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import java.util.HashMap;
import java.util.Map;
import torojima.buildhelper.common.Constants;



public abstract class ItemPosWand extends Item
{
	protected Map<String, BlockPos> startPoint;
	
	public ItemPosWand()
	{
		super();
		this.startPoint = new HashMap<String, BlockPos>();
	}
	
	protected boolean isStartPointPresent(String username)
	{
		return this.startPoint.containsKey(username);
	}
	
	protected void putStartPos(BlockPos pos, String username)
	{
		this.startPoint.put(username, pos);
	}
	
	protected BlockPos popStartPos(String username)
	{		
		BlockPos pos = this.startPoint.get(username);
		this.startPoint.remove(username);
		return pos;		
	}	

	protected void resetWand(String username)
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
