/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.famcs.JavaLaba4;

import javax.swing.SwingUtilities;

public class Java_Laba4 {

    public static void main(String[] args) 
    {

        CoffeeMakerCollection storage = new CoffeeMakerCollection();

        CLI cli = new CLI (storage);
        SwingUtilities.invokeLater(() -> new MainWindow(storage));

        cli.start();
        //обновить конструкторы Date до новейшей версии
        //yaml, CalculusParser, Unit tests
        
    }
}
