package org.famcs.JavaLaba4;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TXTReader implements DataReader
{
    private String filename;

    TXTReader ()
    {
        this.filename = "input";
    }
    TXTReader(String filename)
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
    public void read (CoffeeMakerCollection collection)
    {
        try (Scanner scan = new Scanner (new FileReader (filename + ".txt")))
        {
            String str;
           while (scan.hasNextLine())
           {
                str = scan.nextLine();
                CoffeeMaker item = parseToCoffeeMaker(str);
                collection.add(item);
           }
        }
        catch (IOException error)
        {
            System.out.println("Error reading file " + filename + ".txt" + "\n" + error.getMessage());
        }
    }

    private CoffeeMaker parseToCoffeeMaker (String str)
    {
        CoffeeMaker obj = new CoffeeMaker();
        
        str = str.replaceAll ("\\s", "");
        StringTokenizer separator = new StringTokenizer(str, "|");
        int count = separator.countTokens();

        String[] tokens = new String[count];

        for(int i = 0; i < count; i++)
        {
            tokens[i] = separator.nextToken();
        }

        String IDstr = tokens[0].replace ("ID:", "");
        obj.setID(Integer.parseInt(IDstr));
        
        obj.setBrand(tokens[1].replace ("Brand:", ""));

        obj.setModel(tokens[2].replace ("Model:", ""));

        String powerStr = tokens[3].replace ("Power:","");
        powerStr = powerStr.substring(0, powerStr.length() - 1);  
        obj.setPower(Integer.parseInt(powerStr));

        String priceStr = tokens[4].replace ("Price:", "");
        priceStr = priceStr.substring (0, priceStr.length() - 3);
        priceStr = priceStr.replace (",", ".");
        obj.setPrice (Double.parseDouble(priceStr));

        String dateStr = tokens[5].replace ("ReleaseDate:", "");
        SimpleDateFormat dateFrmt = new SimpleDateFormat("dd:MM:yyyy");
         
        try {
            Date date = dateFrmt.parse (dateStr);
            obj.setDate(date);
        }
        catch (ParseException error)
        {
            System.out.println ("Error parsing the string containing the date");
        }

        return obj;
    }
}
