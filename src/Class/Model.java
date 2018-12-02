/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PraveenKumar
 */
public class Model extends AbstractTableModel {

    private String[] cols;
    private Object[][] rows;

    public Model(Object[][] data, String[] colname) {
        this.cols = colname;
        this.rows = data;
    }

    public Class getColumnClass(int col) {
        if (col == 0) {
            return Icon.class;
        } else {
            return getValueAt(0, col).getClass();
        }
    }

    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return this.cols[col];
    }
}
