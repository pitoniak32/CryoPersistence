package me.cryocell.cryopersistence;

import me.cryocell.cryopersistence.services.backup.BackupService;
import me.cryocell.cryopersistence.command.CryoPersistenceCommand;
import me.cryocell.cryopersistence.command.CryoPersistenceTabComplete;
import me.cryocell.cryopersistence.config.ConfigService;
import me.cryocell.cryopersistence.config.Constants;
import me.cryocell.cryopersistence.services.save.SaveService;
import me.cryocell.cryopersistence.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class CryoPersistence extends JavaPlugin {
    public ConfigService configService;

    public BackupService backupService;
    public SaveService saveService;

    @Override
    public void onEnable() {
        this.configService = new ConfigService("config.yml", this.getDataFolder());

        this.backupService = this.configService.getBackupConfig().getIsAutoBackupOn() ? new BackupService(this.configService, this) : null;
        this.saveService = this.configService.getSaveConfig().getIsAutoSaveOn() ? new SaveService(this.configService, this) : null;

        MessageUtils.broadcastMessage(this.getServer(), "ticks: " + this.configService.getSaveConfig().getAutoSaveIntervalTicks());

        this.getCommand(Constants.COMMAND_NAME).setTabCompleter(new CryoPersistenceTabComplete(this));
        this.getCommand(Constants.COMMAND_NAME).setExecutor(new CryoPersistenceCommand(this, this.configService));
    }

    @Override
    public void onDisable() {
        this.configService.save();

        for(World world : this.getServer().getWorlds()) {
            world.setAutoSave(true);
        }
        try {
            this.cancelTasks();
        } catch (Exception e) {
            Bukkit.getLogger().info(e.getMessage());
        }
    }

    public void reloadTasks() {
        this.cancelTasks();

        if(this.configService.getSaveConfig().getIsAutoSaveOn()) {
            this.saveService = new SaveService(this.configService, this);
        }
        if(this.configService.getBackupConfig().getIsAutoBackupOn()) {
            this.backupService = new BackupService(this.configService, this);
        }
    }

    public void cancelTasks() {
        if(this.saveService != null) {
            this.saveService.cancelSaveTask();
        }
        if(this.backupService != null) {
            this.backupService.cancelBackupTask();
        }
    }
}
