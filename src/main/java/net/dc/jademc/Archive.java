package net.dc.jademc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(JadeMCMod.MODID)
public class Archive {

    // Items
    public static final Item JADE = null;
    public static final Item JADE_SHARD = null;
    public static final Item IMPURE_CRYSTAL = null;
    public static final Item RING_OF_FLIGHT = null;

    // Specific Items (tools, weapons, armor, etc.)
    public static final PickaxeItem JADE_PICKAXE = null;
    public static final SwordItem   JADE_SWORD = null;
    public static final ShovelItem  JADE_SHOVEL = null;
    public static final AxeItem     JADE_AXE = null;
    public static final HoeItem     JADE_HOE = null;
    public static final ArmorItem   JADE_HELMET = null;
    public static final ArmorItem   JADE_CHESTPLATE = null;
    public static final ArmorItem   JADE_LEGGINGS = null;
    public static final ArmorItem   JADE_BOOTS = null;
    public static final SwordItem   ENDERITE_SWORD = null;
    public static final PickaxeItem ENDERITE_PICKAXE = null;
    public static final AxeItem     ENDERITE_AXE = null;
    public static final ShovelItem  ENDERITE_SHOVEL = null;
    public static final HoeItem     ENDERITE_HOE = null;
    public static final ArmorItem   ENDERITE_HELMET = null;
    public static final ArmorItem   ENDERITE_CHESTPLATE = null;
    public static final ArmorItem   ENDERITE_LEGGINGS = null;
    public static final ArmorItem   ENDERITE_BOOTS = null;

    // Blocks
    public static final Block GREEN_CRYSTAL_ORE = null;
    public static final Block TRANSPORT_HUB = null;
    public static final Block PACKAGE = null;
    public static final Block JADE_BLOCK = null;
    public static final Block ENDERITE_ORE = null;
    public static final Block ENDERITE_BLOCK = null;

    // Block-Items
    @ObjectHolder("green_crystal_ore")
    public static final Item GREEN_CRYSTAL_ORE_ITEM = null;
    @ObjectHolder("transport_hub")
    public static final Item TRANSPORT_HUB_ITEM = null;
    @ObjectHolder("package")
    public static final Item PACKAGE_ITEM = null;
    @ObjectHolder("jade_block")
    public static final Item JADE_BLOCK_ITEM = null;
    @ObjectHolder("enderite_ore")
    public static final Item ENDERITE_ORE_ITEM = null;
    @ObjectHolder("enderite_block")
    public static final Item ENDERITE_BLOCK_ITEM = null;

    // Tile Entity Types
    @ObjectHolder("package")
    public static final TileEntityType<?> PACKAGE_TYPE = null;

    public static class NoRegistry {
        // Tags
        @SafeVarargs
        private static <T extends IForgeRegistryEntry<T>> Tag<T> c(T... a) {
            HashSet<T> hs = new HashSet<T>(Arrays.asList(a));
            return Tag.create((Set<T>) hs);
        }
        public static final Tag<Item> JADE_ITEMS = c(JADE, JADE_SHARD);
        public static final Tag<Item> JADE_REPAIR_ITEMS = c(JADE);
        public static final Tag<Item> ENDERITE_REPAIR_ITEMS = c();
        public static final Tag<Item> VANILLA_TRANSPORT_ITEMS = c(
            Items.SHULKER_BOX,
            Items.RED_SHULKER_BOX,
            Items.WHITE_SHULKER_BOX,
            Items.ORANGE_SHULKER_BOX,
            Items.MAGENTA_SHULKER_BOX,
            Items.LIGHT_BLUE_SHULKER_BOX,
            Items.YELLOW_SHULKER_BOX,
            Items.LIME_SHULKER_BOX,
            Items.PINK_SHULKER_BOX,
            Items.GRAY_SHULKER_BOX,
            Items.LIGHT_GRAY_SHULKER_BOX,
            Items.CYAN_SHULKER_BOX,
            Items.PURPLE_SHULKER_BOX,
            Items.BLUE_SHULKER_BOX,
            Items.BROWN_SHULKER_BOX,
            Items.GREEN_SHULKER_BOX,
            Items.BLACK_SHULKER_BOX
        );
    }
}
