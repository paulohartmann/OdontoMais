package odontomais.view;

import odontomais.model.Agendamento;
import odontomais.model.Paciente;
import odontomais.service.AgendamentoService;
import odontomais.service.PacienteService;
import odontomais.service.util.MensagensAlerta;
import odontomais.view.tabmod.TabPaciente;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ListaPacientes extends JDialog {
    private JPanel contentPane;
    private JButton btnSelecionar;
    private JButton buttonCancel;
    private JTable tblPacientes;
    private JTextField edtNomePaciente;
    private JButton procurarButton;
    private JButton btnEditar;
    private JButton excluirButton;

    private TabPaciente tabPaciente;
    private Paciente paciente;

    public ListaPacientes() {
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnSelecionar);

        addWindowListener(new WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnSelecionar.addActionListener(e -> onSelecionar());

        buttonCancel.addActionListener(e -> onCancel());

        btnEditar.addActionListener(e -> onEditar());

        procurarButton.addActionListener(e -> onProcurar());

        edtNomePaciente.addActionListener(e -> onProcurar());

        excluirButton.addActionListener(e -> onExcluir());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onExcluir() {
        if (tblPacientes.getSelectedRow() >= 0) {
            Paciente p = tabPaciente.get(tblPacientes.getSelectedRow());
            int opc = JOptionPane.showConfirmDialog(this, "Você deseja realmente excluir o paciente "+ p.getNomeCompleto() + "?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opc == JOptionPane.YES_OPTION) {
                AgendamentoService agendamentoService = new AgendamentoService();
                List<Agendamento> listAg = agendamentoService.findByPaciente(p);
                for(Agendamento a : listAg){
                    agendamentoService.remove(a);
                }
                PacienteService service = new PacienteService();
                service.remover(p);
                onProcurar();
            }
        }
    }

    private void onProcurar() {
        PacienteService service = new PacienteService();
        List<Paciente> list = service.findFromName(edtNomePaciente.getText());
        atualizaTabela(list);
    }

    private void formWindowOpened(WindowEvent evt) {
        atualizaTabela(new ArrayList<>());
    }

    private void atualizaTabela(List<Paciente> list) {
        tabPaciente = new TabPaciente(list);
        tblPacientes.setModel(tabPaciente);
        tabPaciente.fireTableDataChanged();
    }

    private void onSelecionar() {
        paciente = selectPaciente();
        if (paciente != null) {
            dispose();
        }
    }

    private void onEditar() {
        paciente = selectPaciente();
        if (paciente != null) {
            NovoPaciente dialog = new NovoPaciente(paciente);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Paciente selectPaciente() {
        if (tblPacientes.getSelectedRow() >= 0) {
            Paciente p = tabPaciente.get(tblPacientes.getSelectedRow());
            return p;
        } else {
            MensagensAlerta.msgNadaSelecionadoTabela(this);
        }
        return null;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }
}
