/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import odontomais.model.Paciente;
import odontomais.model.util.DataUtil;

/**
 *
 * @author rodrigos
 */
public class TabPaciente extends AbstractTableModel {

    private ArrayList<Paciente> datalist;
    private Boolean[] editcolumns = new Boolean[8];
    private String[] columns = {"Nome",
        "Dt. Nascimento",
        "Email",
        "Tel. Celular",
        "Tel. Residencial",
        "Tel. Trabalho",
        "CPF",
        "RG"};

    public TabPaciente(ArrayList<Paciente> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        editcolumns[2] = false;
        editcolumns[3] = false;
        editcolumns[4] = false;
        editcolumns[5] = false;
        editcolumns[6] = false;
        editcolumns[7] = false;
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

    public void add(Paciente his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Paciente> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Paciente get(int rows) {
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

    public void remove(Paciente mem) {
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
        Paciente Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Nome")) {
                return Mem.getNomeCompleto();
            }
            if (getColumnName(columnIndex).equals("Dt. Nascimento")) {
                return DataUtil.converteDataToString(Mem.getDataNascimento());
            }
            if (getColumnName(columnIndex).equals("Tel. Celular")) {
                return Mem.getTelCel();
            }
            if (getColumnName(columnIndex).equals("Tel. Residencial")) {
                return Mem.getTelRes();
            }
            if (getColumnName(columnIndex).equals("Tel. Trabalho")) {
                return Mem.getTelTrab();
            }
            if (getColumnName(columnIndex).equals("Email")) {
                return Mem.getEmail();
            }
            if (getColumnName(columnIndex).equals("RG")) {
                return Mem.getRg();
            }
            if (getColumnName(columnIndex).equals("CPF")) {
                return Mem.getCpf();
            }
            return "";
        } else {
            return "";
        }

    }
}
