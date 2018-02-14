/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author rodrigos
 */
public class RenAgendamento extends JLabel implements TableCellRenderer {

    public RenAgendamento() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel rot = new JLabel();
        if (value != null) {
            rot.setOpaque(true);
            rot.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE));

            rot.setVerticalAlignment(CENTER);

            rot.setForeground(Color.BLUE);

            rot.setHorizontalTextPosition(RIGHT);
            rot.setFont(new Font("Serif", Font.BOLD, 12));
            if (isSelected) {
                rot.setBackground(new Color(0, 51, 255));
                rot.setForeground(Color.WHITE);
            }
        }
        return rot;
    }

    public void updateTableAllCabosf() {
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

}
