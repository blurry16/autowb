package ru.blurry16.autowb.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerJoinDetector {

    private final static Pattern PATTERN = Pattern.compile("(\\w+) joined the game"); // default CV pattern
    private final static Random RANDOM = new Random();

    public static void init() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("autowb").executes(AutowbCommands::autowb));
        });

        ClientReceiveMessageEvents.ALLOW_GAME.register((PlayerJoinDetector::onMessage));
    }

    private static String textToString(Text text) {
        return text.getString().replaceAll("§.", "");
    }

    @Nullable
    private static String getPlayerName(Text text) {
        String string = textToString(text);
        Matcher m = PATTERN.matcher(string);
        if (!m.matches()) return null;
        return m.group(1);
    }

    private static boolean shouldWB(Text text, long time) {
        if (!AutowbClient.CONFIG.enabled) return false;
        String playerName = getPlayerName(text);
        if (playerName == null) return false;
        assert MinecraftClient.getInstance().player != null;
        return (!playerName.equals(MinecraftClient.getInstance().player.getName().getString()) && PATTERN.asMatchPredicate().test(textToString(text)) && time - AutowbClient.lastWB > 30);
    }

    private static boolean onMessage(Text text, boolean b) {
        long unixTime = System.currentTimeMillis() / 1000L;
        if (shouldWB(text, unixTime)) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null) {
                TickScheduler.schedule(RANDOM.nextInt(20, 31), () -> {
                    String message = AutowbClient.CONFIG.greetings.get(RANDOM.nextInt(0, AutowbClient.CONFIG.greetings.size()));
                    client.player.networkHandler.sendChatCommand("y " + message);
                });
                AutowbClient.lastWB = unixTime;
            }
        }
        return true;
    }
}
