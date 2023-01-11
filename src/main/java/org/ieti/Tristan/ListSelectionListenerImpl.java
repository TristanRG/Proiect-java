package org.ieti.Tristan;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListSelectionListenerImpl implements ListSelectionListener {

    private final JTable table;

    public ListSelectionListenerImpl(JTable table) {
        this.table = table;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        StringBuilder value = new StringBuilder();
        for (int selectedRow : selectedRows) {
            for (int selectedColumn : selectedColumns) {
                double nota1 = Double.parseDouble(table.getValueAt(selectedRow, 1).toString());
                double nota2 = Double.parseDouble(table.getValueAt(selectedRow, 2).toString());
                double medie = (nota1 + nota2) / 2;
                table.setValueAt(medie, selectedRow, 3);
                table.setValueAt(medie >= 6 ? "Da" : "Nu", selectedRow, 4);
                value.append(table.getValueAt(selectedRow, selectedColumn));
            }
        }
        System.out.println("Table element selected is: " + value);
    }
}