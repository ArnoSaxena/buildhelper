package buildhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public abstract class ItemShiftWand extends ItemPosWand
{
	protected static final int NONE = 0;
	protected static final int CHARGED_START = 1;
	protected static final int CHARGED_FULL = 2;

	protected int status;
	protected ArrayList<Icon> icons;
	protected Map<String, Pos> endPoint;

	public ItemShiftWand(int itemId)
	{
		super(itemId);
		this.endPoint = new HashMap<String, Pos>();
	}
	
	protected abstract void moveBlock(World world, Pos pos);
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{
		String username = player.username;		
		if (!world.isRemote)
		{
			if(this.isStartPointPresent(username))
			{
				if(this.endPoint.containsKey(username))
				{
					this.resetWand(username);
					this.endPoint.remove(username);
					this.status = NONE;
				}
				else
				{
					this.endPoint.put(username, new Pos(posX, posY, posZ));
					this.status = CHARGED_FULL;
				}
			}
			else
			{
				this.putStartPos(posX, posY, posZ, username);
				this.status = CHARGED_START;
				return true;
			}
		}		
		return true;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(this.isStartPointPresent(player.username) && this.endPoint.containsKey(player.username))
		{
			Pos startPos = this.popStartPos(player.username);
			Pos endPos = this.endPoint.get(player.username);
			
			if(this.pointsInDistanceLimit(startPos, endPos))
			{
				Pos posA = this.getPosAllBig(startPos, endPos);
				Pos posB = this.getPosAllSmall(startPos, endPos);

				for(int x = posA.x; x <= posB.x; x++)
				{
					for(int y = posA.y; y <= posB.y; y++)
					{
						for(int z = posA.z; z <= posB.z; z++)
						{
							Pos blockPos = new Pos(x,y,z);
							this.moveBlock(world, blockPos);
						}
					}
				}
			}
			else
			{
				this.resetWand(player.username);
			}
		}
		
		return itemStack;
	}
	
	@Override
	protected void resetWand(String username)
	{
		super.resetWand(username);
		this.status = NONE;
	}


}
