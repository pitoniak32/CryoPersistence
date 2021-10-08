package me.cryocell.cryopersistence.config.backup;

import me.cryocell.cryopersistence.config.Constants;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BackupConfigService {
    private YamlConfiguration config;
    private File dataFolder;

    public BackupConfigService(YamlConfiguration config, File dataFolder) {
        this.config = config;
        this.dataFolder = dataFolder;
        this.dataFolder = new File(this.dataFolder + File.separator + "backups");
        this.setDefaults();
    }

    public int getMaxBackupWorldsCount() { return this.config.getInt(Constants.BACKUP_MAX_COUNT_PATH); }

    public int getAutoBackupIntervalTicks() {
        return this.config.getInt(Constants.AUTO_BACKUP_INTERVAL_TICKS_PATH);
    }


    private void setDefaults() {
        // Settings Defaults.
        this.config.addDefault(Constants.AUTO_BACKUP_INTERVAL_TICKS_PATH, Constants.AUTO_BACKUP_INTERVAL_TICKS_DEFAULT);
        this.config.addDefault(Constants.BACKUP_MAX_COUNT_PATH, Constants.BACKUP_MAX_COUNT_DEFAULT);

        this.config.options().copyDefaults(true);
    }
}
