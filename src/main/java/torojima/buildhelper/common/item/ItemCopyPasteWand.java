package torojima.buildhelper.common.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemCopyPasteWand extends ItemPosWand
{
	public static final String NAME = "copypastewand_item";
	
	protected Map<ITextComponent, BlockPos> endPoint;
	protected Map<ITextComponent, Direction> sourceFace;
	
	public static final int NONE = 0;
	public static final int START_STORED = 1;
	public static final int END_STORED = 2;
	protected int status;
	
	protected final Random rand = new Random();
	
	public ItemCopyPasteWand(Properties properties)
	{
		super(properties);
		
		this.endPoint = new HashMap<ITextComponent, BlockPos>();
		this.sourceFace = new HashMap<ITextComponent, Direction>();
		
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (worldIn.isRemote)
		{
			this.resetWand(playerIn.getName());
			this.endPoint.remove(playerIn.getName());
			this.sourceFace.remove(playerIn.getName());
			this.status = NONE;
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}	
	
	@Override
    public ActionResultType onItemUse(ItemUseContext iuc)
	{
		ITextComponent username = iuc.getPlayer().getName();
		ActionResultType returnValue = ActionResultType.FAIL;
		
		if (!iuc.getWorld().isRemote)
		{
			if(this.isStartPointPresent(username))
			{
				if(this.isEndPointPresent(username))
				{
					if (iuc.getFace() == Direction.DOWN
							|| iuc.getFace() == Direction.UP)
					{
						return returnValue;
					}
					
					BlockPos startPos = this.popStartPos(username);
					BlockPos protoEndPos = this.popEndPos(username);
					Direction sourceFace = this.popSourceFace(username);
					BlockPos copyPos = iuc.getPos();
															
					if(this.pointsInDistanceLimit(startPos, protoEndPos))
					{
						BlockPos sourceEinheitsVector = new BlockPos(
								startPos.getX() > protoEndPos.getX() ? -1 : 1,
								startPos.getY() > protoEndPos.getY() ? -1 : 1,
								startPos.getZ() > protoEndPos.getZ() ? -1 : 1
								);
						
						// so for/next loops will cover the correct volume
						BlockPos endPos = new BlockPos (
								protoEndPos.getX() + sourceEinheitsVector.getX(),
								protoEndPos.getY() + sourceEinheitsVector.getY(),
								protoEndPos.getZ() + sourceEinheitsVector.getZ()
								);
				    	
						int dX = 0;
						int dY = 0;
						int dZ = 0;
						
						for (int x = startPos.getX(); x != endPos.getX(); x += sourceEinheitsVector.getX())
						{
							dX = Math.abs(x - startPos.getX());
							for (int y = startPos.getY(); y != endPos.getY(); y += sourceEinheitsVector.getY())
							{
								dY = Math.abs(y - startPos.getY());
								for (int z = startPos.getZ(); z != endPos.getZ(); z += sourceEinheitsVector.getZ())
								{
									dZ = Math.abs(z - startPos.getZ());
									BlockPos sourcePos = new BlockPos(x,y,z);
									BlockPos sourceVector = new BlockPos(dX,dY,dZ);
									BlockPos targetPos = this.getTargetPos(copyPos, iuc.getFace(), sourcePos, sourceVector, sourceEinheitsVector, sourceFace);
									if(!this.isBedRock(iuc.getWorld(), targetPos))
									{
										iuc.getWorld().setBlockState(targetPos, iuc.getWorld().getBlockState(sourcePos), 3);
									}
								}
								
							}
							
						}
						
						this.status = NONE;
						returnValue = ActionResultType.SUCCESS;
					}
				}
				else
				{
					this.putEndPos(iuc.getPos(), username);
					this.status = END_STORED;
					returnValue = ActionResultType.SUCCESS;
				}
			}
			else
			{
				if (iuc.getFace() == Direction.DOWN
						|| iuc.getFace() == Direction.UP)
				{
					return returnValue;
				}
				this.putStartPos(iuc.getPos(), username);
				this.putSourceFace(iuc.getFace(), username);
				this.status = START_STORED;
				returnValue = ActionResultType.SUCCESS;
			}
		}
		return returnValue;
	}

	private BlockPos getTargetPos(BlockPos copyPos, Direction copyFace, 
			BlockPos sourcePos, BlockPos sourceVector, BlockPos sourceEinheitsVector, 
			Direction sourceFace)
	{
		switch (copyFace)
		{
    	case NORTH:
    		switch (sourceFace)
    		{
        	case NORTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ()
        				);
        	case SOUTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1
        				);
        	case WEST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX()
        				);
        	case EAST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX() * -1
        				);
        	case DOWN:
        	case UP:
        	default:
        		return null;
    		}
    	case SOUTH:
    		switch (sourceFace)
    		{
        	case NORTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1
        				);
        	case SOUTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ()
        				);
        	case WEST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX() * -1
        				);
        	case EAST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX()
        				);
        	case DOWN:
        	case UP:
        	default:
        		return null;
    		}
    	case WEST:
    		switch (sourceFace)
    		{
        	case NORTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX() * -1
        				);
        	case SOUTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX()
        				);
        	case WEST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ()
        				);
        	case EAST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1
        				);
        	case DOWN:
        	case UP:
        	default:
        		return null;
    		}
    	case EAST:
    		switch (sourceFace)
    		{
        	case NORTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX()
        				);
        	case SOUTH:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getZ() * sourceEinheitsVector.getZ(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getX() * sourceEinheitsVector.getX() * -1
        				);        		
        	case WEST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX() * -1,
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ() * -1
        				);
        	case EAST:
        		return new BlockPos(
        				copyPos.getX() + sourceVector.getX() * sourceEinheitsVector.getX(),
        				copyPos.getY() + sourceVector.getY() * sourceEinheitsVector.getY(),
        				copyPos.getZ() + sourceVector.getZ() * sourceEinheitsVector.getZ()
        				);
        	case DOWN:
        	case UP:
        	default:
        		return null;
    		}
    	case DOWN:
    	case UP:
    	default:
    		return null;
		}
	}

	protected void putSourceFace(Direction sourceFace, ITextComponent username)
	{
		this.sourceFace.put(username, sourceFace);
	}
	
	protected Direction popSourceFace(ITextComponent username)
	{
		Direction face = this.sourceFace.get(username);
		this.sourceFace.remove(username);
		return face;
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
}
