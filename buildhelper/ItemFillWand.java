package buildhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFillWand extends ItemPosWand
{
	protected static final int NONE = 0;
	protected static final int NAMED = 1;
	protected static final int CHARGED = 2;
	
	protected class BlockType
	{
		public final int id;
		public final int meta;
		
		public BlockType(int id, int meta)
		{
			this.id = id;
			this.meta = meta;
		}
	}

	protected Map<String, BlockType> usedBlockId;
	protected int status;
	protected ArrayList<Icon> icons;

	public ItemFillWand(int itemId)
	{
		super(itemId);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTools);
		this.usedBlockId = new HashMap<String, BlockType>();
		this.status = NONE;
		this.icons = new ArrayList<Icon>();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
    	this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND));
    	this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND_NAMED));
    	this.icons.add(2, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND_CHARGED));
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int par1)
	{
    	return this.icons.get(this.status);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{
		String username = player.username;

		if (!world.isRemote)
		{
			if(this.usedBlockId.containsKey(username))
			{
				if(this.isStartPointPresent(username))
				{
					Pos startPos = this.popStartPos(username);
					Pos endPos = new Pos(posX, posY, posZ);
					if(this.pointsInDistanceLimit(startPos, endPos))
					{
						Pos posA = this.getPosAllBig(startPos, endPos);
						Pos posB = this.getPosAllSmall(startPos, endPos);

						BlockType usedBlock = this.usedBlockId.get(username);
						this.usedBlockId.remove(username);

						for(int x = posA.x; x <= posB.x; x++)
						{
							for(int y = posA.y; y <= posB.y; y++)
							{
								for(int z = posA.z; z <= posB.z; z++)
								{
									if(!this.isBedRock(world, x, y, z))
									{
										world.setBlock(x, y, z, usedBlock.id, usedBlock.meta, 2);
									}
								}
							}
						}
						this.status = NONE;
						return true;
					}
					else
					{
						this.resetWand(username);
					}
				}
				else
				{
					this.putStartPos(posX, posY, posZ, username);
					this.status = CHARGED;
					return true;
				}
			}
			else
			{
				int clBlockId = world.getBlockId(posX, posY, posZ);
				int clBlockMeta = world.getBlockMetadata(posX, posY, posZ);
				
				BlockType clBlockType = new BlockType(clBlockId, clBlockMeta);
				this.usedBlockId.put(username, clBlockType);
				this.status = NAMED;
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void resetWand(String username)
	{
		super.resetWand(username);
		this.usedBlockId.remove(username);
		this.status = NONE;
	}
	
	protected boolean isBedRock(World world, int x, int y, int z)
	{
		int clBlockId = world.getBlockId(x, y, z);
		if(clBlockId == Block.bedrock.blockID)
		{
			return true;
		}
		return false;
	}
}
