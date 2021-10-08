package me.cryocell.cryopersistence.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWorldFileVisitor extends SimpleFileVisitor<Path> {
    private ZipOutputStream zos;
    private Path sourceDir;

    public ZipWorldFileVisitor(Path sourceDir, ZipOutputStream zos) throws FileNotFoundException {
        this.sourceDir = sourceDir;
        this.zos = zos;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        try {
            Path targetFile = sourceDir.relativize(file);

            zos.putNextEntry(new ZipEntry(targetFile.toString()));

            byte[] bytes = Files.readAllBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();

        } catch (IOException ignore) {
            // Ignoring session.lock error
        }

        return FileVisitResult.CONTINUE;
    }
}
