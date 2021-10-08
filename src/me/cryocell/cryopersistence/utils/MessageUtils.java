package me.cryocell.cryopersistence.utils;

import org.bukkit.ChatColor;
import org.bukkit.Server;

public class MessageUtils {
    public static String convertColorCodes(String input) {
        return ChatColor.translateAlternateColorCodes('&',input);
    }
    public static void sendServerMessage(Server server, String msg) {
        server.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
    public static void broadcastMessage(Server server, String msg) {
        server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
