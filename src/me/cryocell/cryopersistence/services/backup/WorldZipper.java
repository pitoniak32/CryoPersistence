package me.cryocell.cryopersistence.services.backup;

import me.cryocell.cryopersistence.CryoPersistence;
import me.cryocell.cryopersistence.config.ConfigService;
import me.cryocell.cryopersistence.utils.MessageUtils;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WorldZipper {
    CryoPersistence plugin;
    ConfigService configService;

    public WorldZipper(CryoPersistence plugin, ConfigService configService) {
        this.plugin = plugin;
        this.configService = configService;
    }

    private boolean currentlyBackingUp = false;

    public void zipWorlds(String destZipFolder, String dateFolderName) {
        AtomicBoolean failed = new AtomicBoolean(false);

        //create new thread to backup world
        new Thread (() -> {
            try {
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFolder));
                this.zipWorlds(dateFolderName, zos);
                zos.flush();
                zos.close();
            } catch (IOException e) {
                failed.set(true);
                MessageUtils.sendServerMessage(
                    plugin.getServer(),
                    this.configService.getTag() + ChatColor.RED + "backup failed with error:" + e.getMessage()
                );
            }

            if(!failed.get()) {
                MessageUtils.sendServerMessage(
                        plugin.getServer(),
                        this.configService.getTag() + "Backup Complete."
                );
            }
        }).start();
    }

    // TODO: Make sure that the world folders are directories.
    private void zipWorlds(String dateFolderName, ZipOutputStream zos){
        List<String> worldNames = this.plugin.configService.getBackupConfig().getWorldsToBackup();
        MessageUtils.broadcastMessage(
                plugin.getServer(),
                this.configService.getTag() + "Backing up " + worldNames.toString() + "."
        );
        List<File> worldFiles = new ArrayList<>();
        for (String worldName : worldNames) {
            if(!new File(this.plugin.configService.getDataFolder().getAbsolutePath() + File.separator + dateFolderName + worldName).exists()) {
                File file = new File(this.plugin.configService.getDataFolder().getAbsolutePath() + File.separator + dateFolderName + worldName);
                file.mkdir();
                worldFiles.add(file);
                this.zipDirectory(file, file.getName() + "/" + file.getName(), zos);
            }
        }
    }

    private void zipDirectory(File file, String outDir, ZipOutputStream zos) {
        for( File nestedFile : file.listFiles()) {
            if (nestedFile.isDirectory()) {
                zipDirectory(nestedFile, outDir + File.separator + nestedFile.getName(), zos);
            }

            try {
                zos.putNextEntry(new ZipEntry(outDir + "/" + nestedFile.getName()));
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(nestedFile));
                long bytesRead = 0;
                int BUFFER_SIZE = 4096;
                byte[] bytesIn = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = bis.read(bytesIn)) != -1) {
                    zos.write(bytesIn, 0, read);
                    bytesRead += read;
                }
                zos.closeEntry();
            } catch (IOException ignore) {
                //skipping file, most likely 'session.lock' file
            }
        }
    }
}
