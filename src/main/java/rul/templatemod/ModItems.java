package rul.templatemod;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TemplateMod.MOD_ID, name));
        return Items.register(registryKey, factory, settings);
    }

    private static BlockItem registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TemplateMod.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static final Item RUBY = registerItem("ruby", Item::new, new Item.Settings());
    public static final BlockItem RUBY_BLOCK = registerBlockItem("ruby_block", ModBlocks.RUBY_BLOCK);

    public static void registerModItems() {
        
    }
}
