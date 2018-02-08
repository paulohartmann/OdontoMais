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
        novo.pack();
        novo.setVisible(true);
    }

    private void completaConvenioTela(){
        ConvenioService convenioService = new ConvenioService();
        List<Convenio> listaConvenio = convenioService.getLista();
        edtBoxConvenios.removeAllItems();
        for(Convenio c : listaConvenio) edtBoxConvenios.addItem(c.getNome());
    }

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        completaObjeto();
        PacienteService service = new PacienteService();
        if (service.salvarPaciente(paciente)) {
            MensagensAlerta.msgCadastroOK(this);
        } else {
            MensagensAlerta.msgErroCadastro(this);
        }
    }

    private void completaObjeto(){
        paciente.setProfissao(edtProfissao.getText());
        paciente.setProblemasSaude(edtProblemaSaude.getText());
        try {
            paciente.setConvenio(edtBoxConvenios.getSelectedItem().toString());
        }catch (Exception ex){
            paciente.setConvenio("");
        }
        if(edtCheckTratamentoMedico.isSelected()){
            paciente.setTratamentoMedicoRecente(true);
        }else{
            paciente.setTratamentoMedicoRecente(false);
        }
        paciente.setMedicamentosRecorrentes(edtMedicamentoRecorrente.getText());
        paciente.setAlergias(edtAlergias.getText());
    }
}
