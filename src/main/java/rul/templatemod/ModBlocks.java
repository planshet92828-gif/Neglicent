package rul.templatemod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    
    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TemplateMod.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }
    
    public static final Block RUBY_BLOCK = registerBlock("ruby_block", Block.Settings.copy(Blocks.DIAMOND_BLOCK));
    
    public static void registerModBlocks() {}
}
