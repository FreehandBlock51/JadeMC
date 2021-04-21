package net.dc.jademc;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.dc.jademc.block.EnderiteOre;
import net.dc.jademc.block.GreenCrystalOre;
import net.dc.jademc.block.JadeBlock;
import net.dc.jademc.block.Package;
import net.dc.jademc.block.TransportHub;
import net.dc.jademc.item.ImpureCrystalItem;
import net.dc.jademc.item.JadeItem;
import net.dc.jademc.item.JadeShardItem;
import net.dc.jademc.item.RingOfFlightItem;
import net.dc.jademc.item.armor.EnderiteBoots;
import net.dc.jademc.item.armor.EnderiteChestplate;
import net.dc.jademc.item.armor.EnderiteHelmet;
import net.dc.jademc.item.armor.EnderiteLeggings;
import net.dc.jademc.item.armor.JadeBoots;
import net.dc.jademc.item.armor.JadeChestplate;
import net.dc.jademc.item.armor.JadeHelmet;
import net.dc.jademc.item.armor.JadeLeggings;
import net.dc.jademc.item.tool.EnderiteAxeItem;
import net.dc.jademc.item.tool.EnderiteHoeItem;
import net.dc.jademc.item.tool.EnderitePickaxeItem;
import net.dc.jademc.item.tool.EnderiteShovelItem;
import net.dc.jademc.item.tool.EnderiteSword;
import net.dc.jademc.item.tool.JadeAxeItem;
import net.dc.jademc.item.tool.JadeHoeItem;
import net.dc.jademc.item.tool.JadePickaxeItem;
import net.dc.jademc.item.tool.JadeShovelItem;
import net.dc.jademc.item.tool.JadeSwordItem;
import net.dc.jademc.tileentity.PackageTileEntity;
import net.dc.jademc.world.gen.OreGen;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JadeMCMod.MODID)
public class JadeMCMod
{
    public static final String MODID = "jademc";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public JadeMCMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // custom listener for ore generation
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::addFeaturesToBiomes);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("jademc", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");

            blockRegistryEvent.getRegistry().registerAll( // blocks
                new GreenCrystalOre(),
                new TransportHub(),
                new Package(),
                new JadeBlock(),
                new EnderiteOre()
            );
        }
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            LOGGER.info("HELLO from Register Item");

            itemRegistryEvent.getRegistry().registerAll( // items
                new JadeItem(),
                new JadeShardItem(),
                new ImpureCrystalItem(),
                new RingOfFlightItem()
            );
            itemRegistryEvent.getRegistry().registerAll( // block items
                new GreenCrystalOre.Item(),
                new TransportHub.Item(),
                new Package.Item(),
                new JadeBlock.Item(),
                new EnderiteOre.Item()
            );
            itemRegistryEvent.getRegistry().registerAll( // tools
                new JadePickaxeItem(),
                new JadeSwordItem(),
                new JadeAxeItem(),
                new JadeShovelItem(),
                new JadeHoeItem(),
                new EnderitePickaxeItem(),
                new EnderiteSword(),
                new EnderiteAxeItem(),
                new EnderiteShovelItem(),
                new EnderiteHoeItem()
            );
            itemRegistryEvent.getRegistry().registerAll( // armor
                new JadeHelmet(),
                new JadeChestplate(),
                new JadeLeggings(),
                new JadeBoots(),
                new EnderiteHelmet(),
                new EnderiteChestplate(),
                new EnderiteLeggings(),
                new EnderiteBoots()
            );
        }
        @SubscribeEvent
        public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().registerAll(
                TileEntityType.Builder.of(PackageTileEntity::new, Archive.PACKAGE).build(null).setRegistryName("package")
            );
        }
    }
}
