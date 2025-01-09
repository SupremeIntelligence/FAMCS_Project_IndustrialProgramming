package org.famcs.JavaLaba4;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JSONReaderDecorator extends TXTReader
{
    private final DataReader wrapee;
    private final ObjectMapper mapper;


    public JSONReaderDecorator ()
    {
        wrapee = null;
        mapper = new ObjectMapper();
    }
    public JSONReaderDecorator (DataReader source)
    {
        wrapee = source;
        mapper = new ObjectMapper();
    }
    @Override
    public String getFileName ()
    {
        return this.wrapee.getFileName();
    }
    @Override
    public void read (CoffeeMakerCollection collection)
    {
        try
        {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, CoffeeMaker.class);
            List<CoffeeFabric> list = mapper.readValue (new File(this.wrapee.getFileName() + ".json"), listType);
            collection.addFromList(list);
        }
        catch (IOException e)
        {
            System.out.println( "Error reading JSON file " + e.getMessage());
        }
        
    }

    @SuppressWarnings("unused")
    private void read (CoffeeFabric obj)
    {
        try 
        {
            CoffeeFabric temp = mapper.readValue (new File(this.wrapee.getFileName() + ".json"), CoffeeFabric.class);
            
            obj.setID(temp.getID());
            obj.setBrand(temp.getBrand());
            obj.setModel(temp.getModel());
            obj.setPower(temp.getPower());
            obj.setPrice(temp.getPrice());
            obj.setDate(temp.getDate());

        } catch (IOException e) 
        {
            System.out.println ("Error reading JSON file " + e.getMessage());
        }
    }
}
