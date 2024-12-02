package net.aoba.Exception;

import net.aoba.AobaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
        MinecraftClient MC = MinecraftClient.getInstance();

        MC.inGameHud.getChatHud().addMessage(Text.of(AobaClient.CHAT_PREFIX + Formatting.RED + message));
    }
}
