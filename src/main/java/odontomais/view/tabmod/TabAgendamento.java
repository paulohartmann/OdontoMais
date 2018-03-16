/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Agendamento;
import odontomais.model.Clinica;
import odontomais.service.util.DataUtil;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigos
 */
public class TabAgendamento extends AbstractTableModel {

    private List<Agendamento> datalist;
    private Boolean[] editcolumns = new Boolean[6];
    private String[] columns = {"Nome", "Profissional", "Data", "Horário", "Convênio", "Observação"};

    public TabAgendamento(List<Agendamento> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = false;
        editcolumns[2] = false;
        editcolumns[3] = false;
        editcolumns[4] = false;
        editcolumns[5] = false;
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

    public void add(Agendamento his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void add(ArrayList<Agendamento> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public Agendamento get(int rows) {
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

    public void remove(Agendamento mem) {
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
        Agendamento Mem = get(rowIndex);
        if (Mem != null) {
            if(!Mem.getObservacao().equals("Horário Vago")) {
                if (getColumnName(columnIndex).equals("Nome")) {
                    return Mem.getPaciente().getNomeCompleto();
                }
                if (getColumnName(columnIndex).equals("Profissional")) {
                    return Mem.getProfissional().getNome();
                }
                if (getColumnName(columnIndex).equals("Data")) {
                    return DataUtil.converteDataToString(Mem.getDataAgenda());
                }
                if (getColumnName(columnIndex).equals("Horário")) {
                    return DataUtil.converteTimeToString(Mem.getHoraInicio());
                }
                if (getColumnName(columnIndex).equals("Convênio")) {
                    return Mem.getConvenio().getNome();
                }
                if (getColumnName(columnIndex).equals("Observação")) {
                    return Mem.getObservacao();
                }
            }else{
                return "";
            }
        }
        return "";

    }
}
