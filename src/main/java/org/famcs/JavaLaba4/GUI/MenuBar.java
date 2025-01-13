package org.famcs.JavaLaba4.GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.famcs.JavaLaba4.*;
import org.famcs.JavaLaba4.FileManagement.Archiver;
import org.famcs.JavaLaba4.FileManagement.DataAccessManager;
import org.famcs.JavaLaba4.FileManagement.DataReader;
import org.famcs.JavaLaba4.FileManagement.DataWriter;

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
        JMenu archiveMenu = new JMenu("Archive");
        JMenuItem openArchiveAction = new JMenuItem("Open archive");
        JMenuItem encryptAction = new JMenuItem ("Encrypt");
        JMenuItem decryptAction = new JMenuItem ("Decrypt");
        
        JMenuItem ZipArchiveAction = new JMenuItem ("Add to .ZIP");
        JMenuItem JarArchiveAction = new JMenuItem ("Add to .JAR");
        
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.add(archiveMenu);
        fileMenu.add(openArchiveAction);
        fileMenu.add (encryptAction);
        fileMenu.add (decryptAction);

        archiveMenu.add(ZipArchiveAction);
        archiveMenu.add(JarArchiveAction);
        
        openAction.setMnemonic('O');
        saveAction.setMnemonic('S');
        saveAsAction.setMnemonic('S');
        archiveMenu.setMnemonic('A');
        openArchiveAction.setMnemonic('O');
        encryptAction.setMnemonic('E');
        decryptAction.setMnemonic('D');

        openAction.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        saveAction.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        saveAsAction.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        openArchiveAction.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        encryptAction.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        decryptAction.setAccelerator(KeyStroke.getKeyStroke('D', InputEvent.CTRL_DOWN_MASK));

        setIcon(openAction, "resources/open.png");
        setIcon(saveAction, "resources/save.png");
        setIcon(saveAsAction, "resources/save.png");
        setIcon(archiveMenu, "resources/archive.png");
        setIcon(openArchiveAction, "resources/archive.png");
        setIcon(ZipArchiveAction, "resources/zip.png");
        setIcon(JarArchiveAction, "resources/jar.png");
        setIcon(encryptAction, "resources/encrypt.png");
        setIcon(decryptAction, "resources/decrypt.png");
        
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
                SaveAs();
            }
        });

        openArchiveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenArchive();
            }
        });


        encryptAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Encrypt();
            }
        });

        decryptAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Decrypt();
            }
        });
        ZipArchiveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Archive(DataAccessManager.currentFile, Archiver.ArchiveTypes.ZIP);
            }
        });

        JarArchiveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Archive(DataAccessManager.currentFile, Archiver.ArchiveTypes.JAR);
            }
        });
        this.add(fileMenu);
    }

    private void createOptionsMenu()
    {
        JMenu optionsMenu = new JMenu ("Options");

        JMenuItem settingsAction = new JMenuItem ("Settings");
        JMenu themeMenu = new JMenu ("Theme");

        optionsMenu.add(settingsAction);
        optionsMenu.add(themeMenu);

        setIcon(settingsAction, "resources/settings.png");
        setIcon(themeMenu, "resources/theme.png");
        

        JRadioButtonMenuItem lightButton = new JRadioButtonMenuItem("Light");
        JRadioButtonMenuItem darkButton = new JRadioButtonMenuItem("Dark");

        ButtonGroup group = new ButtonGroup();
            group.add(lightButton);
            group.add(darkButton);

        lightButton.setSelected(true);

        themeMenu.add(lightButton);
        themeMenu.add(darkButton);

        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                owner.setLightTheme();
            }
        });

        darkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                owner.setDarkTheme();
            }
        });


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
            <html>Developed by Alexander Evdokimov
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
                    DataAccessManager.getInstance().initialize_read(file.getAbsolutePath());
                    DataReader reader = DataAccessManager.getInstance().getDataReader();
                    CoffeeMakerCollection storage = owner.getStorage();
                    storage.clear();
                    reader.read (owner.getStorage());
                    owner.updateTable();

            }
     }

     protected void Save()
     {
        DataAccessManager.getInstance().initialize_write(DataAccessManager.currentFile);
        DataWriter writer = DataAccessManager.getInstance().getDataWriter();
        writer.write(owner.getStorage());
     }

    private void SaveAs()
    {
        JFileChooser fileChooser = new JFileChooser(DataAccessManager.projectDir.toString());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Text, JSON, XML, YAML files", "txt", "json", "xml", "yaml"));
        int result = fileChooser.showSaveDialog(owner);

        File file;
        if(result == JFileChooser.APPROVE_OPTION)
        {
            
            file = fileChooser.getSelectedFile();

            String filePath = file.getAbsolutePath();

            if (file.isDirectory())
            {
                filePath += "\\default.txt";
                File newFile = new File (filePath);
                while (newFile.exists())
                {
                    
                    String regex = "\\(\\d+\\)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(filePath);

                    if (matcher.find())
                    {
                       String counterStr = matcher.group();
                       String numberStr = counterStr.substring(1, counterStr.length() - 1);
                       
                       int counter = Integer.parseInt(numberStr);
                       counter++;
                       filePath = filePath.replaceAll(regex, "(" + Integer.toString(counter) + ")");
                    }
                    else 
                    {
                        int dotIndex = filePath.lastIndexOf('.');
                        StringBuilder newFilePath = new StringBuilder(filePath);
                        newFilePath.insert(dotIndex - 1, " (1)");

                        filePath = newFilePath.toString();
                    }

                    newFile = new File (filePath);
                }
            }
            DataAccessManager.getInstance().initialize_write(filePath);
            DataWriter writer = DataAccessManager.getInstance().getDataWriter();
            writer.write(owner.getStorage());
        }
    }

    private void Archive(String archivedFilePath, Archiver.ArchiveTypes archiveType)
    {
        int dotIndex = archivedFilePath.lastIndexOf(".");
        String archivePath = archivedFilePath.substring(0, dotIndex);

        if (archiveType == Archiver.ArchiveTypes.ZIP)
        {
            archivePath += ".zip";
            Archiver.zipArchive(archivedFilePath, archivePath);
        }
        else if (archiveType == Archiver.ArchiveTypes.JAR)
        {
            archivePath += ".jar";
            Archiver.jarArchive(archivedFilePath, archivePath);
        }
        JOptionPane.showMessageDialog(owner,  "File successfully archived.", "Operation Completed", JOptionPane.INFORMATION_MESSAGE);
    }

    private void OpenArchive()
    {
        JFileChooser fileChooser = new JFileChooser(DataAccessManager.projectDir.toString());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "ZIP, JAR files", "zip", "jar"));
        int result = fileChooser.showSaveDialog(owner);

        File file;
        if(result == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();

            String archivePath = file.getAbsolutePath();

            int dotIndex = archivePath.lastIndexOf(".");
            String archiveType = archivePath.substring(dotIndex +1);

            if (archiveType.equalsIgnoreCase("zip"))
            {   
                Archiver.ZipUnarchive(archivePath);
                JOptionPane.showMessageDialog(owner,  "File successfully unarchived.", "Operation Completed", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (archiveType.equalsIgnoreCase("jar"))
            {
                Archiver.JarUnarchive(archivePath);
                JOptionPane.showMessageDialog(owner,  "File successfully unarchived.", "Operation Completed", JOptionPane.INFORMATION_MESSAGE);
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Invalid archive type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setIcon(JMenuItem menuItem, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItem.setIcon(new ImageIcon(image));
    }

    private void Encrypt ()
    {
        Save();
        Encryptor encryptor = new Encryptor();
        encryptor.encrypt(DataAccessManager.currentFile);
        JOptionPane.showMessageDialog(owner,  "File successfully saved and encrypted.", "Operation Completed", JOptionPane.INFORMATION_MESSAGE);
        owner.clearTable();
    }

    private void Decrypt()
    {
        JFileChooser fileChooser = new JFileChooser(DataAccessManager.projectDir.toString());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "TXT, XML, JSON, ZIP, JAR files", "txt", "xml", "json", "yaml", "zip", "jar"));
        int result = fileChooser.showSaveDialog(owner);
        File file;
        if(result == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            Encryptor encryptor = new Encryptor();
            encryptor.decrypt(file.getAbsolutePath());
            JOptionPane.showMessageDialog(owner,  "File successfully decrypted.", "Operation Completed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
