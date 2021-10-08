package me.cryocell.cryopersistence.config.backup;

import me.cryocell.cryopersistence.config.Constants;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class BackupConfigService {
    private YamlConfiguration config;
    private File dataFolder;

    public BackupConfigService(YamlConfiguration config, File dataFolder) {
        this.config = config;
        this.dataFolder = dataFolder;
        this.dataFolder = new File(this.dataFolder + File.separator + "backups");
        this.setDefaults();
    }

    public boolean getIsAutoBackupOn() {
        return this.config.getBoolean(Constants.AUTO_BACKUP_ON_PATH);
    }

    public List getWorldsToBackup() {
        return this.config.getStringList(Constants.BACKUP_WORLD_FILES_PATH);
    }

    public int getBackupWorldsCount() {
        return this.config.getStringList(Constants.BACKUP_WORLD_FILES_PATH).size();
    }

    public int getMaxBackupWorldsCount() { return this.config.getInt(Constants.BACKUP_MAX_COUNT_PATH); }

    public int getAutoBackupIntervalTicks() {
        return this.config.getInt(Constants.AUTO_BACKUP_INTERVAL_TICKS_PATH);
    }

    public String getBackupStartedMessageFormat() {
        return this.config.getString(Constants.BACKUP_STARTED_FORMAT_PATH);
    }

    private void setDefaults() {
        // Settings Defaults.
        this.config.addDefault(Constants.AUTO_BACKUP_ON_PATH, Constants.AUTO_BACKUP_ON_DEFAULT);
        this.config.addDefault(Constants.AUTO_BACKUP_INTERVAL_TICKS_PATH, Constants.AUTO_BACKUP_INTERVAL_TICKS_DEFAULT);
        this.config.addDefault(Constants.BACKUP_MAX_FOLDER_SIZE_MB_PATH, Constants.BACKUP_MAX_FOLDER_SIZE_MB_DEFAULT);
        this.config.addDefault(Constants.BACKUP_MAX_COUNT_PATH, Constants.BACKUP_MAX_COUNT_DEFAULT);
        this.config.addDefault(Constants.BACKUP_WORLD_FILES_PATH, Constants.BACKUP_WORLD_FILES_DEFAULT);

        // Formatting Defaults.
        this.config.addDefault(Constants.BACKUP_STARTED_FORMAT_PATH, Constants.BACKUP_STARTED_FORMAT_DEFAULT);

        this.config.options().copyDefaults(true);
    }
}
