package torojima.buildhelper.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
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
    public InteractionResult useOn(UseOnContext iuc)
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
        	return InteractionResult.PASS;
        }
        
        if (event.getResult() == Result.ALLOW)
        {
            return InteractionResult.SUCCESS;
        }

        if (iblockstate.getBlock() instanceof BonemealableBlock)
        {
            BonemealableBlock igrowable = (BonemealableBlock)iblockstate.getBlock();
            
            if (igrowable.isValidBonemealTarget(iuc.getLevel(), iuc.getClickedPos(), iblockstate, iuc.getLevel().isClientSide))
            {
                if (!iuc.getLevel().isClientSide)
                {
                    if (igrowable.isBonemealSuccess(iuc.getLevel(), iuc.getLevel().random, iuc.getClickedPos(), iblockstate))
                    {
                        //igrowable.performBonemeal((ServerWorld)iuc.getLevel(), iuc.getLevel().random, iuc.getClickedPos(), iblockstate);
                        igrowable.performBonemeal((ServerLevel)iuc.getLevel(), iuc.getLevel().random, iuc.getClickedPos(), iblockstate);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }
}
