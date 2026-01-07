package ru.blurry16.autowb.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutowbClient implements ClientModInitializer {


    public final static String MOD_ID = "autowb";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//    public final static String[] GREETINGS = {"wb", "Wb", "WB", "hi", "hello", "sup", "Hi", "Hello", "Sup",};

    public static long lastWB = 0L;
    public static String lastWBed; // last player who got wb

//    public static boolean enabled = false;

    public static AutowbConfig CONFIG;

    @Override
    public void onInitializeClient() {

        AutoConfig.register(AutowbConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(AutowbConfig.class).getConfig();

        PlayerJoinDetector.init();
        ClientTickEvents.END_CLIENT_TICK.register(server -> TickScheduler.tick());
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            AutoConfig.getConfigHolder(AutowbConfig.class).save();
        });

        LOGGER.info("autowb initialized");

    }
}
