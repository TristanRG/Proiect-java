package org.ieti.Tristan;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Main {

    private static final String[] COLUMN_NAMES = {"Cod",
            "Nota 1",
            "Nota 2",
            "Medie",
            "Admis"};

    private static final Object[][] DATA = {
            {"A20134", "5", "9", "1", "Da"},
            {"B20112", "3", "3", "3", "Nu"},
    };

    public static void main(String[] args) {

        DefaultTableModel model = new DefaultTableModel(DATA, COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        for (int i = 0; i < model.getRowCount(); i++) {
            updateMedieAndAdmis(model, i);
        }

        model.addTableModelListener(event -> {
            if (event.getColumn() == 1 || event.getColumn() == 2) {
                updateMedieAndAdmis(model, event.getFirstRow());
            }
        });

        JTable table = new JTable(model);
        table.setCellSelectionEnabled(true);
        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // Frame
        JFrame container = new JFrame("Admitere");
        container.add(scrollPane);
        container.setLayout(new BorderLayout());
        container.add(table.getTableHeader(), BorderLayout.PAGE_START);
        container.add(table, BorderLayout.CENTER);
        container.setSize(1920, 1080);
        container.setExtendedState(JFrame.MAXIMIZED_BOTH);
        container.setVisible(true);

        JButton button = new JButton("Show Highest Medie");
        button.addActionListener(event -> {
            // button
            double highestMedie = Double.MIN_VALUE;
            for (int i = 0; i < model.getRowCount(); i++) {
                double medie = Double.parseDouble(model.getValueAt(i, 3).toString());
                if (medie > highestMedie) {
                    highestMedie = medie;
                }
            }

            // dialog
            JOptionPane.showMessageDialog(container, "The highest medie is " + highestMedie);
        });
        container.add(button);
        button.setBounds(50,50,100,100);
        button.setSize(300, 300);
        button.setLayout(null);
        button.setVisible(true);

        // Menu
        JMenu menu = new JMenu("Options");

        JMenuBar menuBar = new JMenuBar();

        JMenuItem a1 = new JMenuItem("Save");
        JMenuItem a2 = new JMenuItem("Save as");

        menu.add(a1);
        menu.add(a2);

        menuBar.add(menu);

        container.setJMenuBar(menuBar);
        container.setSize(1920, 1080);
        container.setLayout(null);
        container.setVisible(true);

        // Popup
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItemAdd = new JMenuItem("Add New Row");
        menuItemAdd.addActionListener(event -> {
            model.addRow(new Object[]{"", "", "", "", ""});
        });

        JMenuItem menuItemRemove = new JMenuItem("Remove Current Row");
        menuItemRemove.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
            }
        });

        JMenuItem menuItemRemoveAll = new JMenuItem("Remove All Rows");
        menuItemRemoveAll.addActionListener(event -> {
            int rowCount = table.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                ((DefaultTableModel) table.getModel()).removeRow(i);
            }
        });

        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
        table.setComponentPopupMenu(popupMenu);

    }
    private static void updateMedieAndAdmis(DefaultTableModel model, int row) {
        double nota1 = Double.parseDouble(model.getValueAt(row, 1).toString());
        double nota2 = Double.parseDouble(model.getValueAt(row, 2).toString());
        double medie = (nota1 + nota2) / 2;
        model.setValueAt(medie, row, 3);
        model.setValueAt(medie >= 6 ? "Da" : "Nu", row, 4);
    }
}


