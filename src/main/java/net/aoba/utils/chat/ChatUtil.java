package net.aoba.utils.chat;

import net.aoba.AobaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public final class ChatUtil {
    public static void addChatMessage(final String msg) {
        MinecraftClient MC = MinecraftClient.getInstance();

        MC.inGameHud.getChatHud().addMessage(Text.of(AobaClient.CHAT_PREFIX + msg));
    }

    public static void addChatMessage(final String msg, final boolean prefix) {
        MinecraftClient MC = MinecraftClient.getInstance();

        if (prefix) {
            MC.inGameHud.getChatHud().addMessage(Text.of(AobaClient.CHAT_PREFIX + msg));
        } else {
            addChatMessage(msg);
        }
    }
}
