package torojima.buildhelper.common.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemExchangeWand extends ItemFillWand
{
	protected static final int FILL = 3;
	
	public static final String NAME = "exchangewand";

	protected Map<String, Block> fillBlock;

	public ItemExchangeWand()
	{
		super();
		this.setRegistryName(ItemExchangeWand.NAME);
		this.setUnlocalizedName(ItemExchangeWand.NAME);
		this.setMaxStackSize(1);
		GameRegistry.register(this);		
		this.fillBlock = new HashMap<String, Block>();
	}
	
	public ItemExchangeWand(boolean register)
	{
		super();
		if(register)
		{
			this.setRegistryName(ItemExchangeWand.NAME);
			this.setUnlocalizedName(ItemExchangeWand.NAME);
			this.setMaxStackSize(1);
			GameRegistry.register(this);
		}
		this.fillBlock = new HashMap<String, Block>();			
	}

	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		String username = playerIn.getName();

		if (!worldIn.isRemote)
		{
			if(this.usedBlock.containsKey(username))
			{
				if(this.fillBlock.containsKey(username))
				{
					if(this.isStartPointPresent(username))
					{
						BlockPos startPos = this.popStartPos(username);
						BlockPos endPos = pos;
						if(this.pointsInDistanceLimit(startPos, endPos))
						{
							BlockPos posA = this.getPosAllBig(startPos, endPos);
							BlockPos posB = this.getPosAllSmall(startPos, endPos);

							Block usedBlock = this.usedBlock.get(username);
							this.usedBlock.remove(username);
							
							Block fillBlock = this.fillBlock.get(username);
							this.fillBlock.remove(username);

							for(int x = posA.getX(); x <= posB.getX(); x++)
							{
								for(int y = posA.getY(); y <= posB.getY(); y++)
								{
									for(int z = posA.getZ(); z <= posB.getZ(); z++)
									{
										BlockPos changePos = new BlockPos(x,y,z);
										
										if(worldIn.getBlockState(changePos).getBlock() == fillBlock
												&& !this.isBedRock(worldIn, changePos))
										{
											worldIn.setBlockState(changePos, usedBlock.getDefaultState(), 3);
										}
									}
								}
							}
							this.status = NONE;
							return EnumActionResult.SUCCESS;
						}
						else
						{
							this.resetWand(username);
						}
					}
					else
					{
						this.putStartPos(pos, username);
						this.status = CHARGED;
						return EnumActionResult.SUCCESS;
					}
				}
				else
				{
					this.fillBlock.put(username, worldIn.getBlockState(pos).getBlock());
					this.status = FILL;
					return EnumActionResult.SUCCESS;
				}
			}
			else
			{
				this.usedBlock.put(username, worldIn.getBlockState(pos).getBlock());
				this.status = NAMED;
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}
}
