package me.cryocell.cryopersistence.command;

import me.cryocell.cryopersistence.CryoPersistence;
import me.cryocell.cryopersistence.utils.MessageUtils;
import me.cryocell.cryopersistence.config.ConfigService;
import me.cryocell.cryopersistence.config.Constants;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

public class CryoPersistenceCommand implements CommandExecutor {
    private ConfigService configService;
    private CryoPersistence plugin;

    public CryoPersistenceCommand(CryoPersistence plugin, ConfigService configService) {
        this.plugin = plugin;
        this.configService = configService;
    }

    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                if (sender.hasPermission(Constants.RELOAD_PERMISSION_PATH)) {
                    this.configService.reloadSettings();
                    this.plugin.reloadTasks();
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.GRAY + this.configService.getTag() + "reloaded"));
                } else {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("version")) {
                if (sender.hasPermission(Constants.VERSION_PERMISSION_PATH)) {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.DARK_GRAY + "------------------ " + this.configService.getTag() + "------------------"));
                    sender.sendMessage(ChatColor.AQUA + "Version" + ChatColor.DARK_GRAY + ": v" + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC +this.plugin.getDescription().getVersion());
                    sender.sendMessage(ChatColor.AQUA + "Developer" + ChatColor.DARK_GRAY + ": " + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "cryocell");
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.DARK_GRAY + "-----------------------------------------"));
                }else{
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("help")) {
                sendHelpMessage(sender);
                return true;
            }
            if(args[0].equalsIgnoreCase("fbackup")) {
                if (sender.hasPermission(Constants.BACKUP_PERMISSION_PATH)) {
                    this.plugin.backupService.createNewBackup();
                } else {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("fsave")) {
                if (sender.hasPermission(Constants.SAVE_PERMISSION_PATH)) {
                    this.plugin.saveService.saveFiles();
                } else {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
        }
        sendHelpMessage(sender);
        return true;
    }

    public void sendHelpMessage(CommandSender sender){
        if (sender.hasPermission(Constants.HELP_PERMISSION_PATH)) {
            sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.DARK_GRAY + "------------------ " + this.configService.getTag() + "------------------"));
            if(sender.hasPermission(Constants.RELOAD_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + ChatColor.DARK_AQUA + " reload" + ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "reloads config file, and tasks.");
            }
            if(sender.hasPermission(Constants.BACKUP_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + ChatColor.DARK_AQUA + " fbackup" + ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "creates a new backup for worlds.");
            }
            if(sender.hasPermission(Constants.SAVE_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + ChatColor.DARK_AQUA + " fsave" + ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "saves players and worlds.");
            }
            if(sender.hasPermission(Constants.VERSION_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + ChatColor.DARK_AQUA + " version" + ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "display the current version");
            }
            sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.DARK_GRAY + "-----------------------------------------"));
        } else {
            sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
        }
    }
}