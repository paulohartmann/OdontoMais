/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Convenio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigos
 */
public class TabConvenio extends AbstractTableModel {

    private List<Convenio> datalist;
    private Boolean[] editcolumns = new Boolean[2];
    private String[] columns = {"Nome Convênio", "Disponível"};

    public TabConvenio(List<Convenio> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        super.fireTableDataChanged();
    }

    public void setColumns(String[] v) {
        this.columns = v;
    }

    public void setEditableColum(int col, Boolean edt) {
        editcolumns[col] = edt;
    }

    public void clearAll() {
        datalist.clear();
        super.fireTableDataChanged();
    }

    public void add(Convenio his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Convenio> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Convenio get(int rows) {
        if ((getRowCount() < rows) || (getRowCount() == 0)) {
            return null;
        } else {
            return datalist.get(rows);
        }
    }

    public void remove(int linha) {
        datalist.remove(linha);
        super.fireTableRowsDeleted(linha, linha);
    }

    public void remove(Convenio mem) {
        int pos = datalist.indexOf(mem);
        datalist.remove(mem);
        super.fireTableRowsDeleted(pos, pos);
    }

    @Override
    public String getColumnName(int col) {
        return this.columns[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return editcolumns[c];
    }

    @Override
    public int getRowCount() {
        if (datalist != null) {
            return datalist.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public void setValueAt(Object aValue, int r, int c) {
        /*nada edital ainda*/
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Convenio Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Nome Convênio")) {
                return Mem.getNome();
            }
            if (getColumnName(columnIndex).equals("Disponível")) {
                if (Mem.isSemValidade()) {
                    return "Desabilitado";
                } else {
                    return "Habilitado";
                }
            }
            return "";
        } else {
            return "";
        }

    }
}
