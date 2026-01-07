package ru.blurry16.autowb.client;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutowbCommands {
    public static int autowb(CommandContext<FabricClientCommandSource> context) {
        AutowbClient.enabled = !AutowbClient.enabled;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal("autowb " + (AutowbClient.enabled ? "enabled" : "disabled")), false);
        return 1;
    }
}
