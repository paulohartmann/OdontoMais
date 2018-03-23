package odontomais.view;

import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.model.Tratamento;
import odontomais.service.ConvenioService;
import odontomais.service.PacienteService;
import odontomais.service.TratamentoService;
import odontomais.service.util.MensagensAlerta;
import odontomais.view.tabmod.JCheckBoxList;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NovoPacienteComplementar extends JDialog {

    private JPanel contentPane;
    private JTextField edtProfissao;
    private JTextField edtProblemaSaude;
    private JCheckBox edtCheckTratamentoMedico;
    private JTextField edtMedicamentoRecorrente;
    private JTextField edtAlergias;
    private JButton salvarButton;
    private JButton cancelButton;
    private JList CheckBoxList;

    private Paciente paciente;
    private DefaultListModel<JCheckBox> modelTratamento;

    public NovoPacienteComplementar(Paciente p) {
        this.paciente = p;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(salvarButton);
        completaTela();

        salvarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        completaObjeto();

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
        TratamentoService service = new TratamentoService();
        paciente.getTratamentos().clear();
        for(int i = 0; i < modelTratamento.size(); i++){
            JCheckBox box = modelTratamento.get(i);
            if(box.isSelected()){
                Tratamento t = service.findByName(box.getText());
                paciente.getTratamentos().add(t);
            }
        }
    }

    private void completaTela(){
        if(paciente.isTratamentoMedicoRecente()){
            edtCheckTratamentoMedico.setSelected(true);
        }else {
            edtCheckTratamentoMedico.setSelected(false);
        }
        edtAlergias.setText((paciente.getAlergias() == null) ? "" : paciente.getAlergias());
        edtMedicamentoRecorrente.setText((paciente.getMedicamentosRecorrentes() == null) ? "" : paciente.getMedicamentosRecorrentes());
        edtProblemaSaude.setText((paciente.getProblemasSaude() == null) ? "" : paciente.getProblemasSaude());
        edtProfissao.setText((paciente.getProfissao() == null) ? "" : paciente.getProfissao());
        TratamentoService service = new TratamentoService();
        for(Tratamento t : service.getList()){
            JCheckBox box = new JCheckBox(t.getNome());
            modelTratamento.addElement(box);
            for(Tratamento o : paciente.getTratamentos()){
                if(t.getNome().equals(o.getNome())){
                    box.setSelected(true);
                }
            }
        }


    }

    private void createUIComponents() {
        modelTratamento = new DefaultListModel<JCheckBox>();
        CheckBoxList = new JCheckBoxList(modelTratamento);
    }
}
