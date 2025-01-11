package org.famcs.JavaLaba4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TXTWriter implements DataWriter
{
    private String filePath;

    TXTWriter ()
    {
        this.filePath = "output.txt";
    }
    TXTWriter(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public String getFilePath()
    {
        return filePath;
    }

    @Override
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public void write (CoffeeMakerCollection collection)
    {
        try (FileWriter output = new FileWriter (filePath);)
        {
            output.write(collection.toString());
        }
        catch (IOException error)
        {
            System.out.println("\nError writing to file " + filePath +"\n" + error.getMessage());
        }
    }
    public void zipArchive (String zipfilePath)
    {
            try (FileInputStream fileInput = new FileInputStream(filePath); 
                FileOutputStream fileOutput = new FileOutputStream(zipfilePath); 
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

    public void jarArchive(String jarfilePath)
    {
        try (FileInputStream fileInput = new FileInputStream(filePath); 
            FileOutputStream fileOutput = new FileOutputStream(jarfilePath); 
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
