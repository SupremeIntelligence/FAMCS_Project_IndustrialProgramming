package org.famcs.JavaLaba4.FileManagement;

import org.famcs.JavaLaba4.CoffeeMakerCollection;

public interface DataWriter
{
    public void write (CoffeeMakerCollection collection);
    public String getFilePath();
    public void setFilePath(String filePath);
}
