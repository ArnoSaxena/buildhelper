package buildhelper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemExchangeWand extends ItemFillWand
{
	protected static final int FILL = 3;

	protected Map<String, BlockType> fillBlockId;

	public ItemExchangeWand(int itemId)
	{
		super(itemId);
		this.fillBlockId = new HashMap<String, BlockType>();
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_EXCHANGE_WAND));
		this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_EXCHANGE_WAND_NAMED));
		this.icons.add(2, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_EXCHANGE_WAND_CHARGED));
		this.icons.add(3, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_EXCHANGE_WAND_FILL));
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{
		String username = player.username;

		if (!world.isRemote)
		{
			if(this.usedBlockId.containsKey(username))
			{
				if(this.fillBlockId.containsKey(username))
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
							
							BlockType fillBlock = this.fillBlockId.get(username);
							this.fillBlockId.remove(username);

							for(int x = posA.x; x <= posB.x; x++)
							{
								for(int y = posA.y; y <= posB.y; y++)
								{
									for(int z = posA.z; z <= posB.z; z++)
									{
										int currentBlockId = world.getBlockId(x, y, z);
										if(currentBlockId == fillBlock.id)
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
					this.fillBlockId.put(username, clBlockType);
					this.status = FILL;
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
}
