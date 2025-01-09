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
    private String filename;

    TXTWriter ()
    {
        this.filename = "output";
    }
    TXTWriter(String filename)
    {
        this.filename = filename;
    }

    @Override
    public String getFileName()
    {
        return filename;
    }

    @Override
    public void setFileName(String filename)
    {
        this.filename = filename;
    }

    @Override
    public void write (CoffeeMakerCollection collection)
    {
        try (FileWriter output = new FileWriter (filename+ ".txt");)
        {
            output.write(collection.toString());
        }
        catch (IOException error)
        {
            System.out.println("\nError writing to file " + filename + ".txt" +"\n" + error.getMessage());
        }
    }
    public void zipArchive (String zipfilename)
    {
            try (FileInputStream fileInput = new FileInputStream(filename); 
                FileOutputStream fileOutput = new FileOutputStream(zipfilename); 
                ZipOutputStream zipOutput = new ZipOutputStream(fileOutput)
                ) 
            {
                ZipEntry zipEntry = new ZipEntry(filename);
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

    public void jarArchive(String jarfilename)
    {
        try (FileInputStream fileInput = new FileInputStream(filename + ".txt"); 
            FileOutputStream fileOutput = new FileOutputStream(jarfilename); 
            JarOutputStream jarOutput = new JarOutputStream(fileOutput);
            )
        {

            JarEntry jarEntry = new JarEntry(filename + ".txt");
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
