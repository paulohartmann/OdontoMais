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

}
