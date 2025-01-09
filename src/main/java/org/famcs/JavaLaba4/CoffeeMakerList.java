package org.famcs.JavaLaba4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CoffeeMakerList extends CoffeeMakerCollection
{
    private List<CoffeeFabric> list;

    public CoffeeMakerList ()
    {
        list = new ArrayList<>();
    }

    public CoffeeMakerList(int size)
    {
        list = new ArrayList<>(size);
    }

    @Override
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

    @Override
    public void delete(int index)
    {
        list.remove(index);
    }

    @Override
    public void display ()
    {
        if (!list.isEmpty())
        {
            Iterator<CoffeeFabric> itr = list.iterator();
            while (itr.hasNext())
            {
                CoffeeFabric obj = itr.next();
                obj.Display();
            }
            System.out.println();
        }
        else
        {
            System.out.println ("There are no objects in the list");
        }
        
    }

    @Override
    public int getSize()
    {
        return list.size();
    }
    @Override
    public void update(CoffeeFabric obj)
    {
        list.set(obj.getID(), obj);
    }

    @Override
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
    @Override
    public CoffeeFabric getItem(int index)
    {
        return list.get(index);
    }

    @Override
    public List<CoffeeFabric> getList ()
    {
        return list;
    }
    @Override
    public void addFromList (List<CoffeeFabric> obj)
    {
        for (CoffeeFabric item : obj)
        {
            list.add(item);
        }
    }
    @Override
    public void clear()
    {
        list.clear();
    }
}
