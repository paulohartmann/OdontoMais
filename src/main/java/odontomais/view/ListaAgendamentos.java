package odontomais.view;

import odontomais.model.Agendamento;
import odontomais.model.Paciente;
import odontomais.service.AgendamentoService;
import odontomais.view.tabmod.TabAgendamento;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ListaAgendamentos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblAgendamento;
    private JButton pacienteButton;
    private JButton convênioButton;

    private Paciente paciente;
    private TabAgendamento tabAgendamento;

    public ListaAgendamentos() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        pacienteButton.addActionListener(e -> goPacienteList());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void goPacienteList() {
        ListaPacientes listaPacientes = new ListaPacientes();
        listaPacientes.pack();
        listaPacientes.setLocationRelativeTo(null);
        listaPacientes.setVisible(true);

        if (listaPacientes.getPaciente() != null) {
            paciente = listaPacientes.getPaciente();
            AgendamentoService service = new AgendamentoService();
            atualizaTabela(service.findByPaciente(paciente));

        }
    }

    private void atualizaTabela(List<Agendamento> list) {
        tabAgendamento = new TabAgendamento(list);
        tblAgendamento.setModel(tabAgendamento);
        tblAgendamento.getTableHeader().setReorderingAllowed(false);
        tblAgendamento.getTableHeader().setVisible(true);
        tblAgendamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabAgendamento.fireTableDataChanged();
    }

    private void onOK() {
        if (tblAgendamento.getSelectedRow() >= 0) {
            Agendamento ag = tabAgendamento.get(tblAgendamento.getSelectedRow());
            NovoAgendamento dialog = new NovoAgendamento(ag);
            dialog.pack();
            dialog.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Selecione um agendamento na tabela", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
