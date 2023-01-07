package org.ieti.Tristan;
import java.awt.*;
import javax.swing.*;


public class Main {

    private static final String[] COLUMN_NAMES = {"Cod",
            "Nota 1",
            "Nota 2",
            "Medie",
            "Admis"};

    private static final Object[][] DATA = {
            {"A20134", 5, 9, 1, "Da"},
            {"B20112", "3", "3", "3", "Nu"},
            {"C40323", "2", "5", "2", "Da"},
            {"B30221", "6", "5", "20", "Nu"},
            {"D20122", "3", "2", "10", "Nu"}
    };

    public static void main(String[] args) {

        JFrame container = new JFrame("Admitere");
        JTable table = new JTable(DATA, COLUMN_NAMES);
        table.setCellSelectionEnabled(true);

        ListSelectionListenerImpl listSelectionListener = new ListSelectionListenerImpl(table);

        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        select.addListSelectionListener(listSelectionListener);

        JScrollPane scrollPane = new JScrollPane(table);

        container.add(scrollPane);
        container.setLayout(new BorderLayout());
        container.add(table.getTableHeader(), BorderLayout.PAGE_START);
        container.add(table, BorderLayout.CENTER);
        container.setVisible(true);
        container.setSize(1920, 1080);


        /// meniu

        JMenu menu = new JMenu("Options");

        JMenuBar menuBar = new JMenuBar();

        JMenuItem a1 = new JMenuItem("Save");
        JMenuItem a2 = new JMenuItem("Save as");

        menu.add(a1);
        menu.add(a2);

        menuBar.add(menu);

        container.setJMenuBar(menuBar);
        container.setSize(1940, 1080);
        container.setLayout(null);
        container.setVisible(true);

        ///

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemAdd = new JMenuItem("Add New Row");
        JMenuItem menuItemRemove = new JMenuItem("Remove Current Row");
        JMenuItem menuItemRemoveAll = new JMenuItem("Remove All Rows");

        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
        table.setComponentPopupMenu(popupMenu);

        /// button

        JFrame frame = new JFrame("Adauga o coloana");
        JButton b = new JButton("Click");
        b.setBounds(80, 80, 80, 50);
        frame.add(b);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        JTextField d = new JTextField("Change me...");
        d.setBounds(50, 200, 200, 30);
        frame.add(d);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
