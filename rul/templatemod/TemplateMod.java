package rul.templatemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateMod implements ModInitializer, ClientModInitializer {

	public static final String MOD_ID = "templatemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
	}

	// Добавили клиентскую часть
	@Override
	public void onInitializeClient() {
		NigKeyHandler.register(); // Тут запускается наш обработчик кнопки
	}
}