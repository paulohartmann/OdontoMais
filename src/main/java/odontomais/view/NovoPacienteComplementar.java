package odontomais.view;

import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.service.ConvenioService;
import odontomais.service.PacienteService;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NovoPacienteComplementar extends JDialog {

    private JPanel contentPane;
    private JTextField edtProfissao;
    private JTextField edtProblemaSaude;
    private JComboBox<String> edtBoxConvenios;
    private JCheckBox edtCheckTratamentoMedico;
    private JTextField edtMedicamentoRecorrente;
    private JTextField edtAlergias;
    private JButton salvarButton;
    private JButton btnNovoConvenio;
    private JButton cancelButton;

    private Paciente paciente;

    public NovoPacienteComplementar(Paciente p) {
        this.paciente = p;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(salvarButton);
        completaConvenioTela();
        completaTela();

        salvarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnNovoConvenio.addActionListener(e -> newConvenio());

        cancelButton.addActionListener(e -> onCancel());

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

    private void newConvenio() {
        NovoConvenio novo = new NovoConvenio();
        novo.setLocationRelativeTo(null);
        novo.pack();
        novo.setVisible(true);
        completaConvenioTela();
    }

    private void completaConvenioTela() {
        ConvenioService convenioService = new ConvenioService();
        List<Convenio> listaConvenio = convenioService.getLista();
        edtBoxConvenios.removeAllItems();
        for (Convenio c : listaConvenio) {
            edtBoxConvenios.addItem(c.getNome());
        }
    }

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        completaObjeto();
        Convenio c = selecionaConvenio();
        if (c != null) {
            paciente.setConvenio(c);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um convênio na lista");
            return;
        }
        PacienteService service = new PacienteService();
        if(paciente.getId() == 0) {
            if (service.salvar(paciente)) {
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } else {
                MensagensAlerta.msgErroCadastro(this);
            }
        }else{
            service.atualizar(paciente);
            MensagensAlerta.msgCadastroOK(this);
            dispose();
        }
    }

    private Convenio selecionaConvenio() {
        ConvenioService service = new ConvenioService();
        Convenio c = service.findByName(edtBoxConvenios.getSelectedItem().toString());
        return c;
    }

    private void completaObjeto() {
        paciente.setProfissao(edtProfissao.getText());
        paciente.setProblemasSaude(edtProblemaSaude.getText());

        if (edtCheckTratamentoMedico.isSelected()) {
            paciente.setTratamentoMedicoRecente(true);
        } else {
            paciente.setTratamentoMedicoRecente(false);
        }
        paciente.setMedicamentosRecorrentes(edtMedicamentoRecorrente.getText());
        paciente.setAlergias(edtAlergias.getText());
    }

    private void completaTela(){
        if (paciente.getConvenio().getNome() != null) {
            edtBoxConvenios.setSelectedItem(paciente.getConvenio().getNome());
        }
        if(paciente.isTratamentoMedicoRecente()){
            edtCheckTratamentoMedico.setSelected(true);
        }else {
            edtCheckTratamentoMedico.setSelected(false);
        }
        edtAlergias.setText((paciente.getAlergias() == null) ? "" : paciente.getAlergias());
        edtMedicamentoRecorrente.setText((paciente.getMedicamentosRecorrentes() == null) ? "" : paciente.getMedicamentosRecorrentes());
        edtProblemaSaude.setText((paciente.getProblemasSaude() == null) ? "" : paciente.getProblemasSaude());
        edtProfissao.setText((paciente.getProfissao() == null) ? "" : paciente.getProfissao());
    }
}
