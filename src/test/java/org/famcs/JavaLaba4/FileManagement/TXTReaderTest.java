package org.famcs.JavaLaba4.FileManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.famcs.JavaLaba4.CoffeeFabric;
import org.famcs.JavaLaba4.CoffeeMakerCollection;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TXTReaderTest {

    private File tempFile;
    private TXTReader txtReader;
    private CoffeeMakerCollection collection;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testInput", ".txt");
        tempFile.deleteOnExit();  

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("ID:1||Brand:BrandTest||Model:ModelTest||Power:1000W||Price:249.99 USD||ReleaseDate:15:05:2023\n");
            writer.write("ID:2||Brand:Brand2||Model:Model2||Power:1500W||Price:299.99 USD||ReleaseDate:10:10:2022\n");
        }

        txtReader = new TXTReader(tempFile.getAbsolutePath());
        collection = new CoffeeMakerCollection();
    }

    @Test
    public void testRead() {
        txtReader.read(collection);

        assertEquals(2, collection.getSize());

        CoffeeFabric firstItem = collection.getItem(0);
        assertEquals(1, firstItem.getID());
        assertEquals("BrandTest", firstItem.getBrand());
        assertEquals("ModelTest", firstItem.getModel());
        assertEquals(1000, firstItem.getPower());
        assertEquals(249.99, firstItem.getPrice(), 0.01);
        assertEquals("15:05:2023", new java.text.SimpleDateFormat("dd:MM:yyyy").format(firstItem.getDate()));

        CoffeeFabric secondItem = collection.getItem(1);
        assertEquals(2, secondItem.getID());
        assertEquals("Brand2", secondItem.getBrand());
        assertEquals("Model2", secondItem.getModel());
        assertEquals(1500, secondItem.getPower());
        assertEquals(299.99, secondItem.getPrice(), 0.01);
        assertEquals("10:10:2022", new java.text.SimpleDateFormat("dd:MM:yyyy").format(secondItem.getDate()));
    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
