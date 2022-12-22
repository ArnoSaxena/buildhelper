package torojima.buildhelper.init;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.*;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.*;


public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BuildHelperMod.MODID);
    
    public static final RegistryObject<Item> ARROWWAND_ITEM = ITEMS.register(ItemArrowWand.NAME, () -> new ItemArrowWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    //public static final RegistryObject<Item> CUBEDIGGERWAND_ITEM = ITEMS.register(ItemCubeDiggerWand.NAME, () -> new ItemCubeDiggerWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> EXCHANGEWAND_ITEM = ITEMS.register(ItemExchangeWand.NAME, () -> new ItemExchangeWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> SANDINWATERWAND_ITEM = ITEMS.register(ItemSandWaterWand.NAME, () -> new ItemSandWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWANDDIRT_ITEM = ITEMS.register(ItemFillWandDirt.NAME, () -> new ItemFillWandDirt(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWANDIRONORE_ITEM = ITEMS.register(ItemFillWandIronore.NAME, () -> new ItemFillWandIronore(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWANDCOBBLE_ITEM = ITEMS.register(ItemFillWandCobble.NAME, () -> new ItemFillWandCobble(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWANDSTONE_ITEM = ITEMS.register(ItemFillWandStone.NAME, () -> new ItemFillWandStone(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWANDAIR_ITEM = ITEMS.register(ItemFillWandAir.NAME, () -> new ItemFillWandAir(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> GAPFILLWAND_ITEM = ITEMS.register(ItemGapFillWand.NAME, () -> new ItemGapFillWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> GAPFILLWATERWAND_ITEM = ITEMS.register(ItemGapFillWaterWand.NAME, () -> new ItemGapFillWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> REMOVEWATERWAND_ITEM = ITEMS.register(ItemRemoveWaterWand.NAME, () -> new ItemRemoveWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLWAND_ITEM = ITEMS.register(ItemFillWand.NAME, () -> new ItemFillWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> GROWWAND_ITEM = ITEMS.register(ItemGrowWand.NAME, () -> new ItemGrowWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> TORCHWAND_ITEM = ITEMS.register(ItemTorchWand.NAME, () -> new ItemTorchWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> FILLDOWNWAND_ITEM = ITEMS.register(ItemFillDownWand.NAME, () -> new ItemFillDownWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPYPASTEWAND_ITEM = ITEMS.register(ItemCopyPasteWand.NAME, () -> new ItemCopyPasteWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
}
