package torojima.buildhelper.common.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemCopyPasteWand extends ItemPosWand
{
	public static final String NAME = "copypastewand_item";
	
	protected Map<ITextComponent, BlockPos> endPoint;
	
	public static final int NONE = 0;
	public static final int START_STORED = 1;
	public static final int END_STORED = 2;
	protected int status;
	
	public ItemCopyPasteWand(Properties properties)
	{
		super(properties);
		
		this.endPoint = new HashMap<ITextComponent, BlockPos>();
		
		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
				(_itemStack, _world, _livingBase) -> 
			{
				if(_itemStack.getItem() instanceof ItemCopyPasteWand)
				{
					ItemCopyPasteWand icpw = (ItemCopyPasteWand)_itemStack.getItem();
					if (icpw.getStatus() == ItemCopyPasteWand.START_STORED)
					{
						return 0.1F;
					}
					else if (icpw.getStatus() == ItemCopyPasteWand.END_STORED)
					{
						return 0.2F;
					}
				}
				return 0.0F;
			}
		);
		
	}
	
	@Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
	{
		ITextComponent username = iuc.getPlayer().getName();
		EnumActionResult returnValue = EnumActionResult.FAIL;
		
		if (!iuc.getWorld().isRemote)
		{
			if(this.isStartPointPresent(username))
			{
				if(this.isEndPointPresent(username))
				{		
					BlockPos startPos = this.popStartPos(username);
					BlockPos endPos = this.popEndPos(username);
					BlockPos copyPos = iuc.getPos();
					
					BlockPos vector = this.getVector(startPos, endPos);
					
					// ##########################
					
					BlockPos buildVector = null;
			    	switch(iuc.getFace())
			    	{
			    	case DOWN:
			    		buildVector = new BlockPos(0, 1, 0);
			    	case UP:
			    		buildVector = new BlockPos(0, -1, 0);
			    		break;
			    	case NORTH:
			    		buildVector = new BlockPos(0, 0, 1);
			    		break;
			    	case SOUTH:
			    		buildVector = new BlockPos(0, 0, -1);
			    		break;
			    	case WEST:
			    		buildVector = new BlockPos(1, 0, 0);
			    		break;
			    	case EAST:
			    		buildVector = new BlockPos(-1, 0, 0);
			    		break;
			    	default:
			    		return EnumActionResult.FAIL;
			    	}
					
					// ##########################
					
					
										
					if(this.pointsInDistanceLimit(startPos, endPos))
					{
						BlockPos posA = this.getPosAllBig(startPos, endPos);
						BlockPos posB = this.getPosAllSmall(startPos, endPos);
						
						int dX = 0;
						int dY = 0;
						int dZ = 0;
						
						// copy paste old area to new area
						
						for(int x = posA.getX(); x <= posB.getX(); x++)
						{
							dX = x - posA.getX();
							
							for(int y = posA.getY(); y <= posB.getY(); y++)
							{
								dY = y - posA.getY();
								
								for(int z = posA.getZ(); z <= posB.getZ(); z++)
								{
									dZ = z - posA.getZ();
									
									BlockPos sourcePos = new BlockPos(x,y,z);
									BlockPos targetPos = new BlockPos(dX + copyPos.getX(), dY + copyPos.getY(), dZ + copyPos.getZ());
									
									if(!this.isBedRock(iuc.getWorld(), targetPos))
									{
										iuc.getWorld().setBlockState(targetPos, iuc.getWorld().getBlockState(sourcePos), 3);
									}
								}
							}
						}
						this.status = NONE;
						returnValue = EnumActionResult.SUCCESS;
					}
				}
				else
				{
					this.putEndPos(iuc.getPos(), username);
					this.status = END_STORED;
					returnValue = EnumActionResult.SUCCESS;
				}
			}
			else
			{
				this.putStartPos(iuc.getPos(), username);
				this.status = START_STORED;
				returnValue = EnumActionResult.SUCCESS;
			}
		}
		return returnValue;
	}
	
	protected boolean isEndPointPresent(ITextComponent username)
	{
		return this.endPoint.containsKey(username);
	}
	
	protected void putEndPos(BlockPos pos, ITextComponent username)
	{
		this.endPoint.put(username, pos);
	}
	
	protected BlockPos popEndPos(ITextComponent username)
	{		
		BlockPos pos = this.endPoint.get(username);
		this.endPoint.remove(username);
		return pos;		
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	protected boolean isBedRock(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.BEDROCK;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		if(this.getStatus() == ItemCopyPasteWand.END_STORED)
		{
			return true;
		}
		return false;
	}
	
	protected BlockPos getVector(BlockPos startPos, BlockPos endPos)
	{
		int vectorX = startPos.getX() > endPos.getX() ? 1 : -1;
		int vectorY = startPos.getY() > endPos.getY() ? 1 : -1;
		int vectorZ = startPos.getZ() > endPos.getZ() ? 1 : -1;
		return new BlockPos(vectorX, vectorY, vectorZ);
	}
}
