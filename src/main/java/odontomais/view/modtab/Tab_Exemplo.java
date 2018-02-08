/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.modtab;

import br.ind.fockink.dao.CaboFabricaDAO;
import br.ind.fockink.modelo.CaboFabrica;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigos
 */
public class Tab_Exemplo extends AbstractTableModel {

    private ArrayList<CaboFabrica> datalist;
    private boolean salvar = true;
    private Boolean[] editcolumns = new Boolean[8];
    private String[] columns = {"Grupo",
        "Cabo",
        "Arco",
        "Sensores",
        "Distancia",
        "X",
        "Y",
        "Compensacao"};

    public Tab_Exemplo(ArrayList<CaboFabrica> l) {
        datalist = l;
        editcolumns[0] = false;
        editcolumns[1] = true;
        editcolumns[2] = false;
        editcolumns[3] = false;
        editcolumns[4] = false;
        editcolumns[5] = false;
        editcolumns[6] = false;
        editcolumns[7] = true;
        super.fireTableDataChanged();
    }

    public void setSalvar(boolean set) {
        salvar = set;
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

    public void addCabo(CaboFabrica his) {
        datalist.add(his);
        super.fireTableDataChanged();
    }

    public void addCabos(ArrayList<CaboFabrica> l) {
        datalist.addAll(l);
        super.fireTableDataChanged();
    }

    public CaboFabrica getCabo(int rows) {
        if ((!datalist.isEmpty()) && (getRowCount() >= rows)) {
            return datalist.get(rows);
        } else {
            return null;
        }
    }

    public void removeCabo(int linha) {
        datalist.remove(linha);
        super.fireTableRowsDeleted(linha, linha);
    }

    public void removeCabo(CaboFabrica mem) {
        int pos = datalist.indexOf(mem);
        datalist.remove(mem);
        super.fireTableRowsDeleted(pos, pos);
    }

    /**
     * Retorna o objeto cabo ou null;
     *
     * @param num int:Numero do cabo;
     * @return Cabo;
     */
    public CaboFabrica getNumCabo(int num) {
        for (CaboFabrica c : datalist) {
            if (c.getNum_cabo() == num) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<CaboFabrica> getArrayCabo() {
        return datalist;
    }

    public int getRow(CaboFabrica c) {
        return datalist.indexOf(c);
    }

    @Override
    public String getColumnName(int col) {
        return this.columns[col].toString();
    }

    @Override
    public Class getColumnClass(int c) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return editcolumns[c];
    }

    @Override
    public int getRowCount() {
        if (!datalist.isEmpty()) {
            return datalist.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public void setValueAt(Object aValue, int r, int c) {
        CaboFabrica cab = datalist.get(r);

        if (getColumnName(c).equals("Cabo")) {
            //Integer valor = (Integer) aValue;
            cab.setNum_cabo(Integer.valueOf((String) aValue));
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Arco")) {
            Integer valor = (Integer) aValue;
            cab.setArco(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Sensores")) {
            Integer valor = (Integer) aValue;
            cab.setNumSensores(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Distancia")) {
            Float valor = (Float) aValue;
            cab.setDistanciaSensor(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("X")) {
            Float valor = (Float) aValue;
            cab.setCompInf(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Y")) {
            Float valor = (Float) aValue;
            cab.setCompSup(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Grupo")) {
            String valor = (String) aValue;
            cab.setLetraGrupo(valor);
            datalist.set(r, cab);
            salvar(cab);
        }
        if (getColumnName(c).equals("Compensacao")) {
            //Float valor = (Float) aValue;
            cab.setCompensacao(Float.valueOf((String) aValue));
            datalist.set(r, cab);
            salvar(cab);
        }

    }

    /**
     * Método que salva no banco o cabo que foi alterado pelo AbstactTableModel;
     * Apenas salva, não retorna nada.
     *
     * @param cab CaboFabrica
     */
    public void salvar(CaboFabrica cab) {
        if (salvar) {
            CaboFabricaDAO dao = new CaboFabricaDAO();
            try {
                dao.UpdateInsert(cab);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Problemas ao atualizar Cabo no BD. \n" + ex.getMessage(),
                        "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CaboFabrica Mem = getCabo(rowIndex);
        if (Mem != null) {
            if (getColumnName(columnIndex).equals("Cabo")) {
                return Mem.getNum_cabo();
            }
            if (getColumnName(columnIndex).equals("Arco")) {
                return Mem.getArco();
            }
            if (getColumnName(columnIndex).equals("Sensores")) {
                return Mem.getNumSensores();
            }
            if (getColumnName(columnIndex).equals("Distancia")) {
                return Mem.getDistanciaSensor();
            }
            if (getColumnName(columnIndex).equals("X")) {
                return Mem.getCompInf();
            }
            if (getColumnName(columnIndex).equals("Y")) {
                return Mem.getCompSup();
            }
            if (getColumnName(columnIndex).equals("Compensacao")) {
                return Mem.getCompensacao();
            }
            if (getColumnName(columnIndex).equals("Grupo")) {
                return Mem.getLetraGrupo();
            }
        }
        return "";
    }

}
