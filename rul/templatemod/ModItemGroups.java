package rul.templatemod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TEMPLATE_MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, 
        Identifier.of(TemplateMod.MOD_ID, "template_mod_items"), 
        FabricItemGroup.builder()
        .icon(() -> new ItemStack(ModItems.RUBY))
        .displayName(Text.translatable("itemgroup.templatemod.template_mod_items"))
        .entries((displayContext, entries) -> 
        {
            entries.add(ModItems.RUBY);
        })
        .build());
    
    public static final ItemGroup TEMPLATE_MOD_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP, 
        Identifier.of(TemplateMod.MOD_ID, "template_mod_blocks"), 
        FabricItemGroup.builder()
        .icon(() -> new ItemStack(ModItems.RUBY_BLOCK))
        .displayName(Text.translatable("itemgroup.templatemod.template_mod_blocks"))
        .entries((displayContext, entries) -> 
        {
            entries.add(ModItems.RUBY_BLOCK);
        })
        .build());
        
    public static void registerItemGroups() {}
}
