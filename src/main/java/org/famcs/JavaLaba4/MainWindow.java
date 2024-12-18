package org.famcs.JavaLaba4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame 
{

    private JTable table;
    private DefaultTableModel tableModel;
    private CoffeeMakerCollection collection = new CoffeeMakerList();
    private DataReader reader;
    private DataWriter writer;
    private DataAccessManager manager;
    
    public MainWindow ()
    {
        setTitle("CoffeeMaker Manager");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        generateUI();
        setVisible(true);
    }

    private void generateUI()
    {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout (new GridLayout(0, 1, 50, 10));

        JLabel nameLabel = new JLabel("Action Menu", JLabel.CENTER);
        JButton addButton = new JButton("Add item");
        JButton updateButton = new JButton ("Update item");
        JButton deleteButton = new JButton("Delete item");
        JButton sortButton = new JButton("Sort by field");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 inputPanel();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                inputPanel();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deletePanel();
            }
            
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sortPanel();
            }
        });
        menuPanel.add(nameLabel);
        menuPanel.add(addButton);
        menuPanel.add(updateButton);
        menuPanel.add(deleteButton);
        menuPanel.add(sortButton);


    JRadioButton listRButton = new JRadioButton("List");
    JRadioButton mapRButton = new JRadioButton("Map");

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(1,0,25,25));
        containerPanel.add(listRButton);
        containerPanel.add(mapRButton);

        ButtonGroup RBgroup = new ButtonGroup();
        RBgroup.add (listRButton);
        RBgroup.add(mapRButton);
        listRButton.setSelected(true);

        listRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (listRButton.isSelected())
                {
                    collection = new CoffeeMakerList();
                }
            }
        });

        mapRButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (mapRButton.isSelected())
                {
                    collection = new CoffeeMakerMap();
                }
            }
        }));

        manager = DataAccessManager.getInstance();
        manager.initialize_read("input", "txt");
        reader = manager.getDataReader();
        reader.read(collection);

        String[] columnNames = {"ID", "Brand", "Model", "Power", "Price", "Release date"};

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable (tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        updateTable();
        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.WEST);
        this.add(containerPanel, BorderLayout.NORTH);
        this.add(tableScrollPane, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter()
        {
        @Override
        public void windowClosing(WindowEvent e)
        {
            manager.initialize_write("output", "txt");
            writer = manager.getDataWriter();
            writer.write(collection);

            manager.initialize_write("output", "json");
            writer = manager.getDataWriter();
            writer.write(collection);

            manager.initialize_write("output", "xml");
            writer = manager.getDataWriter();
            writer.write(collection);
        }
        });
    }

    private void inputPanel()
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
                int ID = Integer.parseInt(idField.getText()); //лишнее
                String brand = brandField.getText();
                String model = modelField.getText();
                int power = Integer.parseInt(powerField.getText());
                double price = Double.parseDouble(priceField.getText());
                int day = Integer.parseInt(dayField.getText());
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());
                //String dateStr = dayField.getText() + ":" + monthField.getText() + ":" + yearField.getText();

                CoffeeMaker obj = new CoffeeMaker (brand, model, power, price, day, month, year);
                obj.setID(ID);
                Date date = obj.getDate();
                String dateStr = Integer.toString(date.getDay()) + ":" + Integer.toString(date.getMonth()) + ":" + Integer.toString(date.getYear());
                if (ID>=tableModel.getRowCount())
                {
                    collection.add(obj);
                    tableModel.addRow(new Object[] {ID, brand, model, power, price, dateStr});
                }
                else if (ID >= 0 || ID < tableModel.getRowCount()) {
                    tableModel.setValueAt(ID, ID, 0);
                    tableModel.setValueAt(brand, ID, 1);
                    tableModel.setValueAt(model, ID, 2);
                    tableModel.setValueAt(power, ID, 3);
                    tableModel.setValueAt(price, ID, 4);
                    tableModel.setValueAt(dateStr, ID, 5);
                    collection.update(obj);
                }
                 dialog.dispose();
            }
        });
        dialog.add(inputPanel);
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    private void deletePanel()
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
                collection.delete(index);

                dialog.dispose();
            }
        });

        dialog.add(deletePanel);
        dialog.add(submitButton);
        dialog.setVisible(true);

    }

    private void sortPanel()
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
                    collection.sort(1);
                    updateTable();
                }
                else if (brandButton.isSelected())
                {
                    collection.sort(2);
                    updateTable();
                }
                else if (modelButton.isSelected())
                {
                    collection.sort(3);
                    updateTable();
                }
                else if (powerButton.isSelected())
                {
                    collection.sort(4);
                    updateTable();
                }
                else if (priceButton.isSelected())
                {
                    collection.sort(5);
                    updateTable();
                }
                else if (dateButton.isSelected())
                {
                    collection.sort(6);
                    updateTable();
                }
                dialog.dispose();
            }
        });

        dialog.add(sortPanel);
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    private void updateTable()
    {
        tableModel.setRowCount(0);
            for (int i = 0; i < collection.getSize(); i++) {
                CoffeeMaker obj = (CoffeeMaker) collection.getItem(i);

                Date date = obj.getDate();
                String dateStr = Integer.toString(date.getDay()) + ":" + Integer.toString(date.getMonth()) + ":" + Integer.toString(date.getYear()+1900);

                tableModel.addRow(new Object[]{obj.getID(), obj.getBrand(), obj.getModel(), obj.getPower(), obj.getPrice(), dateStr});
            }
    }
}

