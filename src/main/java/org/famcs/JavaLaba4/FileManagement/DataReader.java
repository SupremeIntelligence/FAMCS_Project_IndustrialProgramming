package org.famcs.JavaLaba4.FileManagement;

import org.famcs.JavaLaba4.CoffeeMakerCollection;

public interface DataReader 
{
    public void read (CoffeeMakerCollection collection);
    public String getFilePath();
    public void setFilePath(String filePath);
}
