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

    private void setDefaults() {
        // Settings Defaults.
        this.config.addDefault(Constants.AUTO_SAVE_ON_PATH, Constants.AUTO_SAVE_ON_DEFAULT);
        this.config.addDefault(Constants.AUTO_SAVE_INTERVAL_TICKS_PATH, Constants.AUTO_SAVE_INTERVAL_TICKS_DEFAULT);

        this.config.options().copyDefaults(true);
    }
}
