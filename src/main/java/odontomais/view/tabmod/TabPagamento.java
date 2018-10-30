/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Pagamento;
import odontomais.service.util.DataUtil;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigos
 */
public class TabPagamento extends AbstractTableModel {

    private List<Pagamento> datalist;
    private Boolean[] editcolumns = new Boolean[7];
    private String[] columns = {"Paciente", "Forma Pgmto.", "Profissional", "Tratamento", "Data", "Débito", "Pagamento"};

    public TabPagamento(List<Pagamento> l) {
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

    public void add(Pagamento his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Pagamento> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Pagamento get(int rows) {
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

    public void remove(Pagamento mem) {
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
        Pagamento Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Paciente")) {
                return Mem.getPaciente().getNomeCompleto();
            }
            if (getColumnName(columnIndex).equals("Forma Pgmto.")) {
                return Mem.getFormaPagamento();
            }
            if (getColumnName(columnIndex).equals("Profissional")) {
                return Mem.getProfissional();
            }
            if (getColumnName(columnIndex).equals("Tratamento")) {
                return Mem.getTratamento();
            }
            if (getColumnName(columnIndex).equals("Data")) {
                return DataUtil.converteDataToString(Mem.getDataHora());
            }
            if (getColumnName(columnIndex).equals("Débito")) {
                if (Mem.getDebito().compareTo(new BigDecimal(0)) > 0) {
                    return Mem.getDebito();
                }
            }
            if (getColumnName(columnIndex).equals("Pagamento")) {
                if (Mem.getDebito().compareTo(new BigDecimal(0)) <= 0) {
                    return Mem.getDebito();
                }
            }
            return "";
        } else {
            return "";
        }

    }
}
