package org.famcs.JavaLaba4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame 
{

    private JTable table;
    private DefaultTableModel tableModel;
    private CoffeeMakerCollection storage;
    private DataReader reader;
    private DataWriter writer;
    private DataAccessManager manager;
    
    public MainWindow (CoffeeMakerCollection storage)
    {
        setTitle("CoffeeMaker Manager");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JFrame.setDefaultLookAndFeelDecorated(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("resources/icon.png");
        setIconImage(icon);

        this.storage = storage;
        generateUI();
        setVisible(true);
    }

    private void generateUI()
    {
        manager = DataAccessManager.getInstance();

        manager.initialize_read(DataAccessManager.currentFile);

        reader = manager.getDataReader();
        reader.read(storage);

        setMenuBar();
        this.setLayout(new BorderLayout());
        this.add(createMenuPanel(), BorderLayout.NORTH);
        this.add(createTable(), BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter()
        {
        @Override
        public void windowClosing(WindowEvent e)
        {
            manager.initialize_write(DataAccessManager.currentFile);
            writer = manager.getDataWriter();
            writer.write(storage);
        }
        });
    }

    public CoffeeMakerCollection getStorage ()
    {
        return storage;
    }

    private JPanel createMenuPanel()
    {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout (new GridLayout(1, 0, 0, 0));

        JButton addButton = new JButton("Add item");
        JButton updateButton = new JButton ("Update item");
        JButton deleteButton = new JButton("Delete item");
        JButton sortButton = new JButton("Sort by field");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 createInputPanel();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createInputPanel();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createDeletePanel();
            }
            
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createSortPanel();
            }
        });
        menuPanel.add(addButton);
        menuPanel.add(updateButton);
        menuPanel.add(deleteButton);
        menuPanel.add(sortButton);

        menuPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        return menuPanel;
    }

    private void setMenuBar()
    {
        MenuBar menuBar = new MenuBar(this);
        this.setJMenuBar(menuBar);
    }

    private JScrollPane createTable()
    {
        String[] columnNames = {"ID", "Brand", "Model", "Power", "Price", "Release date"};

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable (tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        updateTable();

        return tableScrollPane;
    }

    private void createInputPanel()
    {
         JDialog dialog = new JDialog(this, "CoffeeMaker Input Window", false);
         dialog.setSize(300, 400);
         dialog.setLocationRelativeTo(this); 
         dialog.setResizable(false);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));

        JTextField idField = new JTextField (15);
        JTextField brandField = new JTextField (15);
        JTextField modelField = new JTextField (15);
        JTextField powerField = new JTextField (15);
        JTextField priceField = new JTextField (15);
        JTextField dayField = new JTextField (15);
        JTextField monthField = new JTextField(15);
        JTextField yearField = new JTextField (15);

        JLabel idLabel = new JLabel ("ID:");
        JLabel brandLabel = new JLabel ("Brand:");
        JLabel modelLabel = new JLabel ("Model:");
        JLabel powerLabel = new JLabel ("Power:");
        JLabel priceLabel = new JLabel ("Price:");
        JLabel dayLabel = new JLabel ("Day:");
        JLabel monthLabel = new JLabel ("Month:");
        JLabel yearLabel = new JLabel ("Year:");

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (5,5,5,5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //x - column, y - row
        constraints.gridx = 0;   constraints.gridy = 0;     inputPanel.add(idLabel, constraints); 
        constraints.gridx = 1;   constraints.gridy = 0;     inputPanel.add(idField, constraints);
        constraints.gridx = 0;   constraints.gridy = 1;     inputPanel.add(brandLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 1;     inputPanel.add(brandField, constraints);
        constraints.gridx = 0;   constraints.gridy = 2;     inputPanel.add(modelLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 2;     inputPanel.add(modelField, constraints);
        constraints.gridx = 0;   constraints.gridy = 3;     inputPanel.add(powerLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 3;     inputPanel.add(powerField, constraints);
        constraints.gridx = 0;   constraints.gridy = 4;     inputPanel.add(priceLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 4;     inputPanel.add(priceField, constraints);
        constraints.gridx = 0;   constraints.gridy = 5;     inputPanel.add(dayLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 5;     inputPanel.add(dayField, constraints);
        constraints.gridx = 0;   constraints.gridy = 6;     inputPanel.add(monthLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 6;     inputPanel.add(monthField, constraints);
        constraints.gridx = 0;   constraints.gridy = 7;     inputPanel.add(yearLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 7;     inputPanel.add(yearField, constraints);

        JButton submitButton = new JButton ("OK");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int ID = Integer.parseInt(idField.getText()); 
                String brand = brandField.getText();
                String model = modelField.getText();
                int power = Integer.parseInt(powerField.getText());
                double price = Double.parseDouble(priceField.getText());
                int day = Integer.parseInt(dayField.getText());
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

                CoffeeMaker obj = new CoffeeMaker (brand, model, power, price, day, month, year);
                obj.setID(ID);
                Date date = obj.getDate();
                String dateStr = Integer.toString(date.getDay()) + ":" + Integer.toString(date.getMonth()) + ":" + Integer.toString(date.getYear());
                if (ID>=tableModel.getRowCount())
                {
                    storage.add(obj);
                    tableModel.addRow(new Object[] {ID, brand, model, power, price, dateStr});
                }
                else if (ID >= 0 || ID < tableModel.getRowCount()) {
                    tableModel.setValueAt(ID, ID, 0);
                    tableModel.setValueAt(brand, ID, 1);
                    tableModel.setValueAt(model, ID, 2);
                    tableModel.setValueAt(power, ID, 3);
                    tableModel.setValueAt(price, ID, 4);
                    tableModel.setValueAt(dateStr, ID, 5);
                    storage.update(obj);
                }
                 dialog.dispose();
            }
        });
        dialog.add(inputPanel);
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    private void createDeletePanel()
    {
        JDialog dialog = new JDialog(this, "CoffeeMaker Delete Window", false);
        dialog.setSize(300, 200);
         dialog.setLocationRelativeTo(this); 
         dialog.setResizable(false);

        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));

         JPanel deletePanel = new JPanel ();
         deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.X_AXIS));
         deletePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

         JLabel deleteLabel = new JLabel ("Index of deleted row:");
         JTextField deleteField = new JTextField (5);

        deletePanel.add(deleteLabel);
        deletePanel.add(Box.createHorizontalStrut(20));
        deletePanel.add(deleteField);

        JButton submitButton = new JButton("OK");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(deleteField.getText());
                tableModel.removeRow(index);
                storage.delete(index);
                updateTable();
                dialog.dispose();
            }
        });

        dialog.add(deletePanel);
        dialog.add(submitButton);
        dialog.setVisible(true);

    }

    private void createSortPanel()
    {
        JDialog dialog = new JDialog(this, "Sorting Window", false);
        dialog.setSize(200, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));

        ButtonGroup sortGroup = new ButtonGroup();
        JRadioButton idButton = new JRadioButton();
        JRadioButton brandButton = new JRadioButton();
        JRadioButton modelButton = new JRadioButton();
        JRadioButton powerButton = new JRadioButton();
        JRadioButton priceButton = new JRadioButton();
        JRadioButton dateButton = new JRadioButton();

        idButton.setSelected(true);

        sortGroup.add(idButton);
        sortGroup.add(brandButton);
        sortGroup.add(modelButton);
        sortGroup.add(powerButton);
        sortGroup.add(priceButton);
        sortGroup.add(dateButton);

        JLabel idLabel = new JLabel ("Sort by ID");
        JLabel brandLabel = new JLabel ("Sort by brand");
        JLabel modelLabel = new JLabel ("Sort by model");
        JLabel powerLabel = new JLabel ("Sort by power");
        JLabel priceLabel = new JLabel ("Sort by price");
        JLabel dateLabel = new JLabel ("Sort by date");

        JPanel sortPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (5,5,5,5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //x - column, y - row
        constraints.gridx = 0;   constraints.gridy = 0;     sortPanel.add(idLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 0;     sortPanel.add(idButton, constraints);
        constraints.gridx = 0;   constraints.gridy = 1;     sortPanel.add(brandLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 1;     sortPanel.add(brandButton, constraints);
        constraints.gridx = 0;   constraints.gridy = 2;     sortPanel.add(modelLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 2;     sortPanel.add(modelButton, constraints);
        constraints.gridx = 0;   constraints.gridy = 3;     sortPanel.add(powerLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 3;     sortPanel.add(powerButton, constraints);
        constraints.gridx = 0;   constraints.gridy = 4;     sortPanel.add(priceLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 4;     sortPanel.add(priceButton, constraints);
        constraints.gridx = 0;   constraints.gridy = 5;     sortPanel.add(dateLabel, constraints);
        constraints.gridx = 1;   constraints.gridy = 5;     sortPanel.add(dateButton, constraints);

        JButton submitButton = new JButton ("OK");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(idButton.isSelected())
                {
                    storage.sort(1);
                    updateTable();
                }
                else if (brandButton.isSelected())
                {
                    storage.sort(2);
                    updateTable();
                }
                else if (modelButton.isSelected())
                {
                    storage.sort(3);
                    updateTable();
                }
                else if (powerButton.isSelected())
                {
                    storage.sort(4);
                    updateTable();
                }
                else if (priceButton.isSelected())
                {
                    storage.sort(5);
                    updateTable();
                }
                else if (dateButton.isSelected())
                {
                    storage.sort(6);
                    updateTable();
                }
                dialog.dispose();
            }
        });

        dialog.add(sortPanel);
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    public void updateTable()
    {
        tableModel.setRowCount(0);
            for (int i = 0; i < storage.getSize(); i++) {
                CoffeeMaker obj = (CoffeeMaker) storage.getItem(i);

                Date date = obj.getDate();
                String dateStr = Integer.toString(date.getDay()) + ":" + Integer.toString(date.getMonth()) + ":" + Integer.toString(date.getYear()+1900);

                tableModel.addRow(new Object[]{obj.getID(), obj.getBrand(), obj.getModel(), obj.getPower(), obj.getPrice(), dateStr});
            }
    }

    public void clearTable()
    {
        storage.clear();
        updateTable();
    }
}

