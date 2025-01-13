package org.famcs.JavaLaba4.FileManagement;

import java.io.FileWriter;
import java.io.IOException;

import org.famcs.JavaLaba4.CoffeeMakerCollection;

public class TXTWriter implements DataWriter
{
    private String filePath;

    public TXTWriter ()
    {
        this.filePath = "output.txt";
    }
    public TXTWriter(String filePath)
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
}
