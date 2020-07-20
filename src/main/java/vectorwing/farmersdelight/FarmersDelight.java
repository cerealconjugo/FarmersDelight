package vectorwing.farmersdelight;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import vectorwing.farmersdelight.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.init.ModBlocks;
import vectorwing.farmersdelight.init.ModItems;
import vectorwing.farmersdelight.init.ModContainerTypes;
import vectorwing.farmersdelight.init.TileEntityInit;
import vectorwing.farmersdelight.setup.ClientEventHandler;
import vectorwing.farmersdelight.setup.CommonEventHandler;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FarmersDelight.MODID)
@Mod.EventBusSubscriber(modid = FarmersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FarmersDelight
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "farmersdelight";

    public static final CreativeTab ITEM_GROUP = new CreativeTab("farmersdelight");

    public FarmersDelight() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonEventHandler::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventHandler::init);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(IRecipeSerializer.class, this::registerRecipeSerializers);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        TileEntityInit.TILES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerRecipeSerializers (RegistryEvent.Register<IRecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(FarmersDelight.MODID, "cooking"), CookingPotRecipe.TYPE);
        event.getRegistry().register(CookingPotRecipe.SERIALIZER);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

//        ModBlocks.BLOCKS.getEntries().stream()
//                .filter(block -> !(block.get() instanceof CropsBlock))
//                .map(RegistryObject::get).forEach(block -> {
//            final Item.Properties properties = new Item.Properties().group(FarmersDelight.ITEM_GROUP);
//            final BlockItem blockItem = new BlockItem(block, properties);
//            blockItem.setRegistryName(block.getRegistryName());
//            registry.register(blockItem);
//        });
    }
}
