package org.famcs.JavaLaba4;

import java.io.FileWriter;
import java.io.IOException;

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
}
