package org.famcs.JavaLaba4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CoffeeMakerMap extends CoffeeMakerCollection
{
    private Map<Integer, CoffeeFabric> map;

    public CoffeeMakerMap ()
    {
        map = new HashMap<>();
    }
    public CoffeeMakerMap (int size)
    {
        map = new HashMap<>(size);
    }

    @Override
    public void add(CoffeeFabric obj)
    {
        if (obj.getID()<=map.size())
        {
            map.put (obj.getID(), obj);
        }
        else
        {
            obj.setID(map.size());
            map.put(obj.getID(), obj);
        }
    }

    @Override
    public void delete(int index)
    {
        map.remove(index);
    }

    @Override
    public void display ()
    {
        if (!map.isEmpty())
        {
            Iterator<Integer> itr = map.keySet().iterator();
            while (itr.hasNext())
            {
                CoffeeFabric obj = map.get(itr.next());
                obj.Display();
            }
            System.out.println();
        }
        else 
        {
            System.out.println("There are no objects in the map");
        }
    }

    @Override
    public int getSize()
    {
        return map.size();
    }
    
    @Override
    public void update(CoffeeFabric obj)
    {
        map.put(obj.getID(), obj);
    }

    @Override
    public void sort(int choice)
    {
        switch (choice)
        {
            case 1: 
            Map<Integer, CoffeeFabric> trMap = new TreeMap<>(map);
            map = trMap;
            break;

            case 2:
            map = map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.comparing(CoffeeFabric::getBrand)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2)->key1, LinkedHashMap::new));
            break;

            case 3:
            map = map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.comparing(CoffeeFabric::getModel)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2)->key1, LinkedHashMap::new));
            break;

            case 4:
            map = map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(CoffeeFabric::getPower)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2)->key1, LinkedHashMap::new));
            break;

            case 5:
            map = map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(CoffeeFabric::getPrice)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2)->key1, LinkedHashMap::new));
            break;
            
            case 6:
            map = map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.comparing(CoffeeFabric::getDate)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2)->key1, LinkedHashMap::new));
            break;

            default:
            System.out.println("Incorrect input. Try again");
        }
    }
    @Override
    public String toString()
    {
        String str = "";
        if (!map.isEmpty())
        {
            Iterator<Integer> itr = map.keySet().iterator();
            while (itr.hasNext())
            {
                CoffeeFabric obj = map.get(itr.next());
                str += obj.toString() + "\n";
            }
        }
        return str;
    }

    @Override
    public CoffeeFabric getItem (int index)
    {
        return map.get(index);
    }
    @Override
    public List<CoffeeFabric> getList()
    {
        List<CoffeeFabric> list = new ArrayList<>(map.values());
        return list;
    }
    @Override
    public void addFromList(List<CoffeeFabric> list)
    {
        Map<Integer,CoffeeFabric> temp = list.stream().collect(Collectors.toMap (CoffeeFabric :: getID, value->value));
        map.clear();
        map.putAll(temp);
    }
    @Override
    public void clear()
    {
        map.clear();
    }
}