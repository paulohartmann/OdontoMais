/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Agendamento;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * @author rodrigos
 */
public class RenAgendamento extends JPanel implements TableCellRenderer {

    protected List<Agendamento> lista;

    public RenAgendamento(List<Agendamento> l) {
        this.lista = l;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JPanel panel = new JPanel();
        JLabel rot = new JLabel();
        if (value != null) {
            Agendamento agenda = lista.get(table.convertRowIndexToModel(row));
            rot.setText(agenda.getStatus());
            rot.setOpaque(true);
            rot.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE));

            rot.setVerticalAlignment(JLabel.CENTER);

            rot.setForeground(Color.BLUE);

            rot.setHorizontalTextPosition(JLabel.RIGHT);
            rot.setFont(new Font("Serif", Font.BOLD, 12));
            if (isSelected) {
                rot.setBackground(new Color(0, 51, 255));
                rot.setForeground(Color.WHITE);
            }
            panel.add(rot);
            JLabel horarioFim = new JLabel();
            horarioFim.setText(agenda.getHoraFim().toString());
        }
        rot.setText("teste");
        panel.add(rot);
        return panel;
    }

    /*public void updateTableAllCabosf() {
        CaboFabricaDAO daocabofabrica = new CaboFabricaDAO();
        modtabAll = new TabCaboFabrica(daocabofabrica.findAllFromPedido(pedido.getIdPedido()));
        ///adiciona o modelo a tabela...
        for (int i = 0; i <= 7; i++) {
            modtabAll.setEditableColum(i, false);
        }
        tbl_AllCabosf.setModel(modtabAll);
        tbl_AllCabosf.setDefaultRenderer(Object.class, new RenCaboF(modtabAll.getArrayCabo()));
        tbl_AllCabosf.getTableHeader().setReorderingAllowed(false);
    }

    public void updateTableModuloMotores(int tipo) {
        MotoresDAO o = new MotoresDAO();
        UnidadeDAO uniDAO = new UnidadeDAO();
        ModuloMotoresDAO modDAO = new ModuloMotoresDAO();
        ArrayList<Motores> motoresList = o.findMotoresIDOBRA(obra.getIdObra());
        for (Motores m : motoresList) {
            m.setUnidade(uniDAO.findUnidadesFromIdUnidade(m.getUnidade().getIdUnidade()));
            m.setModuloMotores(modDAO.findModuloMotorIDModuloMotor(m.getModuloMotores().getIdModuloMotor()));
        }
        tabMotor = new TabMotor(motoresList);
        tbl_moduloMotor.setModel(tabMotor);
        tbl_moduloMotor.getTableHeader().setReorderingAllowed(false);
        tbl_moduloMotor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabMotor.fireTableDataChanged();
    }
*/
}
