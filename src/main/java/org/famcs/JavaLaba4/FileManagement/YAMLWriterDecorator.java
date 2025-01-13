package org.famcs.JavaLaba4.FileManagement;

import java.io.FileWriter;
import java.io.IOException;

import org.famcs.JavaLaba4.CoffeeMaker;
import org.famcs.JavaLaba4.CoffeeMakerCollection;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class YAMLWriterDecorator extends TXTWriter
{
    private final DataWriter wrapee;

    public YAMLWriterDecorator ()
    {
        this.wrapee = null;
    }

    public YAMLWriterDecorator (DataWriter source)
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
    public void write(CoffeeMakerCollection collection)
    {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); 
        options.setPrettyFlow(true); 
        options.setIndent(10); 
    
        Representer representer = new Representer(options);
        representer.addClassTag(CoffeeMaker.class, new Tag("!org.famcs.JavaLaba4.CoffeeMaker"));

        Yaml yaml = new Yaml(representer);
    
        try (FileWriter writer = new FileWriter(this.getFilePath())) 
        {
            for (int i = 0; i < collection.getSize(); i++) 
            {
                writer.write("- "); 
                yaml.dump(collection.getItem(i), writer);
                writer.write("\n"); 
            }
        } catch (IOException e) 
        {
            System.out.println("Error writing YAML file: " + e.getMessage());
        }
    }
}
