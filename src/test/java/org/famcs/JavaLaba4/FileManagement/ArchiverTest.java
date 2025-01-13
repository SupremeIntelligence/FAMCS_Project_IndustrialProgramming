package org.famcs.JavaLaba4.FileManagement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArchiverTest {

    private File tempFile;
    private File zipFile;
    private File jarFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Test content");
        }
        
        zipFile = File.createTempFile("testArchive", ".zip");
        zipFile.deleteOnExit();
        
        jarFile = File.createTempFile("testArchive", ".jar");
        jarFile.deleteOnExit();
    }

    @Test
    public void testZipArchive() throws IOException {
        Archiver.zipArchive(tempFile.getAbsolutePath(), zipFile.getAbsolutePath());

        // Проверяем, что файл был создан
        assertTrue(zipFile.exists(), "ZIP file should be created after zipping the file");

        // Проверка содержимого архива можно выполнить с помощью ZipInputStream
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zipInput.getNextEntry();
            assertNotNull(zipEntry, "ZIP entry should exist in the archive");
            assertEquals(tempFile.getAbsolutePath(), zipEntry.getName(), "The entry name should match the input file name");
        }
    }

    @Test
    public void testJarArchive() throws IOException {
        Archiver.jarArchive(tempFile.getAbsolutePath(), jarFile.getAbsolutePath());

        // Проверяем, что файл был создан
        assertTrue(jarFile.exists(), "JAR file should be created after jar archiving");

        // Проверка содержимого архива с помощью JarInputStream
        try (JarInputStream jarInput = new JarInputStream(new FileInputStream(jarFile))) {
            JarEntry jarEntry = jarInput.getNextJarEntry();
            assertNotNull(jarEntry, "JAR entry should exist in the archive");
            assertEquals(tempFile.getAbsolutePath(), jarEntry.getName(), "The entry name should match the input file name");
        }
    }

    @Test
    public void testZipUnarchive() throws IOException {
        // Архивируем файл
        Archiver.zipArchive(tempFile.getAbsolutePath(), zipFile.getAbsolutePath());
        
        // Разархивируем файл в другой файл
        File unzippedFile = new File(zipFile.getParent(), tempFile.getName());
        Archiver.ZipUnarchive(zipFile.getAbsolutePath());

        // Проверяем, что файл был разархивирован
        assertTrue(unzippedFile.exists(), "Unzipped file should be created after extracting from ZIP");
    }

    @Test
    public void testJarUnarchive() throws IOException {
        // Архивируем файл
        Archiver.jarArchive(tempFile.getAbsolutePath(), jarFile.getAbsolutePath());

        // Разархивируем файл в другой файл
        File unzippedFile = new File(jarFile.getParent(), tempFile.getName());
        Archiver.JarUnarchive(jarFile.getAbsolutePath());

        // Проверяем, что файл был разархивирован
        assertTrue(unzippedFile.exists(), "Unzipped file should be created after extracting from JAR");
    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
        if (zipFile.exists()) {
            zipFile.delete();
        }
        if (jarFile.exists()) {
            jarFile.delete();
        }
    }
}
