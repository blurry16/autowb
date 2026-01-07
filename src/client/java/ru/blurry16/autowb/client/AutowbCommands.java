package ru.blurry16.autowb.client;

import com.mojang.brigadier.context.CommandContext;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutowbCommands {
    public static int autowb(CommandContext<FabricClientCommandSource> context) {
        AutowbClient.CONFIG.enabled = !AutowbClient.CONFIG.enabled;
        AutoConfig.getConfigHolder(AutowbConfig.class).save();
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal("autowb " + (AutowbClient.CONFIG.enabled ? "enabled" : "disabled")), false);


//        MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(AutowbConfig.class, null).get());
        return 1;
    }
}
