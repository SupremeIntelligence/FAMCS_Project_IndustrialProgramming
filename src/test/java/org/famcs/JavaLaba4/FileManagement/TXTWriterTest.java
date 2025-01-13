package org.famcs.JavaLaba4.FileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.famcs.JavaLaba4.CoffeeMaker;
import org.famcs.JavaLaba4.CoffeeMakerCollection;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TXTWriterTest {

    private File tempFile;
    private TXTWriter txtWriter;
    private CoffeeMakerCollection collection;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testOutput", ".txt");
        tempFile.deleteOnExit(); 

        txtWriter = new TXTWriter(tempFile.getAbsolutePath());
        collection = new CoffeeMakerCollection();
        
        collection.add(new CoffeeMaker(1, "BrandTest", "ModelTest", 1000, 249.99, new java.util.Date()));
        collection.add(new CoffeeMaker(2, "Brand2", "Model2", 1500, 299.99, new java.util.Date()));
    }

    @Test
    public void testWrite() throws IOException {
        txtWriter.write(collection);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }

        String expectedContent = collection.toString().trim(); 
        String actualContent = fileContent.toString().trim(); 
        
        
        assertEquals(expectedContent, actualContent);
    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
