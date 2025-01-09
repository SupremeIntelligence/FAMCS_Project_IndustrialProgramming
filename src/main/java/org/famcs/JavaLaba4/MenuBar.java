package org.famcs.JavaLaba4;

import java.awt.Image;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar 
{
    // private final String fileMenuData [][] = {
    //     {"Open", "resources/open.png"},
    //     {"Save", "resources/save.png"},
    //     {"Save as", "resources/save.png"},
    //     {"Archive", "resources/archive.png"},
    //     {"Archive as", "resoureces/archive.png"},
    //     {"Unarchive", "resources/archive.png"}
    // };

    public MenuBar ()
    {
        JMenu settingsMenu = new JMenu("Settings");
        JMenu helpMenu = new JMenu("Help");

        createFileMenu();
        createSettingsMenu();
        createHelpMenu();

        this.add(settingsMenu);
        this.add(helpMenu);
    }
        
    private void createFileMenu()
    {
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem saveAction = new JMenuItem("Save");
        JMenuItem saveAsAction = new JMenuItem("Save as");
        JMenuItem archiveAction = new JMenuItem("Archive");
        JMenuItem archiveAsAction = new JMenuItem("Archive as");
        JMenuItem openArchiveAction = new JMenuItem("Open archive");
        
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.add(archiveAction);
        fileMenu.add(archiveAsAction);
        fileMenu.add(openArchiveAction);
        

        openAction.setMnemonic('O');
        saveAction.setMnemonic('S');
        saveAsAction.setMnemonic('S');
        archiveAction.setMnemonic('A');
        archiveAsAction.setMnemonic('A');
        openArchiveAction.setMnemonic('O');

        openAction.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        saveAction.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        saveAsAction.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        archiveAction.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
        archiveAsAction.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        openArchiveAction.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        ImageIcon openIcon = new ImageIcon ("resources/open.png");
        Image image = openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        openAction.setIcon(scaledIcon);

        ImageIcon saveIcon = new ImageIcon("resources/save.png");
        image = saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(image);

        saveAction.setIcon(scaledIcon);
        saveAsAction.setIcon(scaledIcon);

        ImageIcon archiveIcon = new ImageIcon("resources/archive.png");
        image = archiveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(image);

        archiveAction.setIcon(scaledIcon);
        archiveAsAction.setIcon(scaledIcon);
        openArchiveAction.setIcon(scaledIcon);

        
        this.add(fileMenu);
    }

    private void createSettingsMenu()
    {

    }

    private void createHelpMenu()
    {
        
    }
}
