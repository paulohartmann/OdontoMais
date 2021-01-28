/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Paciente;
import odontomais.service.util.DataUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author rodrigos
 */
public class TabPaciente extends AbstractTableModel {

    private List<Paciente> datalist;
    private Boolean[] editcolumns = new Boolean[8];
    private String[] columns = {"Nome",
            "Dt. Nascimento",
            "Email",
            "Tel. Celular",
            "Tel. Residencial",
            "Tel. Trabalho",
            "CPF",
            "RG"};

    public TabPaciente(List<Paciente> l) {
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

    public void add(List<Paciente> l) {
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
                if (Mem.getNomeCompleto() != null) {
                    return Mem.getNomeCompleto();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("Dt. Nascimento")) {
                if (Mem.getDataNascimento() != null) {
                    return DataUtil.converteDataToString(Mem.getDataNascimento());
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("Tel. Celular")) {
                if (Mem.getTelCel() != null) {
                    return Mem.getTelCel();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("Tel. Residencial")) {
                if (Mem.getTelRes() != null) {
                    return Mem.getTelRes();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("Tel. Trabalho")) {
                if (Mem.getTelTrab() != null) {
                    return Mem.getTelTrab();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("Email")) {
                if (Mem.getEmail() != null) {
                    return Mem.getEmail();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("RG")) {
                if (Mem.getRg() != null) {
                    return Mem.getRg();
                } else {
                    return "";
                }
            }
            if (getColumnName(columnIndex).equals("CPF")) {
                if (Mem.getCpf() != null) {
                    return Mem.getCpf();
                } else {
                    return "";
                }
            }
            return "";
        } else {
            return "";
        }

    }
}
