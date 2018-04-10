/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Clinica;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 *
 * @author rodrigos
 */
public class TabClinica extends AbstractTableModel {

    private ArrayList<Clinica> datalist;
    private Boolean[] editcolumns = new Boolean[7];
    private String[] columns = {"Nome",
        "Tel. Comercial",
        "Tel. Secundário",
        "Endereço",
        "Bairro",
        "Cidade",
        "Estado"};

    public TabClinica(ArrayList<Clinica> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        editcolumns[2] = false;
        editcolumns[3] = false;
        editcolumns[4] = false;
        editcolumns[5] = false;
        editcolumns[6] = false;
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

    public void add(Clinica his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Clinica> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Clinica get(int rows) {
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

    public void remove(Clinica mem) {
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
        Clinica Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Nome")) {
                return Mem.getNome();
            }
            if (getColumnName(columnIndex).equals("Tel. Comercial")) {
                return Mem.getTelComercial();
            }
            if (getColumnName(columnIndex).equals("Tel. Secundário")) {
                return Mem.getTelEmergencial();
            }
            if (getColumnName(columnIndex).equals("Endereço")) {
                return Mem.getEndereco();
            }
            if (getColumnName(columnIndex).equals("Bairro")) {
                return Mem.getBairro();
            }
            if (getColumnName(columnIndex).equals("Cidade")) {
                return Mem.getCidade();
            }
            if (getColumnName(columnIndex).equals("Estado")) {
                return Mem.getEstado();
            }
            return "";
        } else {
            return "";
        }

    }
}
