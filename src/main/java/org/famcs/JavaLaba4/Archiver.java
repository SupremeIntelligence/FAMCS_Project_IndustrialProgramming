package org.famcs.JavaLaba4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver 
{
    public static enum ArchiveTypes
    {
        ZIP,
        JAR;
    }

    public static void zipArchive (String filePath, String zipPath)
    {
            try (FileInputStream fileInput = new FileInputStream(filePath); 
                FileOutputStream fileOutput = new FileOutputStream(zipPath); 
                ZipOutputStream zipOutput = new ZipOutputStream(fileOutput)
                ) 
            {
                ZipEntry zipEntry = new ZipEntry(filePath);
                zipOutput.putNextEntry(zipEntry);
                    
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileInput.read(buffer)) >= 0)
                {
                        zipOutput.write(buffer, 0, length);
                }
            }
            catch (IOException e)
            {
            System.out.println("Error archiving file " + e.getMessage());
            }
        }

    public static void jarArchive(String filePath, String jarPath)
    {
        try (FileInputStream fileInput = new FileInputStream(filePath); 
            FileOutputStream fileOutput = new FileOutputStream(jarPath); 
            JarOutputStream jarOutput = new JarOutputStream(fileOutput);
            )
        {
            JarEntry jarEntry = new JarEntry(filePath);
            jarOutput.putNextEntry(jarEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInput.read(buffer)) >= 0)
            {
                jarOutput.write(buffer, 0, length);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error archiving file " + e.getMessage());
        }
    }
}
