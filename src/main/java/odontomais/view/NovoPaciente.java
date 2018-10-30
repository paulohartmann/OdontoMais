package odontomais.view;

import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.service.ConvenioService;
import odontomais.service.PacienteService;
import odontomais.service.util.DataUtil;
import odontomais.service.util.FormatadoresTexto;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/*
 * Author: phlab
 * Date: 04/12/17
 */
public class NovoPaciente extends JDialog {

    private JButton btnSalvar;
    private JButton btnSalvarEContinuar;
    private JFormattedTextField edtCelular;
    private JFormattedTextField edtResidencial;
    private JFormattedTextField edtTrabalho;
    private JFormattedTextField edtEndereco;
    private JFormattedTextField edtBairro;
    private JFormattedTextField edtCidade;
    private JFormattedTextField edtEstado;
    private JFormattedTextField edtNome;
    private JRadioButton selectSexoF;
    private JRadioButton selectSexoM;
    private JFormattedTextField edtDataNascimento;
    private JFormattedTextField edtRG;
    private JFormattedTextField edtCPF;
    private JFormattedTextField edtEmail;
    private JPanel contentPane;
    private JComboBox edtBoxConvenios;
    private JButton btnNovoConvenio;

    private Paciente pacienteAtual;

    public NovoPaciente(Paciente paciente) {

        if (paciente != null) {
            pacienteAtual = paciente;
            completaTela();
        } else {
            pacienteAtual = new Paciente();
        }
        completaConvenioTela();

        setContentPane(contentPane);

        btnNovoConvenio.addActionListener(e -> newConvenio());

        btnSalvar.addActionListener(e -> salvar());

        btnSalvarEContinuar.addActionListener((ActionEvent e) -> {
            salvarEContinuar();
        });
    }

    private Convenio selecionaConvenio() {
        ConvenioService service = new ConvenioService();
        Convenio c = service.get(edtBoxConvenios.getSelectedItem().toString());
        return c;
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


    private void salvar() {
        if (testaCampos()) {
            completaObjeto();
            PacienteService service = new PacienteService();
            if (pacienteAtual.getId() > 0) {
                service.atualizar(pacienteAtual);
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } else {
                if (service.salvar(pacienteAtual)) {
                    MensagensAlerta.msgCadastroOK(this);
                    dispose();
                } else {
                    MensagensAlerta.msgErroCadastro(this);
                }
            }
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
        }
    }

    private void salvarEContinuar() {
        if (testaCampos()) {
            completaObjeto();
            NovoPacienteComplementar novo = new NovoPacienteComplementar(pacienteAtual);
            novo.pack();
            dispose();
            novo.setVisible(true);
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
        }
    }

    private void completaTela() {
        edtBairro.setText(pacienteAtual.getBairro());
        edtCelular.setText(pacienteAtual.getTelCel());
        edtCidade.setText(pacienteAtual.getCidade());
        edtCPF.setText(pacienteAtual.getCpf());
        edtDataNascimento.setText(DataUtil.converteDataToString(pacienteAtual.getDataNascimento()));
        edtEmail.setText(pacienteAtual.getEmail());
        edtEndereco.setText(pacienteAtual.getEndResidencial());
        edtEstado.setText(pacienteAtual.getEstado());
        edtNome.setText(pacienteAtual.getNomeCompleto());
        edtResidencial.setText(pacienteAtual.getTelRes());
        edtRG.setText(pacienteAtual.getRg());
        edtTrabalho.setText(pacienteAtual.getTelTrab());
        if (pacienteAtual.getSexo().equals("M")) {
            selectSexoM.setSelected(true);
            selectSexoF.setSelected(false);
        } else {
            selectSexoM.setSelected(false);
            selectSexoF.setSelected(true);
        }
    }

    private void completaObjeto() {
        pacienteAtual.setBairro(edtBairro.getText());
        pacienteAtual.setTelCel(edtCelular.getText());
        pacienteAtual.setCidade(edtCidade.getText());
        pacienteAtual.setCpf(edtCPF.getText());
        pacienteAtual.setDataNascimento(DataUtil.converteStringToDate(edtDataNascimento.getText()));
        pacienteAtual.setEmail(edtEmail.getText());
        pacienteAtual.setEndResidencial(edtEndereco.getText());
        pacienteAtual.setEstado(edtEstado.getText());
        pacienteAtual.setNomeCompleto(edtNome.getText());
        pacienteAtual.setTelRes(edtResidencial.getText());
        pacienteAtual.setRg(edtRG.getText());
        pacienteAtual.setTelTrab(edtTrabalho.getText());
        pacienteAtual.setConvenio(selecionaConvenio());

        if (selectSexoF.isSelected()) {
            pacienteAtual.setSexo("F");
        } else {
            pacienteAtual.setSexo("M");
        }
    }

    private boolean testaCampos() {
//        if (edtDataNascimento.getText().equals("__/__/____")) {
//            return false;
//        }
//        try {
//            if (DataUtil.converteStringToDate(edtDataNascimento.getText()).isAfter(LocalDate.now())) return false;
//        } catch (NullPointerException ex) {
//            return false;
//        }

        if (edtNome.getText().equals("")) {
            return false;
        }
        if (edtCelular.getText().equals("")) {
            return false;
        }


        PacienteService service = new PacienteService();
        if (service.findExisteByCPF(edtCPF.getText()) > 0) {
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }
        if (service.findExisteByRG(edtRG.getText()) > 0) {
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }
        Convenio c = selecionaConvenio();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Selecione um convênio na lista");
            return false;
        }

        return true;
    }

    private void createUIComponents() {
        edtCPF = new JFormattedTextField(FormatadoresTexto.cpfFormat());
        edtDataNascimento = new JFormattedTextField(FormatadoresTexto.dataFormat());
        edtCelular = new JFormattedTextField(FormatadoresTexto.foneCelFormat());
        edtResidencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
        edtRG = new JFormattedTextField(FormatadoresTexto.rgFormat());
        edtTrabalho = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
    }
}
