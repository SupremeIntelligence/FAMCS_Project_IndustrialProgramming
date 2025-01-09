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
        

        //Добавить метод isEmpty И применить его во всех методах вывода и сортировок
        //добавить метод clear
        //вынести консольное меню в отдельный класс CLI
        //для проекта удалить один из видов контейнеров (возможно)
        //обновить конструкторы Date до новейшей версии
        //добавить пункт вид
        //добавить в настройки изменение директории сохранения по умолчанию
        //сделать FilePath статической переменной
        
    }
}
