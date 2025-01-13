package org.famcs.JavaLaba4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMakerCollectionTest {

    private CoffeeMakerCollection coffeeMakerCollection;
    private CoffeeMaker coffeeMaker1;
    private CoffeeMaker coffeeMaker2;

    @BeforeEach
    public void setUp() {
        // Инициализация коллекции и объектов для тестирования
        coffeeMakerCollection = new CoffeeMakerCollection();
        coffeeMaker1 = new CoffeeMaker("Brand1", "Model1", 1000, 199.99, 01, 01, 2020);
        coffeeMaker2 = new CoffeeMaker("Brand2", "Model2", 1200, 299.99, 01,01,2020);
    }
    @Test
    void testAdd() 
    {
        coffeeMakerCollection.add(coffeeMaker1);
        assertEquals(1, coffeeMakerCollection.getSize());

        coffeeMakerCollection.add(coffeeMaker2);
        assertEquals(2, coffeeMakerCollection.getSize());
    }

    @Test
    void testAddFromList() {
        List<CoffeeFabric> newList = new ArrayList<>();
        newList.add(coffeeMaker1);
        newList.add(coffeeMaker2);

        coffeeMakerCollection.addFromList(newList);
        
        assertEquals(2, coffeeMakerCollection.getSize());
        assertEquals(coffeeMaker1, coffeeMakerCollection.getItem(0));
        assertEquals(coffeeMaker2, coffeeMakerCollection.getItem(1));
    }

    @Test
    void testClear() {
        coffeeMakerCollection.add(coffeeMaker1);
        coffeeMakerCollection.add(coffeeMaker2);
        
        assertEquals(2, coffeeMakerCollection.getSize());
        
        coffeeMakerCollection.clear();
        assertEquals(0, coffeeMakerCollection.getSize());
    }

    @Test
    void testDelete() {
        coffeeMakerCollection.add(coffeeMaker1);
        coffeeMakerCollection.add(coffeeMaker2);
        
        coffeeMakerCollection.delete(0);
        assertEquals(1, coffeeMakerCollection.getSize());
        assertEquals(coffeeMaker2, coffeeMakerCollection.getItem(0));
    }

    @Test
    void testUpdate() {
        coffeeMakerCollection.add(coffeeMaker1);
        coffeeMaker1.setID(0);
        
        CoffeeMaker updatedMaker = new CoffeeMaker("UpdatedBrand", "UpdatedModel", 1500, 250.99, 01, 01, 2020);
        updatedMaker.setID(coffeeMaker1.getID());
        coffeeMakerCollection.update(updatedMaker);
        
        assertEquals(updatedMaker, coffeeMakerCollection.getItem(0));
    }
}
