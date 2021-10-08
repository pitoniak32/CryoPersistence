package me.cryocell.cryopersistence.config.save;

import me.cryocell.cryopersistence.config.Constants;
import org.bukkit.configuration.file.YamlConfiguration;

public class SaveConfigService {
    private YamlConfiguration config;

    public SaveConfigService(YamlConfiguration config) {
        this.config = config;
        this.setDefaults();
    }

    public boolean getIsAutoSaveOn() {
        return this.config.getBoolean(Constants.AUTO_SAVE_ON_PATH);
    }

    public int getAutoSaveIntervalTicks() {
        return this.config.getInt(Constants.AUTO_SAVE_INTERVAL_TICKS_PATH);
    }

    public String getSaveStartedMessageFormat() {
        return this.config.getString(Constants.SAVE_STARTED_FORMAT_PATH);
    }

    private void setDefaults() {
        // Settings Defaults.
        this.config.addDefault(Constants.AUTO_SAVE_ON_PATH, Constants.AUTO_SAVE_ON_DEFAULT);
        this.config.addDefault(Constants.AUTO_SAVE_INTERVAL_TICKS_PATH, Constants.AUTO_SAVE_INTERVAL_TICKS_DEFAULT);

        // Formatting Defaults.
        this.config.addDefault(Constants.SAVE_STARTED_FORMAT_PATH, Constants.SAVE_STARTED_FORMAT_DEFAULT);
    }
}
