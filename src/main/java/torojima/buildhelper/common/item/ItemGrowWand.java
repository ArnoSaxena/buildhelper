package torojima.buildhelper.common.item;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;

public class ItemGrowWand extends Item
{
	public static final String NAME = "growwand_item";

	public ItemGrowWand(Properties properties)
	{
		super(properties);
	}
	
    @Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
    {
        IBlockState iblockstate = iuc.getWorld().getBlockState(iuc.getPos());

        BonemealEvent event = new BonemealEvent(iuc.getPlayer(), iuc.getWorld(), iuc.getPos(), iblockstate, iuc.getPlayer().getActiveItemStack());
        if (MinecraftForge.EVENT_BUS.post(event))
        {
        	return EnumActionResult.PASS;
        }
        
        if (event.getResult() == Result.ALLOW)
        {
            return EnumActionResult.SUCCESS;
        }

        if (iblockstate.getBlock() instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if (igrowable.canGrow(iuc.getWorld(), iuc.getPos(), iblockstate, iuc.getWorld().isRemote))
            {
                if (!iuc.getWorld().isRemote)
                {
                    if (igrowable.canUseBonemeal(iuc.getWorld(), iuc.getWorld().rand, iuc.getPos(), iblockstate))
                    {
                        igrowable.grow(iuc.getWorld(), iuc.getWorld().rand, iuc.getPos(), iblockstate);
                    }
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}
