package org.famcs.JavaLaba4;
import java.util.List;
public abstract class CoffeeMakerCollection 
{
    public abstract void add (CoffeeFabric obj);
    public abstract void delete (int index);
    public abstract void display ();
    public abstract int getSize();
    public abstract void update (CoffeeFabric obj);
    public abstract void sort (int choice);
    public abstract CoffeeFabric getItem (int index);
    public abstract List<CoffeeFabric> getList();
    public abstract void addFromList (List<CoffeeFabric> list);
    public abstract void clear();
    @Override
    public abstract String toString();
}
