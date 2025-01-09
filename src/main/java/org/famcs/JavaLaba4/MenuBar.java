package org.famcs.JavaLaba4;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MenuBar extends JMenuBar 
{
    private final MainWindow owner;
    // private final String fileMenuData [][] = {
    //     {"Open", "resources/open.png"},
    //     {"Save", "resources/save.png"},
    //     {"Save as", "resources/save.png"},
    //     {"Archive", "resources/archive.png"},
    //     {"Archive as", "resoureces/archive.png"},
    //     {"Unarchive", "resources/archive.png"}
    // };

    public MenuBar (MainWindow owner)
    {
        this.owner = owner;
        createFileMenu();
        createOptionsMenu();
        createHelpMenu();
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

        openAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Open();
            }
        });

        saveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Save();
            }
        });

        saveAsAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Save();
            }
        });

        archiveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Archive();
            }
        });

        archiveAsAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Archive();
            }
        });

        openArchiveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenArchive();
            }
        });

        archiveAction.setIcon(scaledIcon);
        archiveAsAction.setIcon(scaledIcon);
        openArchiveAction.setIcon(scaledIcon);
        
        this.add(fileMenu);
    }

    private void createOptionsMenu()
    {
        JMenu optionsMenu = new JMenu ("Options");

        JMenuItem settingsAction = new JMenuItem ("Settings");
        JMenuItem themeAction = new JMenuItem ("Theme");

        optionsMenu.add(settingsAction);
        optionsMenu.add(themeAction);

        ImageIcon settingsIcon = new ImageIcon ("resources/settings.png");
        Image image = settingsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        settingsAction.setIcon(scaledIcon);

        ImageIcon themeIcon = new ImageIcon ("resources/theme.png");
        image = themeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(image);
        themeAction.setIcon(scaledIcon);

        this.add(optionsMenu);
    }

    private void createHelpMenu()
    {
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutAction = new JMenuItem("About");
        aboutAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createAboutPanel();
            }
        });


        ImageIcon aboutIcon = new ImageIcon ("resources/about.png");
        Image image = aboutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        aboutAction.setIcon(scaledIcon);

        helpMenu.add(aboutAction);
        this.add(helpMenu);
    }

    private void createAboutPanel()
    {
        JDialog dialog = new JDialog(owner, "About", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(owner);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel ("<html><b><span style='font-size:16px;'>Welcome to the app!</span></b></html>");
        JLabel textLabel = new JLabel("""
            <html>Developed by Alexandr Evdokimov
            <br>Version 1.0b</html>""");

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20,5,5,0));
        textLabel.setBorder(BorderFactory.createEmptyBorder(0,5,5,20));

        dialog.add(welcomeLabel, BorderLayout.NORTH);
        dialog.add(textLabel, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    private void Open()
    {
        JFileChooser fileChooser = new JFileChooser(DataAccessManager.projectDir.toString());
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Text, JSON, XML files", "txt", "json", "xml"));

        int result = fileChooser.showOpenDialog(owner);
        File file;
        if (result == JFileChooser.APPROVE_OPTION) 
            {
                    file = fileChooser.getSelectedFile();
                    
                    String fullFilename = file.getName();

                    int dotIndex = fullFilename.lastIndexOf('.');
                    String filename = fullFilename.substring(0, dotIndex);
                    String fileType = fullFilename.substring(dotIndex+1);

                    DataAccessManager.getInstance().initialize_read(filename, fileType);
                    DataReader reader = DataAccessManager.getInstance().getDataReader();
                    CoffeeMakerCollection storage = owner.getStorage();
                    storage.clear();
                    reader.read (owner.getStorage());
                    owner.updateTable();

            }
     }

    private void Save()
    {

    }

    private void Archive()
    {

    }

    private void OpenArchive()
    {

    }

}
