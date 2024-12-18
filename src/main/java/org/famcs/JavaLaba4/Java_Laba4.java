/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.famcs.JavaLaba4;

import java.util.Scanner;

import javax.swing.SwingUtilities;




public class Java_Laba4 {

    public static void main(String[] args) 
    {



    SwingUtilities.invokeLater(MainWindow::new);

        CoffeeMakerList list = new CoffeeMakerList();
        CoffeeMakerMap map = new CoffeeMakerMap();


        // CoffeeMaker obj1 = new CoffeeMaker("Bosch", "PP-123", 120, 300);
        //CoffeeMaker obj2 = new CoffeeMaker();
        // CoffeeMaker obj3 = new CoffeeMaker("Samsung", "S21 Ultra", 240, 2150.2, 16, 2, 2017 - 1900);
        // CoffeeMaker obj4 = new CoffeeMaker("Xiaomi", "15 Pro", 1000, 100,29,10,2024-1900);
        //CoffeeMaker obj5 = new CoffeeMaker("Xiaomi", "17 Pro Max", 1100, 150,29,10,2024-1900);
        //JSONWriter writer = new JSONWriter("output.json");
        //writer.write(obj5);
        // System.out.println(obj2);
        // JSONReader reader = new JSONReader ("input.json");
        // reader.read(obj2);
        // System.out.println(obj2);
        //Добавить метод isEmpty И применить его во всех методах вывода и сортировок
        //добавить метод clear
        String menu = """
                      Choose your preffered data type:
                      1.List
                      2.Map
                      3.Exit
                      """;
        String submenu = """
                         Choose option:
                         1.Read from file
                         2.Write to file
                         3.Display data
                         4.Add item
                         5.Update item
                         6.Delete item
                         7.Sort data by field
                         8.Archive
                         9.Encrypt
                         10.Decrypt
                         11.Return to main menu
                         """;
        String sortmenu = """
                          Choose sort mode: 
                          1. Sort by ID
                          2. Sort by brand
                          3. Sort by model
                          4. Sort by power
                          5. Sort by price
                          6. Sort by release date.""";

        String filemenu = """
                Choose file extension:
                1.TXT file
                2.XML file
                3.JSON file
                """;

        String archivemenu = """
                Choose archive extension:
                1.ZIP
                2.JAR
                """;

        DataAccessManager dataManager;// = DataAccessManager.getInstance();

        DataReader input;
        DataWriter out;
        Encryptor encryptor = new Encryptor();

        System.out.println(menu);
        int choice;
        int subChoice;
        int sortChoice;
        int fileChoice;
        int archiveChoice;
        String zipname;
        int ID;
        Scanner scan = new Scanner (System.in);
        choice = scan.nextInt();

        while (choice != 3)
        {
            switch (choice)
            {
                case 1:
                    System.out.println (submenu);
                    subChoice = scan.nextInt();
                    while (subChoice != 11)
                    {
                        switch(subChoice)
                        {
                            case 1:
                            System.out.println(filemenu);
                            fileChoice = scan.nextInt();
                                switch(fileChoice)
                                {
                                    case 1:
                                    //dataManager.initialize_read("input", "txt");
                                    //input = dataManager.getDataReader();
                                    //input.read(list);
                                    System.out.println ("//Reading data from txt file//");
                                    break;

                                    case 2:
                                    //dataManager.initialize_read("input", "xml");
                                    //input = dataManager.getDataReader();
                                    //input.read (list);
                                    System.out.println("//Reading data from XML file");
                                    break;

                                    case 3:
                                   // dataManager.initialize_read("input", "json");
                                    //input = dataManager.getDataReader();
                                    //input.read(list);
                                    System.out.println("//Reading data from JSON file");
                                    break;

                                    default:
                                    System.out.println("Incorrect input.");
                                    break;
                                }
                                subChoice = scan.nextInt();
                                break;
                            case 2:
                            System.out.println(filemenu);
                            fileChoice = scan.nextInt();
                                switch(fileChoice)
                                {
                                    case 1:
                                   // dataManager.initialize_write("output", "txt");
                                    //out = dataManager.getDataWriter();
                                    //out.write(list);
                                    System.out.println ("//Writing data to txt file//");
                                    break;

                                    case 2:
                                    //dataManager.initialize_write("output", "xml");
                                    //out = dataManager.getDataWriter();
                                    //out.write(list);
                                    System.out.println("//Writing data to XML file");
                                    break;

                                    case 3:
                                   // dataManager.initialize_write("output", "json");
                                    //out = dataManager.getDataWriter();
                                    //out.write(list);
                                    System.out.println("//Writing data to JSON file");
                                    break;

                                    default:
                                    System.out.println("Incorrect input.");
                                    break;
                                }
                                subChoice = scan.nextInt();
                                break;

                            case 3:
                                System.out.println (list);
                                subChoice = scan.nextInt();
                                break;

                            case 4:
                                CoffeeMaker newObj1 = new CoffeeMaker();
                                newObj1.Input(scan);
                                list.add(newObj1);
                                System.out.println("//Adding a new item//");
                                subChoice = scan.nextInt();
                                break;

                            case 5:
                                System.out.print("Enter the ID of the item you want to update: ");
                                ID = scan.nextInt();
                                CoffeeMaker updObj1 = new CoffeeMaker();
                                updObj1.Input(scan);
                                updObj1.setID(ID);
                                list.update(updObj1);
                                System.out.println("//Updating item//");
                                subChoice = scan.nextInt();
                                break;

                            case 6:
                                System.out.print("Enter the ID of the item you want to delete: ");
                                ID = scan.nextInt();
                                list.delete(ID);
                                System.out.println("//Deleting item//");
                                subChoice = scan.nextInt();
                                break;
                            case 7:
                                System.out.println(sortmenu);
                                sortChoice = scan.nextInt();
                                System.out.println ("//Sorting data//");
                                list.sort(sortChoice);
                                subChoice = scan.nextInt();
                                break;
                            case 8:
                                System.out.println ("Enter the name of the archive: ");
                                scan.nextLine();
                                zipname = scan.nextLine();
                                System.out.println(archivemenu);
                                archiveChoice = scan.nextInt();
                                switch(archiveChoice)
                                {
                                    case 1:
                                    zipname = zipname + ".zip";
                                    //out.zipArchive(zipname);
                                    System.out.println ("//Zip data archiving//");
                                    break;
                                
                                    case 2:
                                    zipname = zipname + ".jar";
                                    //out.jarArchive(zipname);
                                    System.out.println ("//Jar data archiving//");
                                    break;
                                        
                                    default:
                                    System.out.println("Incorrect input.");
                                    break;
                                }
                                subChoice = scan.nextInt();
                                break;
                            case 9:
                                encryptor.encrypt("output.txt", "encrypted.txt");
                                System.out.println ("//Data encryption//");
                                subChoice = scan.nextInt();
                                break;
                            
                            case 10:
                                encryptor.decrypt("encrypted.txt", "decrypted.txt");
                                System.out.println ("//Data decryption//");
                                subChoice = scan.nextInt();
                                break;

                            default:
                                System.out.print("Incorrect input. Try again:\t");
                                subChoice = scan.nextInt();
                                break;

                        }
                    }
                    System.out.println(menu);
                    choice = scan.nextInt();
                    break;
                case 2:
                    System.out.println (submenu);
                    subChoice = scan.nextInt();
                    while (subChoice != 11)
                    {
                        switch(subChoice)
                        {
                        case 1:
                        System.out.println(filemenu);
                        fileChoice = scan.nextInt();
                            switch(fileChoice)
                            {
                                case 1:
                                //dataManager.initialize_read("input", "txt");
                                    //input = dataManager.getDataReader();
                                //input.read(map);
                                System.out.println ("//Reading data from txt file//");
                                break;

                                case 2:
                                //dataManager.initialize_read("input", "xml");
                                //input = dataManager.getDataReader();
                                //input.read (map);
                                System.out.println("//Reading data from XML file");
                                break;

                                case 3:
                                //dataManager.initialize_read("input", "json");
                                 //   input = dataManager.getDataReader();
                                //input.read(map);
                                System.out.println("//Reading data from JSON file");
                                break;

                                default:
                                System.out.println("Incorrect input.");
                                break;
                            }
                            subChoice = scan.nextInt();
                            break;
                        case 2:
                        System.out.println(filemenu);
                        fileChoice = scan.nextInt();
                            switch(fileChoice)
                            {
                                case 1:
                                //dataManager.initialize_write("output", "txt");
                                //out = dataManager.getDataWriter();
                                //out.write(map);
                                System.out.println ("//Writing data to txt file//");
                                break;

                                case 2:
                                //dataManager.initialize_write("output", "xml");
                                // = dataManager.getDataWriter();
                                //out.write(map);
                                System.out.println("//Writing data to XML file");
                                break;

                                case 3:
                                //dataManager.initialize_write("output", "json");
                                //out = dataManager.getDataWriter();
                                //out.write(map);
                                System.out.println("//Writing data to JSON file");
                                break;

                                default:
                                System.out.println("Incorrect input.");
                                break;
                            }
                            subChoice = scan.nextInt();
                            break;

                        case 3:
                            System.out.println (map);
                            subChoice = scan.nextInt();
                            break;

                        case 4:
                            CoffeeMaker newObj2 = new CoffeeMaker();
                            newObj2.Input(scan);
                            map.add(newObj2);
                            System.out.println("//Adding a new item//");
                            subChoice = scan.nextInt();
                            break;

                        case 5:
                            System.out.print("Enter the ID of the item you want to update: ");
                            ID = scan.nextInt();
                            CoffeeMaker updObj2 = new CoffeeMaker();
                            updObj2.Input(scan);
                            updObj2.setID(ID);
                            map.update(updObj2);
                            System.out.println("//Updating item//");
                            subChoice = scan.nextInt();
                            break;

                        case 6:
                            System.out.print("Enter the ID of the item you want to delete: ");
                            ID = scan.nextInt();
                            map.delete(ID);
                            System.out.println("//Deleting item//");
                            subChoice = scan.nextInt();
                            break;
                        case 7:
                            System.out.println(sortmenu);
                            sortChoice = scan.nextInt();
                            System.out.println ("//Sorting data//");
                            map.sort(sortChoice);
                            subChoice = scan.nextInt();
                            break;
                        case 8:
                            System.out.println ("Enter the name of the archive: ");
                            scan.nextLine();
                            zipname = scan.nextLine();
                            System.out.println(archivemenu);
                            archiveChoice = scan.nextInt();
                            switch(archiveChoice)
                            {
                                case 1:
                                zipname = zipname + ".zip";
                                //out.zipArchive(zipname);
                                System.out.println ("//Zip data archiving//");
                                break;

                                case 2:
                                zipname = zipname + ".jar";
                                //out.jarArchive(zipname);
                                System.out.println ("//Jar data archiving//");
                                break;

                                default:
                                System.out.println("Incorrect input.");
                                break;
                            }
                            subChoice = scan.nextInt();
                            break;

                            case 9:
                            encryptor.encrypt("output.txt", "encrypted.txt");
                            System.out.println ("//Data encryption//");
                            subChoice = scan.nextInt();
                            break;
                        
                        case 10:
                            encryptor.decrypt("encrypted.txt", "decrypted.txt");
                            System.out.println ("//Data decryption//");
                            subChoice = scan.nextInt();
                            break;

                        default:
                            System.out.print("Incorrect input. Try again:\t");
                            subChoice = scan.nextInt();
                            break;
                        }
                    }
                    System.out.println(menu);
                    choice = scan.nextInt();
                    break;

                default:
                    System.out.print("Incorrect input. Try again:");
                    choice = scan.nextInt();
                    break;
            }
        }
    }
}
