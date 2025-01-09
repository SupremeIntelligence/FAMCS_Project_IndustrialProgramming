package org.famcs.JavaLaba4;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;


public class JSONWriterDecorator extends TXTWriter
{
    private final DataWriter wrapee;
    private final ObjectMapper mapper;

    public JSONWriterDecorator ()
    {
        wrapee = null;
        mapper = JsonMapper.builder()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .build();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public JSONWriterDecorator (DataWriter source)
    {
        wrapee = source;
        mapper = JsonMapper.builder()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .build();
            
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void write (CoffeeFabric obj)
    {
        try
        {
            mapper.writeValue(new File (this.wrapee.getFileName() + ".json"), obj);
        }
        catch(IOException e)
        {
            System.out.println ("Error writing to JSON file " + e.getMessage());
        }
    }
    @Override
    public void write (CoffeeMakerCollection collection)
    {
        try
        {
            List<CoffeeFabric> list = collection.getList();
            mapper.writeValue(new File (this.wrapee.getFileName() + ".json"), list);
        }
        catch(IOException e)
        {
            System.out.println ("Error writing to JSON file " + e.getMessage());
        }
    }
}
