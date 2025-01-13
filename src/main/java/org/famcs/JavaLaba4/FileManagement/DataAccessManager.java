package org.famcs.JavaLaba4;
import java.nio.file.Path;
import java.nio.file.Paths;
public final class DataAccessManager 
{
    private static volatile DataAccessManager instance;

    private DataReader reader;
    private DataWriter writer;
    public static Path projectDir = Paths.get("").toAbsolutePath();
    public static String currentFile = projectDir.toString() + "\\example.txt";
    
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

    public void initialize_read (String filePath)
    {
        DataReader baseReader = new TXTReader(filePath);
        updateCurrentFile(filePath);
        int dotIndex = filePath.lastIndexOf('.');
        String fileType = filePath.substring(dotIndex+1);
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
            case "yaml":
                reader = new YAMLReaderDecorator(baseReader);
                break;
            default:
                reader = baseReader;
                break;
        }
    }
    public void initialize_write(String filePath)
    {
        DataWriter baseWriter = new TXTWriter(filePath);
        updateCurrentFile(filePath);
        int dotIndex = filePath.lastIndexOf('.');
        String fileType = filePath.substring(dotIndex+1);
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
            case "yaml":
                writer = new YAMLWriterDecorator(baseWriter);
                break;
            default:
                writer = baseWriter;
                break;
        }
    }

    public DataReader getDataReader ()
    {
        return reader;
    }

    public DataWriter getDataWriter ()
    {
        return writer;
    }

    private void updateCurrentFile(String filePath)
    {
        currentFile = filePath;
    }
}
