package me.cryocell.cryopersistence.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipOutputStream;

public class ZipWorlds {

    public static void zipWorldFolders(String worldsFolder, String destBasePath) {
        try {
            Path sourceDir = Paths.get(worldsFolder);
            String strDate = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destBasePath.concat(File.separator).concat(strDate).concat(".zip")));

            Files.walkFileTree(sourceDir, new ZipWorldFileVisitor(sourceDir, zos));

            zos.flush();
            zos.close();
        } catch (IOException ignore) {
            // TODO: Fix exception on write of session.lock.
        }
    }
}
