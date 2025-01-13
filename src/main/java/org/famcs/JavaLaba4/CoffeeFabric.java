package org.famcs.JavaLaba4;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type" 
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CoffeeMaker.class, name = "CoffeeMaker")

})
public abstract class CoffeeFabric
{
    protected int ID;               //ID Обьекта
    protected static int count;     //Счетчик количества обьектов
    
    protected CoffeeFabric () 
    {
        this.ID = CoffeeFabric.count++;
    };
    @Override
    public abstract String toString();
    public abstract String getModel();
    public abstract void setModel (String model);
    public abstract String getBrand ();
    public abstract void setBrand(String brand);
    public abstract int getPower();
    public abstract void setPower(int value);
    public abstract double getPrice();
    public abstract void setPrice(double value);
    public abstract Date getDate();
    public abstract void setDate(Date date);

    public int getID()  {return this.ID;}

    public void setID(int id) {
        this.ID = id;
    }

    public static int getCount() {
        return CoffeeFabric.count;
    }

    public static void setCount(int count) {
        CoffeeFabric.count = count;
    }
}
