package buildhelper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public abstract class ItemPosWand extends Item
{
	protected class Pos
	{
		public final int x;
		public final int y;
		public final int z;

		public Pos(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	protected Map<String, Pos> startPoint;
	
	public ItemPosWand(int itemId)
	{
		super(itemId);
		this.startPoint = new HashMap<String, Pos>();
	}
	
	protected boolean isStartPointPresent(String username)
	{
		return this.startPoint.containsKey(username);
	}
	
	protected void putStartPos(int posX, int posY, int posZ, String username)
	{
		this.startPoint.put(username, new Pos(posX, posY, posZ));
	}
	
	protected Pos popStartPos(String username)
	{		
		Pos pos = this.startPoint.get(username);
		this.startPoint.remove(username);
		return pos;		
	}	

	protected void resetWand(String username)
	{
		this.startPoint.remove(username);
	}
	
	protected Pos getPosAllBig(Pos posA, Pos posB)
	{
		return new Pos(
				posA.x < posB.x ? posA.x : posB.x, 
				posA.y < posB.y ? posA.y : posB.y, 
				posA.z < posB.z ? posA.z : posB.z
			);
	}
	
	protected Pos getPosAllSmall(Pos posA, Pos posB)
	{
		return new Pos(
				posA.x > posB.x ? posA.x : posB.x, 
				posA.y > posB.y ? posA.y : posB.y, 
				posA.z > posB.z ? posA.z : posB.z
			);
	}

	protected boolean pointsInDistanceLimit(Pos startPos, Pos endPos)
	{
		int distX = Math.abs(startPos.x - endPos.x);
		int distY = Math.abs(startPos.y - endPos.y);
		int distZ = Math.abs(startPos.z - endPos.z);

		if(distX <= Constants.LIMIT_FILL_X_Z
				&& distY <= Constants.LIMIT_FILL_Y
				&& distZ <= Constants.LIMIT_FILL_X_Z)
		{
			return true;
		}
		return false;
	}

}
