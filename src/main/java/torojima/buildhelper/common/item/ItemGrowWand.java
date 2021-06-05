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
    public ActionResultType useOn(ItemUseContext iuc)
    {
        BlockState iblockstate = iuc.getLevel().getBlockState(iuc.getClickedPos());

        BonemealEvent event = 
                new BonemealEvent(
                        iuc.getPlayer(), 
                        iuc.getLevel(), 
                        iuc.getClickedPos(), 
                        iblockstate, 
                        iuc.getPlayer().getMainHandItem()
                        );
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
            
            if (igrowable.isValidBonemealTarget(iuc.getLevel(), iuc.getClickedPos(), iblockstate, iuc.getLevel().isClientSide))
            {
                if (!iuc.getLevel().isClientSide)
                {
                    if (igrowable.isBonemealSuccess(iuc.getLevel(), iuc.getLevel().random, iuc.getClickedPos(), iblockstate))
                    {
                        igrowable.performBonemeal((ServerWorld)iuc.getLevel(), iuc.getLevel().random, iuc.getClickedPos(), iblockstate);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}
