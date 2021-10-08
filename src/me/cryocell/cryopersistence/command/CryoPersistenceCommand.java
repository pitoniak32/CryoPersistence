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
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.GRAY + this.configService.getTag() + this.configService.getReloadMessageFormat()));
                } else {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("version")) {
                if (sender.hasPermission(Constants.VERSION_PERMISSION_PATH)) {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.DARK_GRAY + "------------------ " + this.configService.getTag() + "------------------"));
                    sender.sendMessage(ChatColor.AQUA + "Version" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + this.plugin.getDescription().getVersion());
                    sender.sendMessage(ChatColor.AQUA + "Developer" + ChatColor.DARK_GRAY + ": " + ChatColor.DARK_AQUA + "cryocell");
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
            if(args[0].equalsIgnoreCase("force-backup")) {
                if (sender.hasPermission(Constants.BACKUP_PERMISSION_PATH)) {
                    this.plugin.backupService.createNewBackup();
                } else {
                    sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("force-save")) {
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
            sender.sendMessage(MessageUtils.convertColorCodes(this.configService.getTag() + ChatColor.AQUA + "Commands:"));
            if(sender.hasPermission(Constants.RELOAD_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + " reload " + ChatColor.WHITE + "Reloads plugin config file.");
            }
            if(sender.hasPermission(Constants.BACKUP_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + " force-backup" + ChatColor.WHITE + "Creates a new backup for worlds.");
            }
            if(sender.hasPermission(Constants.VERSION_PERMISSION_PATH)){
                sender.sendMessage(ChatColor.AQUA + Constants.COMMAND_ALIAS + " version " + ChatColor.WHITE + "Display the current version");
            }
        } else {
            sender.sendMessage(MessageUtils.convertColorCodes(ChatColor.RED + this.configService.getTag() + this.configService.getNoPermissionsMessageFormat()));
        }
    }
}