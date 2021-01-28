/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.ContasPagar;
import odontomais.service.util.DataUtil;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paulohar
 */
public class TabContasPagar extends AbstractTableModel {

    private List<ContasPagar> datalist;
    private Boolean[] editcolumns = new Boolean[4];
    private String[] columns = {"Pagamento", "Valor", "Validade", "Pago"};

    public TabContasPagar(List<ContasPagar> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        editcolumns[2] = false;
        editcolumns[3] = false;
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

    public void add(ContasPagar his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<ContasPagar> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public ContasPagar get(int rows) {
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

    public void remove(ContasPagar mem) {
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
        ContasPagar Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Pagamento")) {
                return Mem.getDestinatario();
            }
            if (getColumnName(columnIndex).equals("Valor")) {
                return "R$ " + Mem.getValor();
            }
            if (getColumnName(columnIndex).equals("Validade")) {
                return DataUtil.converteDataToString(Mem.getDataValidade());
            }
            if (getColumnName(columnIndex).equals("Pago")) {
                if (Mem.getDataPagamento() != null) {
                    return DataUtil.converteDataToString(Mem.getDataPagamento());
                }
            }
            return "";
        } else {
            return "";
        }

    }
}
