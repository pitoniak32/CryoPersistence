package me.cryocell.cryopersistence.command;

import me.cryocell.cryopersistence.CryoPersistence;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CryoPersistenceTabComplete implements TabCompleter {
    CryoPersistence plugin;
    public CryoPersistenceTabComplete(CryoPersistence pl) { this.plugin = pl; }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("cp") || label.equalsIgnoreCase("cryo-persist")) {
            if (sender instanceof Player && args.length == 1) {
                ArrayList<String> autoComplete = new ArrayList<>(); //all panels
                autoComplete.add("help");
                autoComplete.add("force-backup");
                autoComplete.add("force-save");
                autoComplete.add("reload");
                autoComplete.add("version");
                return autoComplete;
            }
        }
        return null;
    }
}
