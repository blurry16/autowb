package ru.blurry16.autowb.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutowbClient implements ClientModInitializer {

    public final static String MOD_ID = "autowb";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static long lastWB = 0L;
    public static boolean enabled = false;

    @Override
    public void onInitializeClient() {

        PlayerJoinDetector.init();
        LOGGER.info("autowb initialized");

    }
}
