package torojima.buildhelper.common.item;

import net.minecraft.block.IGrowable;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.server.ServerWorld;
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
    public ActionResultType onItemUse(ItemUseContext iuc)
    {
        BlockState iblockstate = iuc.getWorld().getBlockState(iuc.getPos());

        BonemealEvent event = new BonemealEvent(iuc.getPlayer(), iuc.getWorld(), iuc.getPos(), iblockstate, iuc.getPlayer().getActiveItemStack());
        if (MinecraftForge.EVENT_BUS.post(event))
        {
        	return ActionResultType.PASS;
        }
        
        if (event.getResult() == Result.ALLOW)
        {
            return ActionResultType.SUCCESS;
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
                    	// grow function not deobfuscated ...
                        igrowable.func_225535_a_((ServerWorld)iuc.getWorld(), iuc.getWorld().rand, iuc.getPos(), iblockstate);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}
