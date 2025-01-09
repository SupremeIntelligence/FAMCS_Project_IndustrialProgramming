package org.famcs.JavaLaba4;
import java.nio.file.Path;
import java.nio.file.Paths;
public final class DataAccessManager 
{
    private static volatile DataAccessManager instance;

    private DataReader reader;
    private DataWriter writer;
    public static Path projectDir = Paths.get("").toAbsolutePath();
    
        private DataAccessManager ()
        {
        this.reader = null;
        this.writer = null;
        }

    public static DataAccessManager getInstance()
    {
        DataAccessManager instance = DataAccessManager.instance;
            if (instance == null) //если обьект еще не создан
            {
                synchronized (DataAccessManager.class) 
                {
                instance = DataAccessManager.instance;
                    if (instance == null)                     //или не был создан в промежутке между первой или второй проверкой
                    {
                        DataAccessManager.instance = instance = new DataAccessManager();         
                    }
                }
            }
            return instance;
    }

    public void initialize_read (String fileName, String fileType)
    {
        DataReader baseReader = new TXTReader(fileName);

        switch (fileType.toLowerCase())
        {
            case "txt":
                reader = baseReader;
                break;
            case "json":
                reader = new JSONReaderDecorator(baseReader);
                break;
            case "xml":
                reader = new XMLReaderDecorator(baseReader);
                break;
            default:
                reader = baseReader;
                break;
        }
    }
    public void initialize_write(String fileName, String fileType)
    {
        DataWriter baseWriter = new TXTWriter(fileName);

        switch (fileType.toLowerCase())
        {
            case "txt":
                writer = baseWriter;
                break;
            case "json":
                writer = new JSONWriterDecorator(baseWriter);
                break;
            case "xml":
                writer = new XMLWriterDecorator(baseWriter);
                break;
            default:
                writer = baseWriter;
                break;
        }
    }

    DataReader getDataReader ()
    {
        return reader;
    }

    DataWriter getDataWriter ()
    {
        return writer;
    }
}
