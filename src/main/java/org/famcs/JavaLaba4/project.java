
package org.famcs.JavaLaba4;

import javax.swing.SwingUtilities;

import org.famcs.JavaLaba4.GUI.MainWindow;

public class Java_Laba4 {

    public static void main(String[] args) 
    {

        CoffeeMakerCollection storage = new CoffeeMakerCollection();

        CLI cli = new CLI (storage);
        SwingUtilities.invokeLater(() -> new MainWindow(storage));

        cli.start();
        
    }
}
