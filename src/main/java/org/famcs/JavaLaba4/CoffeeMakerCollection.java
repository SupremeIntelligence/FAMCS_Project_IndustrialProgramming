package org.famcs.JavaLaba4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CoffeeMakerCollection
{
    private List<CoffeeFabric> list;

    public CoffeeMakerCollection ()
    {
        list = new ArrayList<>();
    }

    public CoffeeMakerCollection(int size)
    {
        list = new ArrayList<>(size);
    }

    public void add(CoffeeFabric obj)
    {
        if (obj.getID()<list.size())
        {
            obj.setID(list.size());
            list.add(obj);
        }
        else
        {
            list.add(obj);
        }
    }

    public void delete(int index)
    {
        list.remove(index);
        for (int i = index; i < list.size(); i++)
        {
            CoffeeFabric obj = list.get(i);
            obj.setID(i);
            list.set(i, obj);
        }
    }

    public int getSize()
    {
        return list.size();
    }

    public void update(CoffeeFabric obj)
    {
        list.set(obj.getID(), obj);
    }

    public void sort (int choice)
    {
        switch (choice)
        {
            case 1: 
            Collections.sort(list, Comparator.comparingInt(CoffeeFabric::getID));
            break;

            case 2:
            Collections.sort(list, Comparator.comparing(CoffeeFabric::getBrand));
            break;

            case 3:
            Collections.sort(list, Comparator.comparing(CoffeeFabric::getModel));
            break;

            case 4:
            Collections.sort(list, Comparator.comparingInt(CoffeeFabric::getPower));
            break;

            case 5:
            Collections.sort(list, Comparator.comparingDouble(CoffeeFabric::getPrice));
            break;
            
            case 6:
            Collections.sort(list, Comparator.comparing(CoffeeFabric::getDate));
            break;

            default:
            System.out.println("Incorrect input. Try again");
        }
    }

    @Override
    public String toString()
    {
        String str ="";
        if (!list.isEmpty())
        {
            Iterator<CoffeeFabric> itr = list.iterator();
            while (itr.hasNext())
            {
                CoffeeFabric obj = itr.next();
                str += obj.toString() + "\n";
            }
        }
        return str;
    }

    public CoffeeFabric getItem(int index)
    {
        return list.get(index);
    }


    public List<CoffeeFabric> getList ()
    {
        return list;
    }

    public void addFromList (List<CoffeeFabric> obj)
    {
        for (CoffeeFabric item : obj)
        {
            list.add(item);
        }
    }

    public void clear()
    {
        list.clear();
    }
}
