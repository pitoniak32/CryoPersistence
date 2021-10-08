package me.cryocell.cryopersistence.services.backup;

import me.cryocell.cryopersistence.CryoPersistence;
import me.cryocell.cryopersistence.config.ConfigService;
import me.cryocell.cryopersistence.utils.MessageUtils;
import me.cryocell.cryopersistence.utils.OldBackupManager;
import me.cryocell.cryopersistence.utils.ZipWorlds;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public class BackupService {
    private ConfigService configService;
    private CryoPersistence plugin;
    private BukkitTask autoBackupTask;
    private File saveFolder;
    public WorldZipper zipper;

    public BackupService(ConfigService configService, CryoPersistence plugin) {
        this.configService = configService;
        this.plugin = plugin;
        this.zipper = new WorldZipper(this.plugin, this.configService);
        this.saveFolder = new File(this.plugin.getDataFolder().getAbsolutePath().concat(File.separator).concat("backups"));
        this.scheduleBackupTask();
    }

    public void scheduleBackupTask() {
        if (this.configService.getBackupConfig().getIsAutoBackupOn()) {
            this.autoBackupTask = new BukkitRunnable() {
                @Override
                public void run() {
                    createNewBackup();
                }
            }.runTaskTimer(
                this.plugin,
                this.configService.getBackupConfig().getAutoBackupIntervalTicks(), // Wait the specified ticks to start a backup.
                this.configService.getBackupConfig().getAutoBackupIntervalTicks()  // Repeat backups after specified ticks.
            );

            MessageUtils.sendServerMessage(
                    this.plugin.getServer(),
                    ChatColor.DARK_GRAY + this.configService.getTag() + "backup task scheduled"
            );
        }
    }

    public void createNewBackup(){
        if(!this.saveFolder.exists()){
            this.saveFolder.mkdir();
        }

        MessageUtils.broadcastMessage(this.plugin.getServer(), ChatColor.DARK_GRAY + this.configService.getTag() + "server backup starting");

        new Thread(() -> {
            ZipWorlds.zipWorldFolders(this.plugin.getServer().getWorldContainer().getAbsolutePath(), this.saveFolder.getAbsolutePath());

            OldBackupManager.removeOldBackups(
                    saveFolder.getAbsolutePath(),
                    configService.getBackupConfig().getMaxBackupWorldsCount(),
                    plugin
            );
        }).start();
    }

    public void cancelBackupTask() {
        if (this.autoBackupTask != null) {
            if (!this.autoBackupTask.isCancelled()) {
                this.autoBackupTask.cancel();
            }
        }
    }
}
