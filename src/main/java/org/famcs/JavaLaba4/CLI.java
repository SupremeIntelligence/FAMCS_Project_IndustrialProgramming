package org.famcs.JavaLaba4;

import java.util.Scanner;

public class CLI 
{
    private final String menu = """
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
           11.Exit
           """;
    private final String sortmenu = """
            Choose sort mode: 
            1. Sort by ID
            2. Sort by brand
            3. Sort by model
            4. Sort by power
            5. Sort by price
            6. Sort by release date.""";

    private final String filemenu = """
  Choose file extension:
  1.TXT file
  2.XML file
  3.JSON file
  """;

    private final String archivemenu = """
  Choose archive extension:
  1.ZIP
  2.JAR
  """;

    private CoffeeMakerCollection list;

    CLI (CoffeeMakerCollection list)
    {
      this.list = list;
    }

  public void start()
  {
    DataAccessManager dataManager = DataAccessManager.getInstance();

    DataReader input;
    DataWriter out;
    Encryptor encryptor = new Encryptor();

        System.out.println(menu);
        int choice;
        int sortChoice;
        int fileChoice;
        int archiveChoice;
        String archivePath;
        int ID;
        Scanner scan = new Scanner (System.in);
        System.out.println (menu);
        choice = scan.nextInt();
        while (choice != 11)
            {
                    switch(choice)
                        {
                            case 1:
                            System.out.println(filemenu);
                            fileChoice = scan.nextInt();
                            switch(fileChoice)
                            {
                                case 1:
                                dataManager.initialize_read("input.txt");
                                input = dataManager.getDataReader();
                                input.read(list);
                                System.out.println ("//Reading data from txt file//");
                                break;

                                case 2:
                                dataManager.initialize_read("input.xml");
                                input = dataManager.getDataReader();
                                input.read (list);
                                System.out.println("//Reading data from XML file");
                                break;

                                case 3:
                                dataManager.initialize_read("input.json");
                                input = dataManager.getDataReader();
                                input.read(list);
                                System.out.println("//Reading data from JSON file");
                                break;

                                default:
                                System.out.println("Incorrect input.");
                                break;
                            }
                                choice = scan.nextInt();
                                break;
                            case 2:
                            System.out.println(filemenu);
                            fileChoice = scan.nextInt();
                                switch(fileChoice)
                                {
                                    case 1:
                                    dataManager.initialize_write("output.txt");
                                    out = dataManager.getDataWriter();
                                    out.write(list);
                                    System.out.println ("//Writing data to txt file//");
                                    break;

                                    case 2:
                                    dataManager.initialize_write("output.xml");
                                    out = dataManager.getDataWriter();
                                    out.write(list);
                                    System.out.println("//Writing data to XML file");
                                    break;

                                    case 3:
                                    dataManager.initialize_write("output.json");
                                    out = dataManager.getDataWriter();
                                    out.write(list);
                                    System.out.println("//Writing data to JSON file");
                                    break;

                                    default:
                                    System.out.println("Incorrect input.");
                                    break;
                                }
                                choice = scan.nextInt();
                                break;

                            case 3:
                                System.out.println (list);
                                choice = scan.nextInt();
                                break;

                            case 4:
                                CoffeeMaker newObj1 = new CoffeeMaker();
                                newObj1.Input(scan);
                                list.add(newObj1);
                                System.out.println("//Adding a new item//");
                                choice = scan.nextInt();
                                break;

                            case 5:
                                System.out.print("Enter the ID of the item you want to update: ");
                                ID = scan.nextInt();
                                CoffeeMaker updObj1 = new CoffeeMaker();
                                updObj1.Input(scan);
                                updObj1.setID(ID);
                                list.update(updObj1);
                                System.out.println("//Updating item//");
                                choice = scan.nextInt();
                                break;

                            case 6:
                                System.out.print("Enter the ID of the item you want to delete: ");
                                ID = scan.nextInt();
                                list.delete(ID);
                                System.out.println("//Deleting item//");
                                choice = scan.nextInt();
                                break;
                            case 7:
                                System.out.println(sortmenu);
                                sortChoice = scan.nextInt();
                                System.out.println ("//Sorting data//");
                                list.sort(sortChoice);
                                choice = scan.nextInt();
                                break;
                            case 8:
                                System.out.println ("Enter the name of the archive: ");
                                scan.nextLine();
                                archivePath = scan.nextLine();
                                System.out.println(archivemenu);
                                archiveChoice = scan.nextInt();
                                switch(archiveChoice)
                                {
                                    case 1:
                                    archivePath = DataAccessManager.projectDir.toString() + "\\" + archivePath + ".zip";
                                    Archiver.zipArchive("output.txt" ,archivePath);
                                    System.out.println ("//Zip data archiving//");
                                    break;
                                
                                    case 2:
                                    archivePath = DataAccessManager.projectDir.toString() + "\\" + archivePath + ".jar";
                                    Archiver.jarArchive("output.txt" ,archivePath);
                                    System.out.println ("//Jar data archiving//");
                                    break;
                                        
                                    default:
                                    System.out.println("Incorrect input.");
                                    break;
                                }
                                choice = scan.nextInt();
                                break;
                            case 9:
                                encryptor.encrypt("output.txt", "encrypted.txt");
                                System.out.println ("//Data encryption//");
                                choice = scan.nextInt();
                                break;
                            
                            case 10:
                                encryptor.decrypt("encrypted.txt", "decrypted.txt");
                                System.out.println ("//Data decryption//");
                                choice = scan.nextInt();
                                break;

                            default:
                                System.out.print("Incorrect input. Try again:\t");
                                choice = scan.nextInt();
                                break;
                        }
                    }
        }
    }
