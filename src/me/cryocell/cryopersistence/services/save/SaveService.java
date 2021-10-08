package me.cryocell.cryopersistence.services.save;

import me.cryocell.cryopersistence.CryoPersistence;
import me.cryocell.cryopersistence.config.ConfigService;
import me.cryocell.cryopersistence.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SaveService {
    private ConfigService configService;
    private CryoPersistence plugin;
    private BukkitTask autoSaveTask;

    public SaveService(ConfigService configService, CryoPersistence plugin) {
        this.configService = configService;
        this.plugin = plugin;
        if(this.configService.getSaveConfig().getAutoSaveIntervalTicks() > 0) this.scheduleSaveTask();
    }

    public void scheduleSaveTask() {
        if (this.configService.getSaveConfig().getAutoSaveIntervalTicks() > 0) {

            for(World world : this.plugin.getServer().getWorlds()) {
                world.setAutoSave(false);
            }

            this.autoSaveTask = new BukkitRunnable() {
                @Override
                public void run() {
                    saveFiles();
                }
            }.runTaskTimer(
                this.plugin, this.configService.getSaveConfig().getAutoSaveIntervalTicks(), // Wait the specified ticks to start saves.
                this.configService.getSaveConfig().getAutoSaveIntervalTicks()  // Repeat saves after specified ticks.
            );

            MessageUtils.sendServerMessage(
                    this.plugin.getServer(),
                    ChatColor.DARK_GRAY + this.configService.getTag() + "save task scheduled"
            );
        }
    }

    public void saveFiles() {
        MessageUtils.broadcastMessage(this.plugin.getServer(), ChatColor.DARK_GRAY + this.configService.getTag() + "saving players");
        this.plugin.getServer().savePlayers();

        MessageUtils.broadcastMessage(this.plugin.getServer(), ChatColor.DARK_GRAY + this.configService.getTag() + "saving worlds");
        for(World world : this.plugin.getServer().getWorlds()) {
            world.save();
        }
    }

    public void cancelSaveTask() {
        if (this.autoSaveTask != null) {
            if (!this.autoSaveTask.isCancelled()) {
                this.autoSaveTask.cancel();
            }
        }
    }
}
