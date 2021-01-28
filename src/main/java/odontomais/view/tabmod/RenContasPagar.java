/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.ContasPagar;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author paulohar
 */
public class RenContasPagar extends JPanel implements TableCellRenderer {

    protected List<ContasPagar> lista;

    public RenContasPagar(List<ContasPagar> l) {
        this.lista = l;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //table.setRowHeight(20);
        LocalDate hoje = LocalDate.now();

        JLabel rot = new JLabel();
        if (value != null) {
            rot.setText(value.toString());
            if (column == 2) {
                ContasPagar agenda = lista.get(table.convertRowIndexToModel(row));

                //  rot.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE));

                //rot.setVerticalAlignment(JLabel.CENTER);
                //rot.setHorizontalTextPosition(JLabel.RIGHT);
                if (hoje.isEqual(agenda.getDataValidade()) || agenda.getDataValidade().isBefore(hoje)) {
                    rot.setOpaque(true);
                    rot.setBackground(new Color(255, 30, 50));
                    rot.setForeground(Color.BLACK);
                }
            }



            return rot;
        }
        return rot;

    }

}

