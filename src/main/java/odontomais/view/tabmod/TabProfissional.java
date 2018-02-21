/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import odontomais.model.Profissional;

/**
 *
 * @author rodrigos
 */
public class TabProfissional extends AbstractTableModel {

    private ArrayList<Profissional> datalist;
    private Boolean[] editcolumns = new Boolean[3];
    private String[] columns = {"Nome",
        "Celular",
        "Observações"};

    public TabProfissional(ArrayList<Profissional> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        editcolumns[2] = false;
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

    public void add(Profissional his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Profissional> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Profissional get(int rows) {
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

    public void remove(Profissional mem) {
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
        Profissional Mem = get(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Nome")) {
                return Mem.getNome();
            }
            if (getColumnName(columnIndex).equals("Celular")) {
                return Mem.getTelCelular();
            }
//            if (getColumnName(columnIndex).equals("Horário Almoço")) {
//                return Mem.getHorarioAlmocoInicio().format(formatter) + " - " + Mem.getHorarioAlmocoFim().format(formatter);
//            }
//            if (getColumnName(columnIndex).equals("Dias Serviço")) {
//                boolean[] dias = Mem.montaArrayDiasServico();
//                String retorno = "";
//                if (dias[0]) {
//                    retorno += "DOM-";
//                }
//                if (dias[1]) {
//                    retorno += "SEG-";
//                }
//                if (dias[2]) {
//                    retorno += "TER-";
//                }
//                if (dias[3]) {
//                    retorno += "QUA-";
//                }
//                if (dias[4]) {
//                    retorno += "QUI-";
//                }
//                if (dias[5]) {
//                    retorno += "SEX-";
//                }
//                if (dias[6]) {
//                    retorno += "SAB";
//                }
//                return retorno;
//            }
            if (getColumnName(columnIndex).equals("Observações")) {
                return Mem.getObservacao();
            }
            return "";
        } else {
            return "";
        }

    }
}
