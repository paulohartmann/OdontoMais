/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.tabmod;

import odontomais.model.Agendamento;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import odontomais.service.util.DataUtil;

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
        JLabel horaIni = new javax.swing.JLabel();
        JLabel nomePaciente = new javax.swing.JLabel();
        JLabel horaFim = new javax.swing.JLabel();
        //table.setRowHeight(20);
        LocalDate hoje = LocalDate.now();


        JPanel panel = init(horaIni, horaFim, nomePaciente);

        //if (value != null) {
        Agendamento agenda = lista.get(table.convertRowIndexToModel(row));
        String nome;
        if (agenda.getId() > 0) {
            nome = agenda.getPaciente().getNomeCompleto();
        } else {
            nome = " ";
        }
        if (hoje.equals(agenda.getDataAgenda())) {
            panel.setBackground(new Color(135, 206, 250));
        }
        if (agenda.getHoraInicio().equals(LocalTime.of(12, 0))) {
            horaIni.setText("Horário Almoço");
            panel.setBackground(new Color(255,255,224));
        } else {
            horaIni.setText(DataUtil.converteTimeToString(agenda.getHoraInicio()) + " - " + nome);
        }



        //}
        return panel;
    }

    private JPanel init(JLabel jLabel1, JLabel jLabel2, JLabel jLabel3) {
        JPanel jPanel1 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");


        jLabel2.setText("");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(246, Short.MAX_VALUE))
        );

        return jPanel1;
    }
}
