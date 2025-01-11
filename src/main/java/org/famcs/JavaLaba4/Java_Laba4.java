/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.famcs.JavaLaba4;

import javax.swing.SwingUtilities;

public class Java_Laba4 {

    public static void main(String[] args) 
    {

        CoffeeMakerList listStorage = new CoffeeMakerList();
        CoffeeMakerMap mapStorage = new CoffeeMakerMap();

        CLI cli = new CLI (listStorage, mapStorage);
        SwingUtilities.invokeLater(() -> new MainWindow(listStorage));

        cli.start();
        

        //для проекта удалить один из видов контейнеров (возможно)
        //обновить конструкторы Date до новейшей версии
        //добавить в настройки изменение директории сохранения по умолчанию
        
    }
}
