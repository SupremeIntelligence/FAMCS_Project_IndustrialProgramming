package org.famcs.JavaLaba4.FileManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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
            System.out.println("Error Zip archiving file " + e.getMessage());
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
            System.out.println("Error Jar archiving file " + e.getMessage());
        }
    }

    public static void ZipUnarchive (String zipPath)
    {
           int dotIndex = zipPath.lastIndexOf("\\");
           String filePath = zipPath.substring(0, dotIndex + 1);

        try (FileInputStream fileInput = new FileInputStream(zipPath);
             ZipInputStream zipInput = new ZipInputStream(fileInput);
             )
            {
                ZipEntry zipEntry = zipInput.getNextEntry();
                filePath = zipEntry.getName();
                try (FileOutputStream fileOutput = new FileOutputStream(filePath);)
                {
                    byte[] buffer = new byte[1024];
                    int length;
       
                    while ((length = zipInput.read(buffer)) >= 0) {
                       fileOutput.write(buffer, 0, length);
                   }
                }
        }
            catch (IOException e)
            {
                System.out.println("Error unarchiving Zip file " + e.getMessage());
            }
    }

    public static void JarUnarchive (String jarPath)
    {
        int dotIndex = jarPath.lastIndexOf("\\");
           String filePath = jarPath.substring(0, dotIndex + 1);

        try (FileInputStream fileInput = new FileInputStream(jarPath);
             JarInputStream jarInput = new JarInputStream(fileInput);
             )
            {
                JarEntry jarEntry = jarInput.getNextJarEntry();
                filePath = jarEntry.getName();
                try (FileOutputStream fileOutput = new FileOutputStream(filePath);)
                {
                    byte[] buffer = new byte[1024];
                    int length;
       
                    while ((length = jarInput.read(buffer)) >= 0) {
                       fileOutput.write(buffer, 0, length);
                   }
                }
        }
            catch (IOException e)
            {
                System.out.println("Error unarchiving Jar file " + e.getMessage());
            }
    }
}
