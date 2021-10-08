package me.cryocell.cryopersistence.config;

import me.cryocell.cryopersistence.config.backup.BackupConfigService;
import me.cryocell.cryopersistence.config.save.SaveConfigService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigService {

    // Instance Variables.
    private String configFileName;
    private File dataFolder;

    private SaveConfigService saveConfigService;
    private BackupConfigService backupConfigService;

    private YamlConfiguration config;
    
    public ConfigService(String configFileName, File dataFolder) {
        this.configFileName = configFileName;
        this.dataFolder = dataFolder;

        this.config = YamlConfiguration.loadConfiguration(new File(dataFolder + File.separator + configFileName));

        this.saveConfigService = new SaveConfigService(this.config);
        this.backupConfigService = new BackupConfigService(this.config, dataFolder);

        this.setDefaults();
        this.save();
    }

    public File getDataFolder() {
        return this.dataFolder;
    }

    public String getTag() {
        return this.config.getString(Constants.TAG_FORMAT_BASE_PATH) + " ";
    }

    public String getReloadMessageFormat() {
        return this.config.getString(Constants.RELOADED_FORMAT_PATH);
    }

    public String getNoPermissionsMessageFormat() {
        return this.config.getString(Constants.NO_PERMISSIONS_FORMAT_PATH);
    }

    public final SaveConfigService getSaveConfig() {
        return this.saveConfigService;
    }

    public final BackupConfigService getBackupConfig() {
        return this.backupConfigService;
    }

    public void reloadSettings() {
        this.config = YamlConfiguration.loadConfiguration(new File(dataFolder + File.separator + configFileName));
        this.saveConfigService = new SaveConfigService(this.config);
        this.backupConfigService = new BackupConfigService(this.config, this.dataFolder);
    }

    public void save() {
        try{
            this.config.save(new File(this.dataFolder + File.separator + this.configFileName));
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(this.getTag() + ChatColor.RED + "WARNING: Could not save the config file!");
        }
    }

    private void setDefaults() {
        // Settings Defaults.
        this.config.addDefault(Constants.VERSION_PATH, Constants.VERSION_DEFAULT);

        // Format Defaults.
        this.config.addDefault(Constants.TAG_FORMAT_BASE_PATH, Constants.TAG_FORMAT_BASE_DEFAULT);
        this.config.addDefault(Constants.NO_PERMISSIONS_FORMAT_PATH, Constants.NO_PERMISSIONS_FORMAT_DEFAULT);
        this.config.addDefault(Constants.NOT_FOUND_FORMAT_PATH, Constants.NOT_FOUND_FORMAT_DEFAULT);
        this.config.addDefault(Constants.RELOADED_FORMAT_PATH, Constants.RELOADED_FORMAT_DEFAULT);

        this.config.options().copyDefaults(true);
    }
}
