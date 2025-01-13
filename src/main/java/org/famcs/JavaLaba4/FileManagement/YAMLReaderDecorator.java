package org.famcs.JavaLaba4.FileManagement;

import java.io.FileInputStream;
import java.io.IOException;

import org.famcs.JavaLaba4.CoffeeFabric;
import org.famcs.JavaLaba4.CoffeeMaker;
import org.famcs.JavaLaba4.CoffeeMakerCollection;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLReaderDecorator extends TXTReader
{
     private final DataReader wrapee;

    public YAMLReaderDecorator ()
    {
        this.wrapee = null;
    }

    public YAMLReaderDecorator (DataReader source)
    {
        this.wrapee = source;
    }

    @Override
    public String getFilePath()
    {
        String prevFilePath = this.wrapee.getFilePath();
        int dotIndex = prevFilePath.lastIndexOf('.');

        String newFilePath = prevFilePath.substring(0, dotIndex) + ".yaml";
        return newFilePath;
    }

    @Override
    public void read(CoffeeMakerCollection collection)
    {
        LoaderOptions options = new LoaderOptions();
        Constructor constructor = new Constructor(CoffeeFabric.class, options);

        TypeDescription coffeeMakerDesc = new TypeDescription(CoffeeMaker.class);
        constructor.addTypeDescription(coffeeMakerDesc);
        
        Yaml yaml = new Yaml(constructor);

        try (FileInputStream fileInput = new FileInputStream(this.getFilePath())) 
        {

            Iterable<Object> objects = yaml.loadAll(fileInput);

            for (Object obj : objects) {
                if (obj instanceof CoffeeFabric coffeeFabric) {
                    collection.add(coffeeFabric);
                } else {
                    System.out.println("Unexpected object type: " + obj.getClass().getName());
                }
            }
        }
        catch (IOException e)
        {
            System.out.println ("Error reading YAML file: " + e.getMessage());
        }
    }
}
