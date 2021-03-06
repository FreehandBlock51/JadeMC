package net.dc.jademc.world.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.dc.jademc.Archive;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

// Working ore generation in forge 1.16.3! 
// To get this to work, add this to the constructor of your mod's main class:
// MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::addFeaturesToBiomes).
 
public class OreGen {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void addFeaturesToBiomes(BiomeLoadingEvent event) {
        switch (event.getCategory()) {
            case NETHER: // the nether
                LOGGER.info("biome " + event.getName().toString() + " is in the nether");
                break;
            case THEEND: // the end
                LOGGER.info("biome " + event.getName().toString() + " is in the end");
                addOreToBiome(event.getGeneration(), Archive.ENDERITE_ORE, new RandomBlockMatchRuleTest(Blocks.END_STONE, 0.32F), 255, 0, 5, 5);
                break;
            default: // hopefully, the rest of the biomes are in the overworld :/
                LOGGER.info("biome " + event.getName().toString() + " is in the overworld");
                addOreToBiome(event.getGeneration(), Archive.GREEN_CRYSTAL_ORE, OreFeatureConfig.FillerBlockType.NATURAL_STONE, 1255, 3, 10, 500);
                break;
        }
    }

    private static void addOreToBiome(BiomeGenerationSettingsBuilder biomeGenSettings, Block oreBlock, RuleTest toReplace, int maxHeight, int minHeight, int veinSize, int veinsPerChunk) {
        addOreToBiome(GenerationStage.Decoration.UNDERGROUND_ORES, biomeGenSettings, oreBlock, toReplace, maxHeight, minHeight, veinSize, veinsPerChunk);
    }
    private static void addOreToBiome(GenerationStage.Decoration stage, BiomeGenerationSettingsBuilder biomeGenSettings, Block oreBlock, RuleTest toReplace, int maxHeight, int minHeight, int veinSize, int veinsPerChunk) {
        // mappings weirdness (if some fields don't exist for you) ):
        // field_241882_a should be BASE_STONE_OVERWORLD
        // field_242907_l should be RANGE
 
        ConfiguredFeature<?, ?> feature = Feature.ORE.configured(
                new OreFeatureConfig(
                        toReplace,
                        oreBlock.defaultBlockState(),
                        veinSize))
                .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, veinSize, maxHeight))).count(veinsPerChunk);
 
        biomeGenSettings.addFeature(stage, feature);
    }
}
