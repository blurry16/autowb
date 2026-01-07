package ru.blurry16.autowb.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutowbClient implements ClientModInitializer {

    public final static String MOD_ID = "autowb";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public final static String[] GREETINGS = {"wb", "Wb", "WB", "hi", "hello", "sup", "Hi", "Hello", "Sup",};

    public static long lastWB = 0L;
    public static boolean enabled = false;

    @Override
    public void onInitializeClient() {

        PlayerJoinDetector.init();
        ClientTickEvents.END_CLIENT_TICK.register(server -> TickScheduler.tick());
        LOGGER.info("autowb initialized");

    }
}
