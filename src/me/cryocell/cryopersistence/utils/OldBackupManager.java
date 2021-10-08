package me.cryocell.cryopersistence.utils;

import me.cryocell.cryopersistence.CryoPersistence;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class OldBackupManager {

    public static void removeOldBackups(String backupDir, int maxBackups, CryoPersistence plugin) {
        try {
            File backupDirFolder = new File(backupDir);
            if(backupDirFolder.isDirectory()) {
                ArrayList<File> fileList = new ArrayList<>(Arrays.stream(backupDirFolder.listFiles()).toList());
                if(fileList.size() > maxBackups) {
                    fileList.sort(Comparator.comparingLong(File::lastModified));
                    int numDeleted = 0;
                    while(fileList.size() > maxBackups) {
                        OldBackupManager.deleteDirectoryJava7(fileList.get(0).getAbsolutePath());
                        fileList.remove(0);
                        numDeleted++;
                    }
                    MessageUtils.sendServerMessage(plugin.getServer(), ChatColor.DARK_GRAY + plugin.configService.getTag() + "deleted " + numDeleted + " backups");
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void deleteDirectoryJava7(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {

            // delete directories or folders
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }

            // delete files
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
